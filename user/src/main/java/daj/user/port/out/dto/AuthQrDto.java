package daj.user.port.out.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthQrDto {

  private Integer id;

  private String username;

  private String password;

  private List<UserRole> roles;
  
}
