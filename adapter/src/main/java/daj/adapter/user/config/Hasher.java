package daj.adapter.user.config;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import daj.user.service.IHasher;

@Primary
@Component
public class Hasher implements IHasher {

  private final static BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

  @Override
  public String encode(String rawPassword) {
    return passEncoder.encode(rawPassword);
  }

  @Override
  public boolean matches(String rawPassword, String encodedPassword) {
    return passEncoder.matches(rawPassword, encodedPassword);
  }
  
}
