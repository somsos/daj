package daj.adapter.product.inWeb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static daj.adapter.common.AuthConstants.ROLE_REGISTERED;
import static daj.adapter.common.AuthConstants.ROLE_PRODUCT;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

import daj.adapter.product.inWeb.reqAndRes.ProductSimpleResponse;
import daj.adapter.product.inWeb.reqAndRes.ProductsSaveRequest;
import daj.adapter.user.config.AuthConfig;
import daj.adapter.user.config.AuthJwtFilter;
import daj.product.port.in.IProductInputPort;
import daj.user.port.out.IUserReaderOutputPort;
import daj.user.service.JwtService;

@WebMvcTest({ ProductController.class, AuthConfig.class, AuthJwtFilter.class, JwtService.class })
public class ProductControllerTest {

  @MockBean
  IProductInputPort inputPort;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private MockMvc mvc;

  @MockBean
  IUserReaderOutputPort userDbReader;
  
  @Test
  void testSave_mustBeProtected_FromAnonymousUsers() throws Exception {
    mvc.perform(post(ProductController.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_REGISTERED})
  void testSave_mustBeProtected_FromRegisteredUsers() throws Exception {
    mvc.perform(post(ProductController.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_PRODUCT})
  void testSave_success() throws Exception {

    //Scenario
    final var input = new ProductsSaveRequest("trompo1", 10.10f, 10, "description1");
    final var output = new ProductSimpleResponse(1, new Date());
    final var request = post(ProductController.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(input));
    when(inputPort.save(any())).thenReturn(output);

    //Test
    mvc.perform(request)
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.id", is(output.getId())))
      .andExpect(jsonPath("$.createdAt").isNotEmpty())
    ;
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_PRODUCT})
  void testSave_mustNotAccept_RequestsWithoutName() throws Exception {

    //Scenario
    final var input = new ProductsSaveRequest(" ", 10.10f, 10, "description1");
    final var output = new ProductSimpleResponse(1, new Date());
    final var request = post(ProductController.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(input));
    when(inputPort.save(any())).thenReturn(output);

    //Test
    mvc.perform(request)
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.name", is("must not be blank")))
    ;
  }

}
