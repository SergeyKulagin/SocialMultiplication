package com.kulagin.books.gamification.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    
    @GetMapping(path = "/leaders", produces = APPLICATION_JSON_VALUE)
    List<LeaderBoardRow> getLeaders() {
        return leaderBoardService.getLeaders();
    }
}
