package daj.adapter.user.outDB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import daj.adapter.user.outDB.entity.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

  UserEntity findByUsername(String username);
  
}
