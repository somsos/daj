package daj.user.ztemporal;

import org.springframework.stereotype.Component;

import daj.user.port.out.IUserReaderOutputPort;
import daj.user.port.out.dto.AuthQrDto;


/*This class is just to temporally satisfy Components when this module, is
 * running tests (without adapter module),
*/

@Component
public class StuffToNotFail implements IUserReaderOutputPort {

  @Override
  public AuthQrDto getAuthInfoByUsername(String username) {
    throw new UnsupportedOperationException("Unimplemented method 'getAuthInfoByUsername'");
  }

  @Override
  public AuthQrDto findAuthById(Integer userId) {
    throw new UnsupportedOperationException("Unimplemented method 'findAuthById'");
  }

  
  
}
