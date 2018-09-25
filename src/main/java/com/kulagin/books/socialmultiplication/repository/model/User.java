package com.kulagin.books.socialmultiplication.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Entity
public class User {
  @Id
  @GeneratedValue
  @Column(name = "USER_ID")
  private Long id;
  @Column(name = "ALIAS")
  private final String alias;


}
