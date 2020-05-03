package com.kulagin.books.gamification.events;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Builder
@NoArgsConstructor(force = true)
public class MultiplicationAttemptDoneEvent implements Serializable {
  private final Long userId;
  private final Long attemptId;
  private final Boolean correct;
}
