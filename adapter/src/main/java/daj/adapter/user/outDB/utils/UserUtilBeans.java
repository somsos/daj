package daj.adapter.user.outDB.utils;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUtilBeans {

  @Bean
  public IUserMapper userMapper() {
    return Mappers.getMapper(IUserMapper.class);
  }
  
}
