package daj.adapter.user.outDB;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import daj.adapter.user.outDB.entity.UserEntity;
import daj.adapter.user.outDB.repository.UserRepository;
import daj.adapter.user.outDB.utils.IUserMapper;
import daj.user.visible.port.dto.UserDto;
import daj.user.visible.port.out.IUserReaderOutputPort;
import lombok.AllArgsConstructor;

@Primary
@Component
@AllArgsConstructor
public class UserReaderDbAdapter implements IUserReaderOutputPort {

  private final UserRepository repo;

  private final IUserMapper mapper;

  @Override
  public UserDto getAuthInfoByUsername(String username) {
    final UserEntity found = repo.findByUsername(username);
  
    final UserDto foundMapped = mapper.entityToDto(found);
    return foundMapped;
  }

  @Override
  public UserDto findAuthById(Integer userId) {
    final UserEntity found = repo.findById(userId).orElse(null);
    
    final UserDto foundMapped = mapper.entityToDto(found);
    return foundMapped;
  }
  
}
