package com.kulagin.books.socialmultiplication.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
