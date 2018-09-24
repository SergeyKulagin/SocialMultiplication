package com.kulagin.books.socialmultiplication.controller;

import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import com.kulagin.books.socialmultiplication.services.model.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.model.MultiplicationAttemptResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ResponseBody
@RequestMapping("/results")
@RequiredArgsConstructor
public class MultiplicationResultAttemptController {
  private final SocialMultiplicationService multiplicationService;

  @PostMapping
  MultiplicationAttemptResult checkResult(@RequestBody MultiplicationAttempt multiplicationAttempt) {
    return multiplicationService.checkAttempt(multiplicationAttempt);
  }
}
