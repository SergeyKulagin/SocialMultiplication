package com.kulagin.books.socialmultiplication.services.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true) // (de)serialization
@Builder
public class MultiplicationAttempt {
  private final Multiplication multiplication;
  private final User user;
  private final int attemptResult;
}
