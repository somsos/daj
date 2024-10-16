package daj.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import daj.user.port.out.IUserReaderOutputPort;
import daj.user.port.out.dto.AuthQrDto;

import daj.user.port.in.dto.LoginRrDto;
import daj.user.port.in.dto.LoginRDto;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

  @Mock
  private IUserReaderOutputPort userReader;

  @Mock
  private JwtService jwtService;

  private LoginService loginService;

  @BeforeEach
  void setUp() throws Exception {
    this.loginService = new LoginService(userReader, jwtService);
  }

  @Test
  void testLogin_successLogin() {
    final var userInDb = new AuthQrDto(1, "mario1", "mario1p");
    final var userInput = new LoginRDto("mario1", "mario1p");
    final var tokenToGenerate = "some-token";

    when(this.userReader.getAuthInfoByUsername( any() )).thenReturn(userInDb);
    when(this.jwtService.generateToken(any())).thenReturn(tokenToGenerate);
    
    final LoginRrDto output = this.loginService.login(userInput);

    Mockito.verify(jwtService, times(1)).generateToken(userInDb.getId());
    assertEquals(userInDb.getId(), output.getId());
    assertEquals(output.getToken(), tokenToGenerate);
  }


  @Test
  void testLogin_failLogin_userNotFound() {
    final var userInput = new LoginRDto("mario1", "mario1p");
    when(this.userReader.getAuthInfoByUsername( any() )).thenReturn(null);

    try {
      this.loginService.login(userInput);  
    } catch (Exception e) {
      assertEquals("bad credentials", e.getMessage());
      return ;
    }
    
    fail("login should throw exception");
  }

  @Test
  void testLogin_failLogin_userPasswordNotMath() {
    final var userInput = new LoginRDto("mario1", "mario1XXX");
    final var userInDb = new AuthQrDto(1, "mario1", "mario1p");
    when(this.userReader.getAuthInfoByUsername( any() )).thenReturn(userInDb);

    try {
      this.loginService.login(userInput);  
    } catch (Exception e) {
      assertEquals("bad credentials", e.getMessage());
      return ;
    }
    
    fail("login should throw exception");
  }


}
