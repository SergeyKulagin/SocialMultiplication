package com.kulagin.books.socialmultiplication.services.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor(force = true)
@Builder
public class Multiplication {
  private final int a;
  private final int b;

  public int getResult() {
    return a * b;
  }
}
