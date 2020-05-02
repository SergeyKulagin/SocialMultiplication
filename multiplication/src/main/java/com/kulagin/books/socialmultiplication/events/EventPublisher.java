package com.kulagin.books.socialmultiplication.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
  private final RabbitTemplate rabbitTemplate;
  private final String multiplicationExchangeName;
  private final String multiplicationAttemptDoneEventRoutingKey;

  public EventPublisher(
      RabbitTemplate rabbitTemplate,
      @Value("${amqp.multiplication.exchange.name}") String multiplicationExchangeName,
      @Value("${amqp.multiplication.attempt-done-event.routing-key}") String multiplicationAttemptDoneEventRoutingKey) {
    this.rabbitTemplate = rabbitTemplate;
    this.multiplicationExchangeName = multiplicationExchangeName;
    this.multiplicationAttemptDoneEventRoutingKey = multiplicationAttemptDoneEventRoutingKey;
  }

  public final void send(MultiplicationAttemptDoneEvent e) {
    rabbitTemplate.convertAndSend(
        multiplicationExchangeName,
        multiplicationAttemptDoneEventRoutingKey,
        e
    );
  }
}
