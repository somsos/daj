package daj.user.testComponents;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import daj.user.port.in.dto.RegisterRDto;
import daj.user.port.in.dto.RegisterRrDto;
import daj.user.port.out.IUserWriterOutputPort;
import daj.user.port.out.dto.UserRole;

@Profile("test")
@Component
public class UserWriterOutputPortMock implements IUserWriterOutputPort {

  @Override
  public RegisterRrDto register(RegisterRDto toRegister, List<UserRole> roles) {
    throw new UnsupportedOperationException("Unimplemented method 'register'");
  }
  
}
