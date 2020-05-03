package com.kulagin.books.gamification.service.impl;

import com.kulagin.books.gamification.domain.LeaderBoardRow;
import com.kulagin.books.gamification.repository.ScoreCardRepository;
import com.kulagin.books.gamification.service.LeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderBoardServiceImpl implements LeaderBoardService {
  private final ScoreCardRepository scoreCardRepository;
  
  @Override
  public List<LeaderBoardRow> getLeaders() {
    return scoreCardRepository.getLeaders(PageRequest.of(0, 10));
  }
}
