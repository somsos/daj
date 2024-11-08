package daj.user.visible.port.out;

import daj.user.visible.port.dto.UserDto;

public interface IUserReaderOutputPort {
  
  UserDto findByUsername(String username);

  UserDto findById(Integer userId);

}
