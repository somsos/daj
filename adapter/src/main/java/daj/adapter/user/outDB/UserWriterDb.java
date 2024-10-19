package daj.adapter.user.outDB;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import daj.adapter.user.outDB.entity.RoleEntity;
import daj.adapter.user.outDB.entity.UserEntity;
import daj.adapter.user.outDB.repository.RoleRepository;
import daj.user.port.in.dto.RegisterRDto;
import daj.user.port.in.dto.RegisterRrDto;
import daj.user.port.out.IUserWriterOutputPort;
import daj.user.port.out.dto.UserRole;
import lombok.AllArgsConstructor;

@Primary
@Component
@AllArgsConstructor
public class UserWriterDb implements IUserWriterOutputPort {

  private final UserRepository repo;

  private final RoleRepository roleRepo;

  @Override
  public RegisterRrDto register(RegisterRDto input, List<UserRole> roles) {
    final var toSave = this._registerRDtoToEntity(input, roles);
    final var validRoles = getValidRoles(roles);
    toSave.setRoles(validRoles);

    final var saved = repo.save(toSave);
    final var output = _entityToRegisterRDto(saved);
    return output;
    
  }

  private List<RoleEntity> getValidRoles(List<UserRole> rolesToAddToUser) {
    final List<Integer> ids = rolesToAddToUser.stream().map(r -> r.getId()).toList();
    final List<RoleEntity> validRoles = (List<RoleEntity>) this.roleRepo.findAllById(ids);
    return validRoles;
  }

  private UserEntity _registerRDtoToEntity(RegisterRDto source, List<UserRole> roles) {
    final var mapped = new UserEntity();
    mapped.setUsername(source.getUsername());
    mapped.setPassword(source.getPassword());
    mapped.setEmail(source.getEmail());
    
    final var rolesMapped = roles.stream()
      .map(r -> new RoleEntity(r.getId(), r.getAuthority(), null))
      .toList();
    mapped.setRoles(rolesMapped);

    return mapped;
  }

  private RegisterRrDto _entityToRegisterRDto(UserEntity source) {
    final List<UserRole> rolesMapped = source.getRoles().stream()
      .map(r -> new UserRole(r.getId(), r.getAuthority()))
      .toList();
    final var mapped = new RegisterRrDto(source.getId(), rolesMapped);
    return mapped;
  }
  
}
