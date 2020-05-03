package com.kulagin.books.gamification.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode
@Entity
@Builder
@ToString
public class ScoreCard {
  @Id
  @EqualsAndHashCode.Exclude
  @GeneratedValue
  @Column(name = "CARD_ID")
  private Long cardId;
  @Column(name = "USER_ID")
  private final Long userId;
  @Column(name = "SCORE")
  private final int score;
  @Column(name = "ATTEMPT_ID")
  private final Long attemptId;
  @Column(name = "TIMESTAMP")
  private final Long timestamp;
}
