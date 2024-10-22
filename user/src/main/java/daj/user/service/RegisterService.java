package daj.user.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import daj.user.port.in.IRegisterInputPort;
import daj.user.port.in.dto.RegisterRDto;
import daj.user.port.in.dto.RegisterRrDto;
import daj.user.port.out.IUserWriterOutputPort;
import daj.user.port.out.dto.UserRole;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService implements IRegisterInputPort {

  final private IUserWriterOutputPort userDbWriter;
  
  final private IHasher hasher;

  @Override
  public RegisterRrDto register(RegisterRDto input) {
    final var roles = Arrays.asList(new UserRole(-53, null)); // public role
    input.setPassword(this.hasher.encode(input.getPassword()));
    final var saved = userDbWriter.register(input, roles);
    return saved;
  }
  
}
