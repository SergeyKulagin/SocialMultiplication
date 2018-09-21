package com.kulagin.books.socialmultiplication.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Multiplication {
  private final int a;
  private final int b;

  public int getResult() {
    return a * b;
  }
}
