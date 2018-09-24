package com.kulagin.books.socialmultiplication.services.impl;

import com.kulagin.books.socialmultiplication.services.RandomNumberService;
import org.springframework.stereotype.Service;

@Service
public class RandomNumberServerImpl implements RandomNumberService{
  @Override
  public int getRandomInt() {
    return 5;
  }
}
