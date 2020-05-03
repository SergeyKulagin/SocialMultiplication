package com.kulagin.books.gamification.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@Builder
public class GameStats {
  private final Long userId;
  private final int score;
  private final List<Badge> badges;
}
