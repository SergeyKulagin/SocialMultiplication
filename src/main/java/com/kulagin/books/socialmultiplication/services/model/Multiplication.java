package com.kulagin.books.socialmultiplication.services.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true)
public class Multiplication {
  private final int a;
  private final int b;

  public int getResult() {
    return a * b;
  }
}
