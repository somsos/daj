package daj.adapter.product.outDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import daj.adapter.product.utils.ProductConstants;
import daj.adapter.product.utils.ProductUtilBeans;
import daj.common.error.ErrorResponse;
import daj.product.port.in.dto.ProductSimpleInfo;
import daj.product.port.out.IProductWriterOutputPort;

@ActiveProfiles("test")
@DataJpaTest
@Import({ProductWriterDBAdapter.class, ProductUtilBeans.class})
public class ProductWriterDbAdapterTest {

  @Autowired
  IProductWriterOutputPort productDBAdapter;

  @Autowired
  ProductRepository repo;
  
  @Test
  void testSave() {
    var input = new ProductEntity(null, "trompo1", 10.10f, 10, "description1", null);
    productDBAdapter.save(input);


    final ProductEntity found = repo.findByName(input.getName());
    assertNotNull(found.getCreatedAt());

  }

  @Test
  @Sql("test_createProduct.sql")
  void testDelete() {
    Integer toDel = 1;
    ProductSimpleInfo deleted = productDBAdapter.delete(toDel);
    assertEquals(toDel, deleted.getId());

    final ProductEntity found = repo.findById(toDel).orElse(null);
    assertNull(found);
  }

  @Test
  void testDelete_notFound() {
    ErrorResponse ex = assertThrows(
      ErrorResponse.class,
           () -> productDBAdapter.delete(1),
           "Not found exception expected"
    );
    assertEquals(ProductConstants.NOT_FOUND, ex.getMessage());
  }

}
