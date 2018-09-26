package com.kulagin.books.socialmultiplication.util;

import com.kulagin.books.socialmultiplication.repository.model.MultiplicationEntity;
import com.kulagin.books.socialmultiplication.services.dto.Multiplication;
import org.springframework.stereotype.Component;

@Component
public class MultiplicationConverter implements Converter<Multiplication, MultiplicationEntity> {
  @Override
  public MultiplicationEntity convertAB(Multiplication o) {
    return null;
  }

  @Override
  public Multiplication convertBA(MultiplicationEntity o) {
    return Multiplication
        .builder()
        .a(o.getA())
        .b(o.getB())
        .build();
  }
}
