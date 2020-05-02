package com.kulagin.books.socialmultiplication.services.dto;

import lombok.*;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true) // (de)serialization
@Builder
@EqualsAndHashCode
public class MultiplicationAttempt {
  private final Multiplication multiplication;
  private final User user;
  private final int attemptResult;
}
