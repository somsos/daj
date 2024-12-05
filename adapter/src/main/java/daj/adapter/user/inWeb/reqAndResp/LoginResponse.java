package daj.adapter.user.inWeb.reqAndResp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
  
  final private String token;
  
  final private LoginResponseUser user;
  
}
