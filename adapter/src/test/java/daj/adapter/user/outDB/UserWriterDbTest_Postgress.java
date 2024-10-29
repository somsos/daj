package daj.adapter.user.outDB;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import daj.adapter.user.outDB.entity.RoleEntity;
import daj.adapter.user.outDB.utils.ErrorConstrainToUserMsg;
import daj.user.port.in.dto.RegisterRDto;
import daj.user.port.out.dto.UserRole;

@DataJpaTest
@Import({UserWriterDb.class, RoleEntity.class})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserWriterDbTest_Postgress {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
          "postgres:14-alpine3.16"
  );

  @BeforeAll
  static void beforeAll() {
      postgres.start();
  }

  @AfterAll
  static void afterAll() {
      postgres.stop();
  }


  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", postgres::getJdbcUrl);
      registry.add("spring.datasource.username", postgres::getUsername);
      registry.add("spring.datasource.password", postgres::getPassword);
  }

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
    assertEquals("ROLE_registered", found.getRoles().get(0).getAuthority());

  }


  @Test
  void testRegister_repeatedUser() {
    final var toRegister = new RegisterRDto("mario1", "mario1@email.com", "mario1p");
    final var roles = new ArrayList<UserRole>();
    roles.add(new UserRole(-53, null));
    try {
      userWriterDb.register(toRegister, roles);
    } catch (DataIntegrityViolationException e) {
      final var errorMsgGot = ErrorConstrainToUserMsg.getUserMsg(e).getMessage();
      assertEquals(ErrorConstrainToUserMsg.usernameInUse, errorMsgGot);
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
    } catch (DataIntegrityViolationException e) {
      final var errorMsgGot = ErrorConstrainToUserMsg.getUserMsg(e).getMessage();
      assertEquals(ErrorConstrainToUserMsg.userEmailInUse, errorMsgGot);
      return ;
    }
    
    fail("exception expected");
  }



}
