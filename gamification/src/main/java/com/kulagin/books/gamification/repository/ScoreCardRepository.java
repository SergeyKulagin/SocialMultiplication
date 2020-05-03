package com.kulagin.books.gamification.repository;

import com.kulagin.books.gamification.domain.LeaderBoardRow;
import com.kulagin.books.gamification.domain.ScoreCard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {
  @Query("select sum(sc.score) from ScoreCard sc where sc.userId = :userId")
  Integer getTotalScore(@Param("userId") Long userId);

  @Query("select count(sc) from ScoreCard sc where sc.userId = :userId")
  Integer getCorrectAttemptsCount(@Param("userId") Long userId);

  @Query("select new com.kulagin.books.gamification.domain.LeaderBoardRow(sc.userId, sum(sc.score)) from ScoreCard sc group by sc.userId order by sum(sc.score) desc")
  List<LeaderBoardRow> getLeaders(Pageable pageable);
}