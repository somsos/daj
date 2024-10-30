package daj.user.port.in;

import daj.user.port.in.dto.UserDto;

public interface IRegisterInputPort {
  
  UserDto register(UserDto input);

}
