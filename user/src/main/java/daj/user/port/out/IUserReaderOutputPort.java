package daj.user.port.out;

import daj.user.port.in.dto.UserDto;

public interface IUserReaderOutputPort {
  
  UserDto getAuthInfoByUsername(String username);

  UserDto findAuthById(Integer userId);

}
