package daj.adapter.user.inWeb.reqAndResp;

import java.util.List;

import daj.user.port.in.dto.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponse {

  final private Integer id;
  
  final private List<UserRoleDto> roles;
  
}
