package com.kulagin.books.gamification.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kulagin.books.gamification.util.MultiplicationAttemptResultJsonDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
@JsonDeserialize(using = MultiplicationAttemptResultJsonDeserializer.class)
public class MultiplicationAttemptResult {
  private final int attemptResult;
}
