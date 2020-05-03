package com.kulagin.books.gamification.domain;

import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
@Builder
@Entity
public class BadgeCard {
  @Id
  @EqualsAndHashCode.Exclude
  @GeneratedValue
  @Column(name = "BADGE_ID")
  private Long badgeId;
  @Column(name = "BADGE_TYPE")
  @Enumerated(value = EnumType.STRING)
  private final Badge badge;
  @Column(name = "USER_ID")
  private final Long userId;
  @Column(name = "TIMESTAMP")
  private final Long timestamp;
}
