package com.kulagin.books.socialmultiplication.controller;

import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttemptResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@Controller + @ResponseBody
@RequestMapping("/results")
@RequiredArgsConstructor
public class MultiplicationResultAttemptController {
  private final SocialMultiplicationService multiplicationService;

  @PostMapping
  MultiplicationAttemptResult checkResult(@RequestBody MultiplicationAttempt multiplicationAttempt) {
    return multiplicationService.checkAttempt(multiplicationAttempt);
  }

  @GetMapping
  List<MultiplicationAttemptResult> getResults(@RequestParam("alias") String userAlias){
    return multiplicationService.getLastAttempts(userAlias);
  }
}
