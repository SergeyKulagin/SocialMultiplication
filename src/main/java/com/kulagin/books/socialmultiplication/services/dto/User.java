package com.kulagin.books.socialmultiplication.services.dto;

import lombok.*;

import javax.persistence.Column;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Builder
@EqualsAndHashCode
public class User {
  @Column(name = "ALIAS")
  private final String alias;
}
