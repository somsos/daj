package daj.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import daj.user.port.in.dto.RegisterRDto;
import daj.user.port.in.dto.RegisterRrDto;
import daj.user.port.out.IUserWriterOutputPort;
import daj.user.port.out.dto.UserRole;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

  @Mock
  IUserWriterOutputPort userDbWriter;
  
  @Test
  void testRegister_success() {
    var input = new RegisterRDto("mario1", "mario@email.com", "mario1p");

    final var rolesOnRegister = new ArrayList<UserRole>();
    // check roles in import.sql
    int publicRoleId = -53;
    rolesOnRegister.add(new UserRole(publicRoleId, null));

    when(userDbWriter.register(any(), any())).thenReturn(new RegisterRrDto(1, rolesOnRegister));

    RegisterService service = new RegisterService(userDbWriter);
    final RegisterRrDto output = service.register(input);

    assertEquals(1, output.getId());
    assertEquals(publicRoleId, output.getRoles().get(0).getId());
  }

}
