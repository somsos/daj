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
  void testFindById() {
    var found = productReader.findById(1);
    assertEquals("trompo1", found.getName());
  }

}
