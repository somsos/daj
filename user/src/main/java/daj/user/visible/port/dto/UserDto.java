package daj.user.visible.port.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Integer id;

  private String username;

  private String email;

  private String password;

  private String token;

 private List<UserRoleDto> roles;
  
}
