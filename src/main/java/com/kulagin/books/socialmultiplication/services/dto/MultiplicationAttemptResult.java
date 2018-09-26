package com.kulagin.books.socialmultiplication.services.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class MultiplicationAttemptResult {
  private final MultiplicationAttempt multiplicationAttempt;
  private final boolean correct;
}
