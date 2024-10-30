package daj.user.testComponents;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import daj.user.port.in.dto.UserDto;
import daj.user.port.out.IUserReaderOutputPort;


/*This class is just to temporally satisfy Components when this module, is
 * running tests (without adapter module),
*/

@Profile("test")
@Component
public class StuffToNotFail implements IUserReaderOutputPort {

  @Override
  public UserDto getAuthInfoByUsername(String username) {
    throw new UnsupportedOperationException("Unimplemented method 'getAuthInfoByUsername'");
  }

  @Override
  public UserDto findAuthById(Integer userId) {
    throw new UnsupportedOperationException("Unimplemented method 'findAuthById'");
  }

  
  
}
