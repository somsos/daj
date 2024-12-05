package daj.adapter.user.utils;

import daj.adapter.user.inWeb.reqAndResp.LoginResponse;
import daj.adapter.user.inWeb.reqAndResp.LoginResponseUser;
import daj.user.visible.port.dto.UserDto;

public abstract class ManualUserMapper {

  final public static LoginResponse dtoToLoginResponse(UserDto source) {
    final var user = new LoginResponseUser(source.getId(), source.getUsername(), source.getRoles());
    final var target = new LoginResponse(source.getToken(), user);
    return target;
  }
  
}
