package daj.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import daj.common.error.ErrorResponse;
import daj.user.port.out.dto.AuthQrDto;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    // Replace this with a secure key in a real application, ideally fetched from
    // environment variables
    public static final String SECRET = "Replacethiswithasecurekeyinarealapplicationideallyfetchedfromenvironmentvariable";

    @Value("${jwt.expiration-mins}")
    private int EXPIRATION_MIN = 5;

    // Generate token with given user name
    public String generateToken(Integer idUser) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, idUser);
    }

    public String extractUsername(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (Exception e) {
            throw this.castException(e);
        }
        
    }

    public Boolean validateToken(String token, AuthQrDto userDetails) {
        try {
            final String username = extractUsername(token);
            final Boolean valid = (username.equals(userDetails.getId() + "") && !isTokenExpired(token));
            return valid;
        } catch (Exception ex) {
            throw this.castException(ex);
        }
    }

    // Create a JWT token with specified claims and subject (user name)
    private String createToken(Map<String, Object> claims, Integer idUser) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(idUser + "")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRATION_MIN)) // Token valid for 30 minutes
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Get the signing key for JWT token
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract the expiration date from the token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract a claim from the token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private ErrorResponse castException(Exception ex) {
        if(ex instanceof ExpiredJwtException) {
            return new ErrorResponse("invalid token", 400, "expired");
        }

        if(ex instanceof SignatureException) {
            return new ErrorResponse("invalid token", 400, "signature"); 
        }
        
        return new ErrorResponse("invalid token", 400, "unknown");
    }

}