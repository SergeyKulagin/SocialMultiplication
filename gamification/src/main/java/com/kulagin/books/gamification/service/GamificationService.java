package com.kulagin.books.gamification.service;

import com.kulagin.books.gamification.domain.GameStats;

public interface GamificationService {
  GameStats createNewAttempt(Long userId, Long attemptId, boolean correct);
}
