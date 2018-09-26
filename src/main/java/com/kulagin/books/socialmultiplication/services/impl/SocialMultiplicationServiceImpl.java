package com.kulagin.books.socialmultiplication.services.impl;

import com.kulagin.books.socialmultiplication.repository.AttemptRepository;
import com.kulagin.books.socialmultiplication.repository.MultiplicationRepository;
import com.kulagin.books.socialmultiplication.repository.UserRepository;
import com.kulagin.books.socialmultiplication.repository.model.AttemptEntity;
import com.kulagin.books.socialmultiplication.repository.model.MultiplicationEntity;
import com.kulagin.books.socialmultiplication.repository.model.UserEntity;
import com.kulagin.books.socialmultiplication.services.RandomNumberService;
import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import com.kulagin.books.socialmultiplication.services.dto.Multiplication;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttemptResult;
import com.kulagin.books.socialmultiplication.services.dto.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SocialMultiplicationServiceImpl implements SocialMultiplicationService {
  private final RandomNumberService randomNumberService;
  private final UserRepository userRepository;
  private final AttemptRepository attemptRepository;
  private final MultiplicationRepository multiplicationRepository;
  private final ModelMapper modelMapper;

  @Override
  public Multiplication getRandomMultiplication() {
    final int a = randomNumberService.getRandomInt();
    final int b = randomNumberService.getRandomInt();
    return new Multiplication(a, b);
  }

  @Override
  public MultiplicationAttemptResult checkAttempt(MultiplicationAttempt attempt) {
    final boolean correct = (attempt.getMultiplication().getA() * attempt.getMultiplication().getB() == attempt.getAttemptResult());

    final Optional<MultiplicationEntity> multiplicationOptional = multiplicationRepository.findByAAndB(
        attempt.getMultiplication().getA(),
        attempt.getMultiplication().getB()
    );
    final MultiplicationEntity multiplication =
        multiplicationOptional.orElse(new MultiplicationEntity(
        attempt.getMultiplication().getA(),
        attempt.getMultiplication().getB()
    ));
    final Optional<UserEntity> userOptional = userRepository.findByAlias(attempt.getUser().getAlias());
    final UserEntity user = userOptional.orElse(new UserEntity(attempt.getUser().getAlias()));
    final AttemptEntity attemptModel = new AttemptEntity(
        user,
        multiplication,
        correct
    );
    attemptRepository.save(attemptModel);
    return new MultiplicationAttemptResult(attempt, correct);
  }

  @Override
  public List<MultiplicationAttemptResult> getLastAttempts(String userAlias) {
    Optional<UserEntity> userEntityOptional = userRepository.findByAlias(userAlias);
    if(!userEntityOptional.isPresent()) {
      return Collections.emptyList();
    }
    final List<AttemptEntity> multiplicationEntities = attemptRepository.findLastAttempts(userEntityOptional.get(), PageRequest.of(0, 5));
    final List<MultiplicationAttemptResult> multiplicationAttemptResults = new ArrayList<>();
    for (AttemptEntity multiplicationEntity : multiplicationEntities) {
      multiplicationAttemptResults.add(
          MultiplicationAttemptResult
              .builder()
              .correct(multiplicationEntity.getCorrect())
              .multiplicationAttempt(
                  MultiplicationAttempt
                      .builder()
                      .attemptResult(-1)
                      .user(new User(multiplicationEntity.getUser().getAlias()))
                      .multiplication(Multiplication.builder()
                          .a(multiplicationEntity.getMultiplication().getA())
                          .b(multiplicationEntity.getMultiplication().getB())
                          .build()
                      )
                      .build()
              )
              .build()
      );
    }
    return multiplicationAttemptResults;
  }
}
