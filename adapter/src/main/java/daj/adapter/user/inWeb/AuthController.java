package daj.adapter.user.inWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import daj.user.port.in.ILoginInputPort;
import daj.user.port.in.IRegisterInputPort;
import daj.user.port.in.dto.LoginRDto;
import daj.user.port.in.dto.LoginRrDto;
import daj.user.port.in.dto.RegisterRDto;
import daj.user.port.in.dto.RegisterRrDto;
import jakarta.validation.Valid;

@RestController
public class AuthController {
  
  public static final String LOGIN_PATH = "/auth/create-token";
  public static final String REGISTER_PATH = "/auth/register";
  
  public static final String CHECK_REGISTERED_USER = "/auth/is-logged";
  public static final String CHECK_USERS_ROLE = "/auth/check-user-role";
  public static final String CHECK_PRODUCT_ROLE = "/auth/check-product-role";

  @Autowired
  private ILoginInputPort loginPortInput;

  @Autowired
  IRegisterInputPort registerInputPort;

  @PostMapping(LOGIN_PATH)
  public LoginRrDto login(@Valid @RequestBody LoginRDto input) {
    LoginRrDto output = loginPortInput.login(input);
    return output;
  }

  @PostMapping(REGISTER_PATH)
  @ResponseStatus(HttpStatus.CREATED)
  public RegisterRrDto register(@Valid @RequestBody RegisterRDto input) {
    var output = registerInputPort.register(input);
    return output;
  }

  @GetMapping(CHECK_REGISTERED_USER)
  public String isLogged() {
    return "valid registered user";
  }

  @GetMapping(CHECK_USERS_ROLE)
  public String checkUsersRole() {
    return "users role ok";
  }

  @GetMapping(CHECK_PRODUCT_ROLE)
  public String checkProductsRole() {
    return "products role ok";
  }

}
