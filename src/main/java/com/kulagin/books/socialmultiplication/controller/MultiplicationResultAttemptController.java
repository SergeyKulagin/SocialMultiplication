package com.kulagin.books.socialmultiplication.controller;

import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ResponseBody
@RequestMapping("/results")
@RequiredArgsConstructor
public class MultiplicationResultAttemptController {
  private final SocialMultiplicationService multiplicationService;


}
