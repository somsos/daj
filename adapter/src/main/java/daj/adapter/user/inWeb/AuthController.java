package daj.adapter.user.inWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import daj.user.port.in.ILoginInputPort;
import daj.user.port.in.dto.LoginRDto;
import daj.user.port.in.dto.LoginRrDto;
import jakarta.validation.Valid;

@RestController
public class AuthController {
  
  public static final String LOGIN_PATH = "/auth/create-token";
  public static final String IS_LOGGED_PATH = "/auth/is-logged";

  @Autowired
  private ILoginInputPort loginPortInput;

  @PostMapping(LOGIN_PATH)
  public LoginRrDto login(@Valid @RequestBody LoginRDto input) {
    LoginRrDto output = loginPortInput.login(input);
    return output;
  }

  @GetMapping(IS_LOGGED_PATH)
  public String isLogged() {
    return "my-secret";
  }

}
