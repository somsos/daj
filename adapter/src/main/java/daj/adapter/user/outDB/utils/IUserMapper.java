package daj.adapter.user.outDB.utils;

import org.mapstruct.Mapper;

import daj.adapter.user.inWeb.reqAndResp.LoginRequest;
import daj.adapter.user.inWeb.reqAndResp.LoginResponse;
import daj.adapter.user.inWeb.reqAndResp.RegisterRequest;
import daj.adapter.user.inWeb.reqAndResp.RegisterResponse;
import daj.adapter.user.outDB.entity.UserEntity;
import daj.user.visible.port.dto.UserDto;
import jakarta.validation.Valid;

@Mapper
public interface IUserMapper {

  //@Mapping(source="id", target="idUser")
  UserDto entityToDto(UserEntity source);

  UserEntity dtoToEntity(UserDto source);

  UserDto loginRequestToDto(LoginRequest input);

  LoginResponse dtoToLoginResponse(UserDto logged);

  UserDto registerRequestToDto(@Valid RegisterRequest input);

  RegisterResponse dtoToRegisterResponse(UserDto registered);

}
