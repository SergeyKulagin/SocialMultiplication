package com.kulagin.books.socialmultiplication.services.impl;

import com.kulagin.books.socialmultiplication.services.RandomNumberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomNumberServerImpl implements RandomNumberService{
  private final Random random = new Random();
  @Value("${service.random.up}")
  private Integer randomUpBound;

  @Override
  public int getRandomInt() {
    return random.nextInt(randomUpBound) + 1;
  }
}
