package daj.user.port.in;

import daj.user.port.in.dto.UserDto;

public interface ILoginInputPort {

  UserDto login(UserDto input);
  
}
