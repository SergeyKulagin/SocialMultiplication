package com.kulagin.books.socialmultiplication.repository;

import com.kulagin.books.socialmultiplication.repository.model.MultiplicationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MultiplicationRepository extends CrudRepository<MultiplicationEntity, Long> {
  Optional<MultiplicationEntity> findByAAndB(int a, int b);
}
