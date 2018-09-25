package com.kulagin.books.socialmultiplication.repository;

import com.kulagin.books.socialmultiplication.repository.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByAlias(String alias);
}
