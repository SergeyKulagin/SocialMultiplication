package com.kulagin.books.socialmultiplication.repository;

import com.kulagin.books.socialmultiplication.repository.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
  Optional<UserEntity> findByAlias(String alias);
}
