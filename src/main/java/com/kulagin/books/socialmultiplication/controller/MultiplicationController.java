package com.kulagin.books.socialmultiplication.controller;

import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import com.kulagin.books.socialmultiplication.services.model.Multiplication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multiplications")
@RequiredArgsConstructor
public class MultiplicationController {
  private final SocialMultiplicationService multiplicationService;

  @GetMapping
  @RequestMapping("/random")
  Multiplication getRandomMultiplication(){
    return multiplicationService.getRandomMultiplication();
  }
}
