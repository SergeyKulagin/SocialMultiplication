package com.kulagin.books.socialmultiplication.repository.model;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Attempt {
  @Id
  @GeneratedValue
  @Column(name = "ATTEMPT_ID")
  private Long id;
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "USER_ID")
  private final User user;
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "MULTIPLICATION_ID")
  private final Multiplication multiplication;
  @Column(name = "IS_CORRECT")
  private final Boolean correct;
}
