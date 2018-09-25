package com.kulagin.books.socialmultiplication.services;

import com.kulagin.books.socialmultiplication.services.dto.Multiplication;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttemptResult;

public interface SocialMultiplicationService {
    Multiplication getRandomMultiplication();
    MultiplicationAttemptResult checkAttempt(MultiplicationAttempt attempt);
}
