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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

import daj.adapter.product.inWeb.reqAndRes.ProductSimpleResponse;
import daj.adapter.product.inWeb.reqAndRes.ProductsSaveRequest;
import daj.adapter.user.config.AuthConfig;
import daj.adapter.user.config.AuthJwtFilter;
import daj.product.port.in.IProductWriteInputPort;
import daj.user.port.out.IUserReaderOutputPort;
import daj.user.service.JwtService;

@WebMvcTest({ ProductWriterController.class, AuthConfig.class, AuthJwtFilter.class, JwtService.class })
public class ProductWriterControllerTest {

  @MockBean
  IProductWriteInputPort writerInputPort;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private MockMvc mvc;

  @MockBean
  IUserReaderOutputPort userDbReader;
  

  // ############# Save


  @Test
  void testSave_mustBeProtected_FromAnonymousUsers() throws Exception {
    mvc.perform(post(ProductWriterController.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_REGISTERED})
  void testSave_mustBeProtected_FromRegisteredUsers() throws Exception {
    mvc.perform(post(ProductWriterController.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_PRODUCT})
  void testSave_success() throws Exception {

    //Scenario
    final var input = new ProductsSaveRequest("trompo1", 10.10f, 10, "description1");
    final var output = new ProductSimpleResponse(1);
    final var request = post(ProductWriterController.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(input));
    when(writerInputPort.save(any())).thenReturn(output);

    //Test
    mvc.perform(request)
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.id", is(output.getId())))
    ;
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_PRODUCT})
  void testSave_mustNotAccept_RequestsWithoutName() throws Exception {

    //Scenario
    final var input = new ProductsSaveRequest(" ", 10.10f, 10, "description1");
    final var output = new ProductSimpleResponse(1);
    final var request = post(ProductWriterController.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(input));
    when(writerInputPort.save(any())).thenReturn(output);

    //Test
    mvc.perform(request)
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.name", is("must not be blank")))
    ;
  }


  // ############# Delete
  @Test
  void testDelete_mustBeProtected_FromAnonymousUsers() throws Exception {
    mvc.perform(delete(ProductWriterController.POINT_PRODUCTS + "/1").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_REGISTERED})
  void testDelete_mustBeProtected_FromRegisteredUsers() throws Exception {
    mvc.perform(delete(ProductWriterController.POINT_PRODUCTS + "/1").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_PRODUCT})
  void testDelete_success() throws Exception {

    //Scenario
    final var request = delete(ProductWriterController.POINT_PRODUCTS_ID.replace("{id}", "1"))
      .contentType(MediaType.APPLICATION_JSON);

    final var output = new ProductSimpleResponse(1);
    
    when(writerInputPort.delete(1)).thenReturn(output);

    //Test
    mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id", is(output.getId())))
    ;
  }


}
