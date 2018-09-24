package com.kulagin.books.socialmultiplication.services.impl;

import com.kulagin.books.socialmultiplication.services.RandomNumberService;
import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import com.kulagin.books.socialmultiplication.services.model.Multiplication;
import com.kulagin.books.socialmultiplication.services.model.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.model.MultiplicationAttemptResult;
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

  @Override
  public MultiplicationAttemptResult checkAttempt(MultiplicationAttempt attempt) {
    final boolean correct = (attempt.getMultiplication().getA() * attempt.getMultiplication().getB() == attempt.getAttemptResult());
    return new MultiplicationAttemptResult(attempt, correct);
  }
}
