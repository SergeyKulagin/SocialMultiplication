package com.kulagin.books.socialmultiplication.util;

public interface Converter<FROM, TO> {
  TO convertAB(FROM o);
  FROM convertBA(TO o);
}
