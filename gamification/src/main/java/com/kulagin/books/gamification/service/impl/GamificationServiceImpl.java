package com.kulagin.books.gamification.service.impl;

import com.kulagin.books.gamification.domain.*;
import com.kulagin.books.gamification.repository.BadgeCardRepository;
import com.kulagin.books.gamification.repository.ScoreCardRepository;
import com.kulagin.books.gamification.service.GamificationService;
import com.kulagin.books.gamification.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class GamificationServiceImpl implements GamificationService {
  public static final int CORRECT_ATTEMPT_SCORE = 10;
  public static final int LUCKY_NUMBER = 42;
  private final MultiplicationService multiplicationService;
  private final BadgeCardRepository badgeCardRepository;
  private final ScoreCardRepository scoreCardRepository;

  @Override
  public GameStats createNewAttempt(Long userId, Long attemptId, boolean correct) {
    if (!correct) {
      return getCurrentStats(userId);
    }


    final List<BadgeCard> badgeCards = new ArrayList<>();
    final List<ScoreCard> scoreCards = new ArrayList<>();

    //1. check first attempt
    Integer totalScore = scoreCardRepository.getTotalScore(userId);
    log.info("User's score {}", totalScore);
    if (totalScore == null || totalScore == 0) {
      badgeCards.add(BadgeCard.builder()
          .badge(Badge.FIRST_ATTEMPT)
          .userId(userId)
          .timestamp(System.currentTimeMillis())
          .build());
      scoreCards.add(ScoreCard.builder()
          .userId(userId)
          .attemptId(attemptId)
          .timestamp(System.currentTimeMillis())
          .score(Badge.FIRST_ATTEMPT.getScore())
          .build());
    }

    //2. check lucky number
    if (isLuckyNumber(attemptId, correct)) {
      badgeCards.add(BadgeCard.builder()
          .badge(Badge.LUCKY_NUMBER)
          .userId(userId)
          .timestamp(System.currentTimeMillis())
          .build());
      scoreCards.add(ScoreCard.builder()
          .userId(userId)
          .attemptId(attemptId)
          .timestamp(System.currentTimeMillis())
          .score(Badge.LUCKY_NUMBER.getScore())
          .build());
    }

    //3. check the others
    if (totalScore != null && totalScore > 0) {
      Badge badge;
      final Integer correctAttemptsCount = scoreCardRepository.getCorrectAttemptsCount(userId);
      log.info("Correct attempts count {}", correctAttemptsCount);
      Assert.state(correctAttemptsCount != null && correctAttemptsCount > 0, "If the user has score he should has correct attempts!");
      if ((correctAttemptsCount + 1) % Badge.GOLD_ATTEMPTS_NUM == 0) { //
        badge = Badge.GOLD;
      } else if ((correctAttemptsCount + 1) % Badge.SILVER_ATTEMPTS_NUM == 0) {
        badge = Badge.SILVER;
      } else if ((correctAttemptsCount + 1) % Badge.BRONZE_ATTEMPTS_NUM == 0) {
        badge = Badge.BRONZE;
      } else {
        badge = null;
      }
      if (badge != null) {
        badgeCards.add(BadgeCard.builder()
            .badge(badge)
            .userId(userId)
            .timestamp(System.currentTimeMillis())
            .build());
        scoreCards.add(ScoreCard.builder()
            .userId(userId)
            .attemptId(attemptId)
            .timestamp(System.currentTimeMillis())
            .score(badge.getScore())
            .build());
      }
    }

    if (badgeCards.isEmpty()) {
      scoreCards.add(ScoreCard.builder()
          .userId(userId)
          .attemptId(attemptId)
          .timestamp(System.currentTimeMillis())
          .score(CORRECT_ATTEMPT_SCORE)
          .build());
    }

    badgeCardRepository.saveAll(badgeCards);
    scoreCardRepository.saveAll(scoreCards);

    final GameStats gameStats =  getCurrentStats(userId);
    log.info("Current stats: {}", gameStats);
    return gameStats;
  }

  private GameStats getCurrentStats(Long userId) {
    final Integer totalScore = scoreCardRepository.getTotalScore(userId);
    final List<BadgeCard> badgeCards = badgeCardRepository.getBadgeCardsByUserId(userId);

    return GameStats
        .builder()
        .userId(userId)
        .badges(badgeCards.stream().map((bc-> bc.getBadge())).collect(Collectors.toList()))
        .score(totalScore)
        .build();
  }

  private boolean isLuckyNumber(Long attemptId, boolean wasCorrect) {
    MultiplicationAttemptResult multiplicationAttemptResult = multiplicationService.getAttempt(attemptId);
    return multiplicationAttemptResult.getAttemptResult() == LUCKY_NUMBER && wasCorrect;
  }
}
