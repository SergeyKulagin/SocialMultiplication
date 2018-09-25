package com.kulagin.books.socialmultiplication.repository;

import com.kulagin.books.socialmultiplication.repository.model.Multiplication;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
  Optional<Multiplication> findByAAndB(int a, int b);
}
