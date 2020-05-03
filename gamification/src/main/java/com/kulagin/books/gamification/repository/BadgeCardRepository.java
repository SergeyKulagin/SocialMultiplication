package com.kulagin.books.gamification.repository;

import com.kulagin.books.gamification.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long> {
  List<BadgeCard> getBadgeCardsByUserId(@Param("userId") Long userId);
}
