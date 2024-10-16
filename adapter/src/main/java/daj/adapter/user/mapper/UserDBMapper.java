package daj.adapter.user.mapper;

import org.mapstruct.Mapper;

import daj.adapter.user.outDB.entity.UserEntity;
import daj.user.port.out.dto.AuthQrDto;

@Mapper
public interface UserDBMapper {

  //@Mapping(source="id", target="idUser")
  AuthQrDto UserAuthInfoToUserAuthInfo(UserEntity source);

}
