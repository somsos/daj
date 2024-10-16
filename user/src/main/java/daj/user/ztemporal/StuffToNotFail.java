package daj.user.ztemporal;

import org.springframework.stereotype.Component;

import daj.user.port.out.IUserReaderOutputPort;
import daj.user.port.out.dto.AuthQrDto;

@Component
public class StuffToNotFail implements IUserReaderOutputPort {

  @Override
  public AuthQrDto getAuthInfoByUsername(String username) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAuthInfoByUsername'");
  }

  @Override
  public AuthQrDto findAuthById(Integer userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAuthById'");
  }

  
  
}
