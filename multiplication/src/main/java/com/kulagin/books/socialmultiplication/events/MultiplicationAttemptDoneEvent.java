package com.kulagin.books.socialmultiplication.events;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Builder
public class MultiplicationAttemptDoneEvent implements Serializable {
  private final Long userId;
  private final Long attemptId;
  private final Boolean correct;
}
