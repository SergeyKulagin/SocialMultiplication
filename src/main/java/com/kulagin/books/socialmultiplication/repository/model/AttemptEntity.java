package com.kulagin.books.socialmultiplication.repository.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class AttemptEntity {
  @Id
  @GeneratedValue
  @Column(name = "ATTEMPT_ID")
  private Long id;
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "USER_ID")
  private final UserEntity user;
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "MULTIPLICATION_ID")
  private final MultiplicationEntity multiplication;
  @Column(name = "IS_CORRECT")
  private final Boolean correct;
  @Column(name = "ATTEMPT_RESULT")
  private final int attemptResult;
  @Column(name = "ATTEMPT_DATE")
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date date = new Date();
}
