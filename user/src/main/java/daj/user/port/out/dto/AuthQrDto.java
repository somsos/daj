package daj.user.port.out.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthQrDto {

  private final Integer id;

  private final String username;

  private final String password;

  private final List<UserRole> roles = new ArrayList<UserRole>();
  
}
