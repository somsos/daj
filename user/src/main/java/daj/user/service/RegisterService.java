package daj.user.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import daj.user.port.in.IHasher;
import daj.user.port.in.IRegisterInputPort;
import daj.user.port.in.dto.UserDto;
import daj.user.port.in.dto.UserRoleDto;
import daj.user.port.out.IUserWriterOutputPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService implements IRegisterInputPort {

  final private IUserWriterOutputPort userDbWriter;
  
  final private IHasher hasher;

  @Override
  public UserDto register(UserDto input) {
    final var roles = Arrays.asList(new UserRoleDto(-53, null)); // public role
    input.setPassword(this.hasher.encode(input.getPassword()));
    final var saved = userDbWriter.register(input, roles);
    return saved;
  }
  
}
