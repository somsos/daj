package daj.adapter.user.outDB;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import daj.common.error.ErrorResponse;
import daj.user.port.in.dto.RegisterRDto;
import daj.user.port.out.dto.UserRole;

@DataJpaTest
@Import({UserWriterDb.class})
public class UserWriterDbTest {

  @Autowired
  UserWriterDb userWriterDb;

  @Autowired
  private UserRepository userRepository;

  @Test
  void testRegister_success() {
    final var toRegister = new RegisterRDto("mario3", "mario3@email.com", "mario3Pass");
    final var roles = new ArrayList<UserRole>();
    roles.add(new UserRole(-53, null));
    userWriterDb.register(toRegister, roles);

    //check
    final var found = userRepository.findByUsername("mario3");
    assertEquals(toRegister.getEmail(), found.getEmail());
    assertEquals("registered", found.getRoles().get(0).getAuthority());

  }


  @Test
  void testRegister_repeatedUser() {
    final var toRegister = new RegisterRDto("mario1", "mario1@email.com", "mario1p");
    final var roles = new ArrayList<UserRole>();
    roles.add(new UserRole(-53, null));
    try {
      userWriterDb.register(toRegister, roles);
    } catch (ErrorResponse e) {
      assertEquals("Username already in use", e.getMessage());
      return ;
    }
    
    fail("exception expected");
  }


  @Test
  void testRegister_repeatedEmail() {
    final var toRegister = new RegisterRDto("marioXXX", "mario1@email.com", "marioXXp");
    final var roles = new ArrayList<UserRole>();
    roles.add(new UserRole(-53, null));
    try {
      userWriterDb.register(toRegister, roles);
    } catch (ErrorResponse e) {
      assertEquals("email already in use", e.getMessage());
      return ;
    }
    
    fail("exception expected");
  }

}
