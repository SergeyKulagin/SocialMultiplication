package com.kulagin.books.socialmultiplication.repository;

import com.kulagin.books.socialmultiplication.repository.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByAlias(String alias);
}
