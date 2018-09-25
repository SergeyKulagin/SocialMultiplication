package com.kulagin.books.socialmultiplication.services.impl;

import com.kulagin.books.socialmultiplication.repository.AttemptRepository;
import com.kulagin.books.socialmultiplication.repository.MultiplicationRepository;
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

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SocialMultiplicationServiceImpl implements SocialMultiplicationService {
  private final RandomNumberService randomNumberService;
  private final UserRepository userRepository;
  private final AttemptRepository attemptRepository;
  private final MultiplicationRepository multiplicationRepository;

  @Override
  public Multiplication getRandomMultiplication() {
    final int a = randomNumberService.getRandomInt();
    final int b = randomNumberService.getRandomInt();
    return new Multiplication(a, b);
  }

  @Override
  public MultiplicationAttemptResult checkAttempt(MultiplicationAttempt attempt) {
    final boolean correct = (attempt.getMultiplication().getA() * attempt.getMultiplication().getB() == attempt.getAttemptResult());

    final Optional<com.kulagin.books.socialmultiplication.repository.model.Multiplication> multiplicationOptional = multiplicationRepository.findByAAndB(
        attempt.getMultiplication().getA(),
        attempt.getMultiplication().getB()
    );
    final com.kulagin.books.socialmultiplication.repository.model.Multiplication multiplication =
        multiplicationOptional.orElse(new com.kulagin.books.socialmultiplication.repository.model.Multiplication(
        attempt.getMultiplication().getA(),
        attempt.getMultiplication().getB()
    ));
    final Optional<User> userOptional = userRepository.findByAlias(attempt.getUser().getAlias());
    final User user = userOptional.orElse(new User(attempt.getUser().getAlias()));
    final Attempt attemptModel = new Attempt(
        user,
        multiplication,
        correct
    );
    attemptRepository.save(attemptModel);
    return new MultiplicationAttemptResult(attempt, correct);
  }
}
