package com.kulagin.books.socialmultiplication.repository.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Builder
public class MultiplicationEntity {
  @Id
  @GeneratedValue
  @Column(name = "MULTIPLICATION_ID")
  private Long id;
  @Column(name = "A")
  private final int a;
  @Column(name = "B")
  private final int b;
}
