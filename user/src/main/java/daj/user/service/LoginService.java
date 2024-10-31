package daj.user.service;

import org.springframework.stereotype.Service;

import daj.common.error.ErrorResponse;
import daj.user.port.in.IHasher;
import daj.user.port.in.IJwtService;
import daj.user.port.in.ILoginInputPort;
import daj.user.port.in.dto.UserDto;
import daj.user.port.out.IUserReaderOutputPort;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService implements ILoginInputPort {
  
  private final IUserReaderOutputPort userReader;

  private final IJwtService jwtService;

  private final IHasher hasher;

  @Override
  public UserDto login(UserDto input) {
    final var userAuthInfoFound = userReader.getAuthInfoByUsername(input.getUsername());

    if(userAuthInfoFound == null) {
      throw new ErrorResponse("bad credentials", 400, "une");
    }

    if(!hasher.matches(input.getPassword(), userAuthInfoFound.getPassword())) {
      throw new ErrorResponse("bad credentials", 400, "ce");
    }

    final String token = jwtService.generateToken(userAuthInfoFound.getId());

    final var output = new UserDto();
    output.setId(userAuthInfoFound.getId());
    output.setToken(token);
    return output;
  }
  
}
