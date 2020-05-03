package com.kulagin.books.gamification.events;

import com.kulagin.books.gamification.service.GamificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class MultiplicationEventsListener {
  private final GamificationService gamificationService;

  @RabbitListener(queues = "${amqp.multiplication.gamification.queue.name}")
  void handleMultiplicationAttemptEvent(MultiplicationAttemptDoneEvent event) {
    try {
      gamificationService.createNewAttempt(event.getUserId(), event.getAttemptId(), event.getCorrect());
    } catch (Exception e) {
      log.error(e);
      throw new AmqpRejectAndDontRequeueException(e);
    }
  }

}
