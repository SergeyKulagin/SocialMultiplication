package com.kulagin.books.socialmultiplication.repository.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Entity
@EqualsAndHashCode
@Builder
public class UserEntity {
  @Id
  @GeneratedValue
  @Column(name = "USER_ID")
  private Long id;
  @Column(name = "ALIAS")
  private final String alias;


}
