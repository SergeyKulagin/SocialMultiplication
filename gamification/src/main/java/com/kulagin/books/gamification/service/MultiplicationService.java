package com.kulagin.books.gamification.service;

import com.kulagin.books.gamification.domain.MultiplicationAttemptResult;

public interface MultiplicationService {
  MultiplicationAttemptResult getAttempt(Long id);
}
