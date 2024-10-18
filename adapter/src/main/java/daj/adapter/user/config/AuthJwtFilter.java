package daj.adapter.user.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import daj.adapter.user.outDB.entity.RoleEntity;
import daj.common.error.ErrorResponse;
import daj.user.port.out.IUserReaderOutputPort;
import daj.user.port.out.dto.AuthQrDto;
import daj.user.service.JwtService;

import java.io.IOException;

@Component
public class AuthJwtFilter implements Filter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserReaderOutputPort userReadings;

    @Override
    public void init(FilterConfig filterConfig) {
        this.jwtService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean(JwtService.class);

        this.userReadings = WebApplicationContextUtils
                .getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean(IUserReaderOutputPort.class);

        assert (userReadings != null);
        assert (jwtService != null);
    }

    @Override
    public void doFilter(ServletRequest requestArg, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        
        // Retrieve the Authorization header
        final var request = (HttpServletRequest) requestArg;
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String idUserInput = null;

        // Check if the header starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Extract token
            idUserInput = jwtService.extractUsername(token); // Extract username from token
        }        

        // If the token is valid and no authentication is set in the context
        final var currentSession = SecurityContextHolder.getContext().getAuthentication();
        if (authHeader != null && currentSession == null) {

            Integer idUser = null;
            try {
                idUser = Integer.parseInt(idUserInput);    
            } catch (NumberFormatException e) {
                throw new ErrorResponse("invalid token", 400, "expected sub as integer");
            }

            AuthQrDto authInfoFound = userReadings.findAuthById(idUser);

            // Validate token and set authentication
            boolean validToken = jwtService.validateToken(token, authInfoFound);

            if(validToken == false) {
                throw new ErrorResponse("Invalid token", 400, "validation failed");
            }
            
            final var authToken = this.fromAuthInfoToAuthToken(authInfoFound);
            
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken fromAuthInfoToAuthToken(AuthQrDto authInfo) {

      final var auths = authInfo.getRoles().stream()
        .map(r -> new RoleEntity(r.getId(), r.getAuthority(), null))
        .toList();

      final var userDetails = new UsernamePasswordAuthenticationToken(
        authInfo, null, auths);
      
        return userDetails;
    
    }

}