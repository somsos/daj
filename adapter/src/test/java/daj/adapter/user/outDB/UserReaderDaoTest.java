package daj.adapter.user.outDB;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({UserReaderDao.class})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserReaderDaoTest {

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
  
  @Autowired
  private DataSource dataSource;
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  private EntityManager entityManager;
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserReaderDao userReaderDao;

  @Test
  void injectedComponentsAreNotNull(){
    assertNotNull(dataSource);
    assertNotNull(jdbcTemplate);
    assertNotNull(entityManager);
    assertNotNull(userRepository);
    assertNotNull(userReaderDao);
  }

  @Test
  void check_start_import_script() {
    final var found = userReaderDao.findAuthById(-100);

    assertNotNull(found);
    assertEquals(2, found.getRoles().size());


    final var found2 = userReaderDao.findAuthById(-99);
    assertEquals(1, found2.getRoles().size());
  }


}
