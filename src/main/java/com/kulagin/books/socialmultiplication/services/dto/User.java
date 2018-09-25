package com.kulagin.books.socialmultiplication.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class User {
  @Column(name = "ALIAS")
  private final String alias;
}
