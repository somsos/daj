package daj.adapter.user.outDB;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.test.context.jdbc.Sql;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import daj.adapter.user.outDB.entity.RoleEntity;

@DataJpaTest
@Import({UserWriterDb.class, RoleEntity.class})
@ActiveProfiles("test")
public class UserWriterDbTest_H2 {

  @Autowired
  UserWriterDb userOP;

  @Autowired
  private UserRepository repo;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  @Sql("zzz_test_createUser.sql")
  void testDelete_mustJustMarkTheUserAsDeleted() {
    var mustExist = repo.findById(1).orElse(null);
    assertEquals(1, mustExist.getId());
    repo.deleteById(1);

    var mustExistMarkedAsDeleted = jdbcTemplate.queryForObject("select id from users;", Integer.class);
    assertEquals(Integer.valueOf(1), mustExistMarkedAsDeleted);

    var mustBeNull = repo.findById(1).orElse(null);
    assertEquals(null, mustBeNull);
  }

}
