package com.kulagin.books.socialmultiplication.services;

import com.kulagin.books.socialmultiplication.services.dto.Multiplication;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttemptResult;

import java.util.List;

public interface SocialMultiplicationService {
    Multiplication getRandomMultiplication();
    MultiplicationAttemptResult checkAttempt(MultiplicationAttempt attempt);
    List<MultiplicationAttemptResult> getLastAttempts(String userAlias);
}
