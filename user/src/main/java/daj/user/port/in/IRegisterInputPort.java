package daj.user.port.in;

import daj.user.port.in.dto.RegisterRDto;
import daj.user.port.in.dto.RegisterRrDto;

public interface IRegisterInputPort {
  
  RegisterRrDto register(RegisterRDto input);

}
