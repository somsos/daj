package daj.user.port.out;

import java.util.List;

import daj.user.port.in.dto.RegisterRDto;
import daj.user.port.in.dto.RegisterRrDto;
import daj.user.port.out.dto.UserRole;

public interface IUserWriterOutputPort {
  
  RegisterRrDto register(RegisterRDto toRegister, List<UserRole> roles);

}
