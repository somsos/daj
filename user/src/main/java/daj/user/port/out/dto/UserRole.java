package daj.user.port.out.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRole {

  private final Integer id;

  private String authority;
  
}
