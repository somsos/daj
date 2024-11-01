package daj.user.visible.port.out;

import daj.user.visible.port.dto.UserDto;

public interface IUserReaderOutputPort {
  
  UserDto getAuthInfoByUsername(String username);

  UserDto findAuthById(Integer userId);

}
