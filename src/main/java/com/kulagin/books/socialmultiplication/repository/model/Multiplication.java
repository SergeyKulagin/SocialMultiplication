package com.kulagin.books.socialmultiplication.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true)
@Entity
public class Multiplication {
  @Id
  @GeneratedValue
  @Column(name = "MULTIPLICATION_ID")
  private Long id;
  @Column(name = "A")
  private final int a;
  @Column(name = "B")
  private final int b;
}
