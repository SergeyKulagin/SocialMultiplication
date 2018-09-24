package com.kulagin.books.socialmultiplication.services.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true) // (de)serialization
public class MultiplicationAttempt {
  private final Multiplication multiplication;
  private final int attemptResult;
}
