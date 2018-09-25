package com.kulagin.books.socialmultiplication.services.impl;

import com.kulagin.books.socialmultiplication.repository.AttemptRepository;
import com.kulagin.books.socialmultiplication.repository.UserRepository;
import com.kulagin.books.socialmultiplication.repository.model.Attempt;
import com.kulagin.books.socialmultiplication.repository.model.User;
import com.kulagin.books.socialmultiplication.services.RandomNumberService;
import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import com.kulagin.books.socialmultiplication.services.dto.Multiplication;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttemptResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialMultiplicationServiceImpl implements SocialMultiplicationService {
  private final RandomNumberService randomNumberService;
  private final UserRepository userRepository;
  private final AttemptRepository attemptRepository;

  @Override
  public Multiplication getRandomMultiplication() {
    final int a = randomNumberService.getRandomInt();
    final int b = randomNumberService.getRandomInt();
    return new Multiplication(a, b);
  }

  @Override
  public MultiplicationAttemptResult checkAttempt(MultiplicationAttempt attempt) {
    final boolean correct = (attempt.getMultiplication().getA() * attempt.getMultiplication().getB() == attempt.getAttemptResult());
    final com.kulagin.books.socialmultiplication.repository.model.Multiplication multiplication = new com.kulagin.books.socialmultiplication.repository.model.Multiplication(
        attempt.getMultiplication().getA(),
        attempt.getMultiplication().getB()
    );
    User user = userRepository.findByAlias(attempt.getUser().getAlias());
    user = (user == null) ? new User(attempt.getUser().getAlias()) : user;
    final Attempt attemptModel = new Attempt(
        user,
        multiplication,
        correct
    );
    attemptRepository.save(attemptModel);
    return new MultiplicationAttemptResult(attempt, correct);
  }
}
