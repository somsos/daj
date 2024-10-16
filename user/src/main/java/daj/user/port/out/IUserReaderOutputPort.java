package daj.user.port.out;

import daj.user.port.out.dto.AuthQrDto;

public interface IUserReaderOutputPort {
  
  AuthQrDto getAuthInfoByUsername(String username);

  AuthQrDto findAuthById(Integer userId);

}
