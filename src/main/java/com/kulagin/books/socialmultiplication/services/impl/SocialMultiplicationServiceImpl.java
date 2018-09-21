package com.kulagin.books.socialmultiplication.services.impl;

import com.kulagin.books.socialmultiplication.services.Multiplication;
import com.kulagin.books.socialmultiplication.services.RandomNumberService;
import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialMultiplicationServiceImpl implements SocialMultiplicationService {
  private final RandomNumberService randomNumberService;

  @Override
  public Multiplication getRandomMultiplication() {
    final int a = randomNumberService.getRandomInt();
    final int b = randomNumberService.getRandomInt();
    return new Multiplication(a, b);
  }
}
