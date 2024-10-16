package daj.adapter.user.inWeb;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import daj.adapter.user.config.AuthConfig;
import daj.adapter.user.config.AuthJwtFilter;
import daj.user.port.in.ILoginInputPort;
import daj.user.port.in.dto.LoginRDto;
import daj.user.port.in.dto.LoginRrDto;
import daj.user.port.out.IUserReaderOutputPort;
import daj.user.port.out.dto.AuthQrDto;
import daj.user.service.JwtService;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//WARNING: there is a class with the same name
//import jakarta.security.auth.message.config.AuthConfig;

//@Import({AuthConfig.class})
@WebMvcTest({AuthConfig.class, AuthJwtFilter.class, JwtService.class, AuthController.class})
public class AuthControllerTest {

  @MockBean
  IUserReaderOutputPort userDbReader;

  @MockBean
  ILoginInputPort loginInputPort;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private MockMvc mvc;

  
  @Test
  void know_path_protection_from_anonymous() throws Exception {
    mvc.perform(get(AuthController.IS_LOGGED_PATH).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="random",roles={"PUBLIC"})
  void allowRequestToLogedUser() throws Exception {
    mvc.perform(get(AuthController.IS_LOGGED_PATH).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  void random_path_protection_from_anonymous() throws Exception {
    mvc.perform(get(getRandomPath()).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  private String getRandomPath() {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
        int randomLimitedInt = leftLimit + (int) 
          (random.nextFloat() * (rightLimit - leftLimit + 1));
        buffer.append((char) randomLimitedInt);
    }
    return "/" + buffer.toString();
  }

  @Test
  void testLogin_success() throws Exception {
    
    //scenario
    final LoginRDto input = new LoginRDto("mario1", "mario1p");
    final AuthQrDto userInDb = new AuthQrDto(1, "mario1", "mario1p");
    final LoginRrDto output = new LoginRrDto(1, "some-token");
    
    final var request = post(AuthController.LOGIN_PATH).contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(input));

    when(userDbReader.findAuthById(any())).thenReturn(userInDb);
    when(loginInputPort.login(any())).thenReturn(output);
    
    //test
    mvc.perform(request)
      .andExpect(status().is2xxSuccessful())
      .andExpect(jsonPath("$.id", is(output.getId())))
      .andExpect(jsonPath("$.token", is(output.getToken())))
    ;
    
  }
}