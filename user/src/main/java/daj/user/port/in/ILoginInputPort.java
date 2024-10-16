package daj.user.port.in;

import daj.user.port.in.dto.LoginRrDto;
import daj.user.port.in.dto.LoginRDto;

public interface ILoginInputPort {

  LoginRrDto login(LoginRDto input);
  
}
