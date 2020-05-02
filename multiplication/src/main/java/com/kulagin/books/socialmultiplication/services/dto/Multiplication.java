package com.kulagin.books.socialmultiplication.services.dto;

import lombok.*;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true)
@Builder
@EqualsAndHashCode
public class Multiplication {
  private final int a;
  private final int b;

  public int getResult() {
    return a * b;
  }
}
