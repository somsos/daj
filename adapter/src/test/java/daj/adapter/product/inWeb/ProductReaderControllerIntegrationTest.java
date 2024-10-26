package daj.adapter.product.inWeb;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import daj.adapter.product.outDB.ProductReaderDbAdapter;
import daj.adapter.product.utils.ProductConstants;
import daj.adapter.user.config.AuthConfig;
import daj.adapter.user.config.AuthJwtFilter;
import daj.product.port.in.IProductReadInputPort;
import daj.product.port.in.dto.ProductModel;
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

  @MockBean
  private IProductReadInputPort productReaderInputPort;

  @Autowired
  private MockMvc mvc;

  @Test
  void testFindById_success() throws Exception {
    final var output = new ProductModel(1, "trompo1", 11.11f, 11, "des1", new Date(), null);
    when(productReaderInputPort.findDetailsById(1)).thenReturn(output);

    final var point = ProductWebConstants.POINT_PRODUCTS_ID.replace("{id}", "1");
    final var req = get(point);
    
    final var resp = mvc.perform(req);

    resp.andExpect(status().isOk())
      .andExpect(jsonPath("$.id", is(output.getId())));
  }


  @Test
  void testFindByIdOrThrow_fail_notFound() throws Exception {
    final var req = get(ProductWebConstants.POINT_PRODUCTS + "/1");
    when(repo.findDetailsById(1)).thenReturn(null);
    
    final var resp = mvc.perform(req);

    resp.andExpect(status().isNotFound())
      .andExpect(jsonPath("$.message", is(ProductConstants.NOT_FOUND)));
  }

  @Test
  void testGetAllProducts() throws Exception {
    // Mock the product response
    ProductModel product1 = new ProductModel(1, "Product 1", 10.0f, 100, "Description 1", new Date(), null);
    ProductModel product2 = new ProductModel(2, "Product 2", 20.0f, 200, "Description 2", new Date(), null);
    List<ProductModel> products = Arrays.asList(product1, product2);

    // Mock the behavior of the service
    final Page<ProductModel> pageFound = new PageImpl<>(products);
    when(productReaderInputPort.getProductsByPage(0, 10)).thenReturn(pageFound);

    // Perform the GET request
    mvc.perform(get(ProductWebConstants.POINT_PRODUCTS_BY_PAGE)
        .param("page", "0")
        .param("size", "10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.length()").value(2))
        .andExpect(jsonPath("$.content[0].name").value("Product 1"))
        .andExpect(jsonPath("$.content[1].name").value("Product 2"))
    ;
  }

  //See image product
  @Test
  void test_uploadImage_mustFail_imageNotFound() throws Exception {
    final var endpoint = ProductWebConstants.POINT_PRODUCTS_IMAGE_ID.replace("{id}", "777");
    mvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.message", is(ProductWebConstants.ERROR_IMAGE_NOT_FOUND)))
    ;
  }
  

}
