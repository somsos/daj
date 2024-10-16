package daj.user.service;

import org.springframework.stereotype.Service;

import daj.common.error.ErrorResponse;
import daj.user.port.in.ILoginInputPort;
import daj.user.port.in.dto.LoginRrDto;
import daj.user.port.in.dto.LoginRDto;
import daj.user.port.out.IUserReaderOutputPort;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService implements ILoginInputPort {
  
  private final IUserReaderOutputPort userReader;

  private final JwtService jwtService;

  @Override
  public LoginRrDto login(LoginRDto input) {
    final var userAuthInfoFound = userReader.getAuthInfoByUsername(input.getUsername());

    if(userAuthInfoFound == null) {
      throw new ErrorResponse("bad credentials", 400, "une");
    }

    if(!input.getPassword().equals("mario1p")) {
      throw new ErrorResponse("bad credentials", 400, "ce");
    }

    final String token = jwtService.generateToken(userAuthInfoFound.getId());

    final var output = new LoginRrDto(userAuthInfoFound.getId(), token);
    return output;
  }
  
}
