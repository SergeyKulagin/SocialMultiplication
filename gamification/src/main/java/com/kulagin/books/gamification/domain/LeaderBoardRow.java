package com.kulagin.books.gamification.domain;


import lombok.*;

@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@Getter
public class LeaderBoardRow {
  private final Long userId;
  private final Long totalScore;
}
