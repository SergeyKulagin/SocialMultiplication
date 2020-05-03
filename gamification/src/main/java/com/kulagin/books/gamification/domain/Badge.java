package com.kulagin.books.gamification.domain;

public enum Badge {
  BRONZE(30),
  SILVER(40),
  GOLD(50),
  FIRST_ATTEMPT(20),
  LUCKY_NUMBER(100);

  public final static int
      BRONZE_ATTEMPTS_NUM = 10,
      SILVER_ATTEMPTS_NUM = 20,
      GOLD_ATTEMPTS_NUM = 30;

  private final int score;

  Badge(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }
}
