package daj.adapter.product.inWeb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static daj.adapter.common.AuthConstants.ROLE_REGISTERED;
import static daj.adapter.common.AuthConstants.ROLE_PRODUCT;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

import daj.adapter.product.inWeb.reqAndRes.ProductSimpleResponse;
import daj.adapter.product.inWeb.reqAndRes.ProductsSaveRequest;
import daj.adapter.user.config.AuthConfig;
import daj.adapter.user.config.AuthJwtFilter;
import daj.product.port.in.IProductWriteInputPort;
import daj.product.port.in.dto.ProductAllPublicInfo;
import daj.product.port.in.dto.RProductImage;
import daj.user.port.out.IUserReaderOutputPort;
import daj.user.service.JwtService;

@WebMvcTest({ ProductWriterController.class, AuthConfig.class, AuthJwtFilter.class, JwtService.class })
public class ProductWriterControllerTest {

  @MockBean
  IProductWriteInputPort writerIP;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private MockMvc mvc;

  @MockBean
  IUserReaderOutputPort userDbReader;
  

  // ############# Save


  @Test
  void testSave_mustBeProtected_FromAnonymousUsers() throws Exception {
    mvc.perform(post(ProductWebConstants.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_REGISTERED})
  void testSave_mustBeProtected_FromRegisteredUsers() throws Exception {
    mvc.perform(post(ProductWebConstants.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_PRODUCT})
  void testSave_success() throws Exception {

    //Scenario
    final var input = new ProductsSaveRequest("trompo1", 10.10f, 10, "description1");
    final var output = new ProductSimpleResponse(1, null);
    final var request = post(ProductWebConstants.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(input));
    when(writerIP.save(any())).thenReturn(output);

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
    final var output = new ProductSimpleResponse(1, null);
    final var request = post(ProductWebConstants.POINT_PRODUCTS).contentType(MediaType.APPLICATION_JSON)
    .content(objectMapper.writeValueAsString(input));
    when(writerIP.save(any())).thenReturn(output);

    //Test
    mvc.perform(request)
      .andExpect(status().isBadRequest())
      //.andExpect(jsonPath("$.name", is("must not be blank")))
    ;
  }


  // ############# Delete
  @Test
  void testDelete_mustBeProtected_FromAnonymousUsers() throws Exception {
    mvc.perform(delete(ProductWebConstants.POINT_PRODUCTS + "/1").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_REGISTERED})
  void testDelete_mustBeProtected_FromRegisteredUsers() throws Exception {
    mvc.perform(delete(ProductWebConstants.POINT_PRODUCTS + "/1").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_PRODUCT})
  void testDelete_success() throws Exception {

    //Scenario
    final var request = delete(ProductWebConstants.POINT_PRODUCTS_ID.replace("{id}", "1"))
      .contentType(MediaType.APPLICATION_JSON);

    final var output = new ProductSimpleResponse(1, null);
    
    when(writerIP.delete(1)).thenReturn(output);

    //Test
    mvc.perform(request)
      .andExpect(status().is(HttpStatus.ACCEPTED.value()))
      .andExpect(jsonPath("$.id", is(output.getId())))
    ;
  }


  // ############# Update
  @Test
  void testUpdate_mustBeProtected_FromAnonymousUsers() throws Exception {
    final var point = ProductWebConstants.POINT_PRODUCTS_ID.replace("{id}", "1");
    mvc.perform(put(point).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_REGISTERED})
  void testUpdate_mustBeProtected_FromRegisteredUsers() throws Exception {
    final var point = ProductWebConstants.POINT_PRODUCTS_ID.replace("{id}", "1");
    mvc.perform(put(point).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_PRODUCT})
  void testUpdate_success() throws Exception {

    //Scenario
    final var input = new ProductsSaveRequest("trompo100", 100.10f, 100, "description100");
    final var output = new ProductAllPublicInfo(1, "trompo11", 101.101f, 101, "description101", new Date());
    final var point = ProductWebConstants.POINT_PRODUCTS_ID.replace("{id}", "1");
    final var request = put(point)
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(input));
      ;
    when(writerIP.update(any(), any())).thenReturn(output);

    //Test
    mvc.perform(request)
      .andExpect(status().is(HttpStatus.ACCEPTED.value()))
      .andExpect(jsonPath("$.id", is(output.getId())))
      .andExpect(jsonPath("$.name", is(output.getName())))
    ;
  }


  //Upload image product
  @Test
  void test_uploadImage_mustBeProtected_FromAnonymousUsers() throws Exception {
    final var endpoint = ProductWebConstants.POINT_PRODUCTS_IMAGE.replace("{id}", "1");
    mvc.perform(post(endpoint).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_REGISTERED})
  void test_uploadImage_mustBeProtected_FromRegisteredUsers() throws Exception {
    final var endpoint = ProductWebConstants.POINT_PRODUCTS_IMAGE.replace("{id}", "1");
    mvc.perform(post(endpoint).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username="mario1",roles={ROLE_PRODUCT})
  void test_uploadImage_success() throws Exception {
    final var image = new MockMultipartFile("image", "filename.png", MediaType.IMAGE_PNG.getType(), "soma_data".getBytes());
    final var endpoint = ProductWebConstants.POINT_PRODUCTS_IMAGE.replace("{id}", "1");
    final var request = MockMvcRequestBuilders.multipart(endpoint).file(image);

    final var outPut = new RProductImage(1, image.getName(), image.getContentType(), image.getBytes(), null);
    when(writerIP.saveImage(any())).thenReturn(outPut);

    mvc.perform(request)
      .andExpect(status().is(HttpStatus.CREATED.value()))
      .andExpect(jsonPath("$.id", is(outPut.getId())))
    ;

  }


  


}
