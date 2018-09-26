package com.kulagin.books.socialmultiplication.services.dto;


import lombok.*;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@EqualsAndHashCode
public class MultiplicationAttemptResult {
  private final MultiplicationAttempt multiplicationAttempt;
  private final boolean correct;
}
