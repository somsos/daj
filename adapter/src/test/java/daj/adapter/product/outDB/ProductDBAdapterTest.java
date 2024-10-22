package daj.adapter.product.outDB;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import daj.adapter.product.utils.ProductUtilBeans;

@ActiveProfiles("test")
@DataJpaTest
@Import({ProductDBAdapter.class, ProductUtilBeans.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductDBAdapterTest {

  @Autowired
  ProductDBAdapter productDBAdapter;

  @Autowired
  ProductRepository repo;

  //Docker-----------
  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
          "postgres:14-alpine3.16"
  );

  @BeforeAll
  static void beforeAll() {
      postgres.start();
  }

  @AfterAll
  static void afterAll() {
      postgres.stop();
  }


  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", postgres::getJdbcUrl);
      registry.add("spring.datasource.username", postgres::getUsername);
      registry.add("spring.datasource.password", postgres::getPassword);
  }
  //End-Docker-----------
  
  @Test
  void testSave() {
    var input = new ProductEntity(null, "trompo1", 10.10f, 10, "description1", null);
    productDBAdapter.save(input);


    final ProductEntity found = repo.findByName(input.getName());
    assertNotNull(found.getCreatedAt());

  }
}
