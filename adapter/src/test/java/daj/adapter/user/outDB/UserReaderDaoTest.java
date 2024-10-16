package daj.adapter.user.outDB;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({UserReaderDao.class})
public class UserReaderDaoTest {
  
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
    final var found = userReaderDao.findAuthById(1);

    assertNotNull(found);
    assertNotNull(found.getRoles());
  }


}
