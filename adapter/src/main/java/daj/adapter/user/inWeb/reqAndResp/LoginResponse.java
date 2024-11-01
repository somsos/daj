package daj.adapter.user.inWeb.reqAndResp;

import java.util.List;

import daj.user.visible.port.dto.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

  final private Integer id;
  
  final private String token;
  
  final private List<UserRoleDto> roles;
  
}
