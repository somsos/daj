package daj.adapter.product.outDB;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import daj.adapter.product.utils.ProductUtilBeans;

@ActiveProfiles("test")
@DataJpaTest
@Import({ProductReaderDbAdapter.class, ProductUtilBeans.class})
public class ProductReaderDbAdapterTest {

  @Autowired
  ProductReaderDbAdapter productReader;
  
  @Test
  @Sql("test_createProduct.sql")
  void test_findByIdOrThrow() {
    var found = productReader.findByIdOrThrow(1);
    assertEquals("trompo1", found.getName());
  }

  @Test
  @Sql("test_createProducts.sql")
  void testFindByPage() {
    final var sizeWanted = 5;
    final var pageFound = productReader.findByPage(3, sizeWanted);

    assertEquals(sizeWanted, pageFound.getContent().size());
    assertEquals(20, pageFound.getTotalElements());
    assertEquals(4, pageFound.getTotalPages());
    assertEquals("Pet Bed", pageFound.getContent().get(pageFound.getContent().size() - 5).getName());
    assertEquals("Board Game", pageFound.getContent().get(pageFound.getContent().size()-1).getName());
  }

}
