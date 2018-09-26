package com.kulagin.books.socialmultiplication.util;

import com.kulagin.books.socialmultiplication.repository.model.UserEntity;
import com.kulagin.books.socialmultiplication.services.dto.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserEntity> {
  @Override
  public UserEntity convertAB(User o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public User convertBA(UserEntity o) {
    return User
        .builder()
        .alias(o.getAlias())
        .build();
  }
}
