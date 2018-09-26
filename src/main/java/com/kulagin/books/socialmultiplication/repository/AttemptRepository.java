package com.kulagin.books.socialmultiplication.repository;

import com.kulagin.books.socialmultiplication.repository.model.AttemptEntity;
import com.kulagin.books.socialmultiplication.repository.model.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttemptRepository extends CrudRepository<AttemptEntity, Long>{
  @Query("select a from Attempt a where a.user = :user order by a.date desc")
  List<AttemptEntity> findLastAttempts(@Param("user") UserEntity user, Pageable pageable);
}
