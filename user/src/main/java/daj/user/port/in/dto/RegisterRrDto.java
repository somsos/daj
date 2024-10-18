package daj.user.port.in.dto;

import java.util.List;

import daj.user.port.out.dto.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class RegisterRrDto {

  final private Integer id;

  final private List<UserRole> roles;
  
}
