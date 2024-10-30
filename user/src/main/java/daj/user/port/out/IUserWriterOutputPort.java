package daj.user.port.out;

import java.util.List;

import daj.user.port.in.dto.UserDto;
import daj.user.port.in.dto.UserRoleDto;

public interface IUserWriterOutputPort {
  
  UserDto register(UserDto toRegister, List<UserRoleDto> roles);

}
