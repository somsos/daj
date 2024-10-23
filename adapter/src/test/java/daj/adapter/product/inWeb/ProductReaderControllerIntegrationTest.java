package daj.adapter.product.inWeb;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import daj.adapter.product.outDB.ProductEntity;
import daj.adapter.product.outDB.ProductReaderDbAdapter;
import daj.adapter.product.utils.ProductConstants;
import daj.adapter.user.config.AuthConfig;
import daj.adapter.user.config.AuthJwtFilter;
import daj.product.port.out.IProductReaderOutputPort;
import daj.product.service.ProductReaderService;
import daj.user.port.out.IUserReaderOutputPort;
import daj.user.service.JwtService;

@WebMvcTest({
  ProductReaderController.class, AuthConfig.class, AuthJwtFilter.class,
  JwtService.class, ProductReaderService.class, ProductReaderDbAdapter.class
})
@ActiveProfiles("test")
public class ProductReaderControllerIntegrationTest {

  @MockBean
  IUserReaderOutputPort userDbReader; // to mock auth flow

  @MockBean
  IProductReaderOutputPort repo;

  @Autowired
  private MockMvc mvc;

  @Test
  void testFindById_success() throws Exception {
    final var output = new ProductEntity(1, "trompo1", 11.11f, 11, "des1", new Date());
    when(repo.findById(1)).thenReturn(output);

    final var req = get(ProductWriterController.POINT_PRODUCTS + "/1");
    
    final var resp = mvc.perform(req);

    resp.andExpect(status().isOk())
      .andExpect(jsonPath("$.id", is(output.getId())));
  }


  @Test
  void testFindById_fail_notFound() throws Exception {
    final var req = get(ProductWriterController.POINT_PRODUCTS + "/1");
    when(repo.findById(1)).thenReturn(null);
    
    final var resp = mvc.perform(req);

    resp.andExpect(status().isNotFound())
      .andExpect(jsonPath("$.message", is(ProductConstants.NOT_FOUND)));
  }

}
