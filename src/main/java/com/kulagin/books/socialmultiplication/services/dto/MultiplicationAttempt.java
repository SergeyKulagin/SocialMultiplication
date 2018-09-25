package com.kulagin.books.socialmultiplication.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true) // (de)serialization
public class MultiplicationAttempt {
  private final Multiplication multiplication;
  private final User user;
  private final int attemptResult;
}
