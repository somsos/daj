package daj.user.port.in;

import daj.user.port.in.dto.UserDto;

public interface IJwtService {

  // Generate token with given user name
  String generateToken(Integer idUser);

  String extractUsername(String token);

  Boolean validateToken(String token, UserDto userDetails);

}