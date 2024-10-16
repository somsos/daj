package daj.adapter.user.outDB;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import daj.adapter.user.mapper.UserDBMapper;
import daj.adapter.user.outDB.entity.UserEntity;
import daj.user.port.out.IUserReaderOutputPort;
import daj.user.port.out.dto.AuthQrDto;
import lombok.AllArgsConstructor;

@Primary
@Component
@AllArgsConstructor
public class UserReaderDao implements IUserReaderOutputPort {

  private final UserRepository repo;

  private static final UserDBMapper mapper = Mappers.getMapper(UserDBMapper.class);

  @Override
  public AuthQrDto getAuthInfoByUsername(String username) {
    final UserEntity found = repo.findByUsername(username);
    final AuthQrDto foundMapped = mapper.UserAuthInfoToUserAuthInfo(found);
    return foundMapped;
  }

  @Override
  public AuthQrDto findAuthById(Integer userId) {
    final UserEntity found = repo.findById(userId).orElse(null);
    final AuthQrDto foundMapped = mapper.UserAuthInfoToUserAuthInfo(found);
    return foundMapped;
  }
  
}
