package com.kulagin.books.gamification.service;

import com.kulagin.books.gamification.domain.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardService {
  List<LeaderBoardRow> getLeaders();
}
