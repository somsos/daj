package daj.adapter.user.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ActiveProfiles("test")
public class AuthConfigTest {
  
  @Test
  void testPasswordEncoder() {
    final var encoder = new BCryptPasswordEncoder();
    final String hash = encoder.encode("mario2p");
    log.info("mario2p = " + hash);
  }
}
