package com.kulagin.books.gamification.controller;

import com.kulagin.books.gamification.domain.LeaderBoardRow;
import com.kulagin.books.gamification.service.LeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class LeaderBoardController {
  private final LeaderBoardService leaderBoardService;

  @GetMapping("/leaders")
  List<LeaderBoardRow> getLeaders(){
    return leaderBoardService.getLeaders();
  }
}
