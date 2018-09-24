package com.kulagin.books.socialmultiplication.services;

import com.kulagin.books.socialmultiplication.services.model.Multiplication;
import com.kulagin.books.socialmultiplication.services.model.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.model.MultiplicationAttemptResult;

public interface SocialMultiplicationService {
    Multiplication getRandomMultiplication();
    MultiplicationAttemptResult checkAttempt(MultiplicationAttempt attempt);
}
