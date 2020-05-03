package com.kulagin.books.gamification.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class AMQPConfiguration implements RabbitListenerConfigurer {

  @Bean
  public Queue multiplicationQueue(@Value("${amqp.multiplication.gamification.queue.name}") String queueName) {
    return new Queue(queueName, true);
  }

  @Bean
  public TopicExchange multiplicationExchange(@Value("${amqp.multiplication.exchange.name}") String exchangeName) {
    return new TopicExchange(exchangeName);
  }

  @Bean
  public Binding gamificationMultiplicationBinding(
      @Value("${amqp.multiplication.gamification.route}") String route,
      TopicExchange multiplicationExchange,
      Queue multiplicationQueue
  ){
    return BindingBuilder
        .bind(multiplicationQueue)
        .to(multiplicationExchange)
        .with(route);
  }

  @Bean
  public MappingJackson2MessageConverter messageConverter() {
    return new MappingJackson2MessageConverter();
  }

  @Bean
  public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory(){
    DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
    factory.setMessageConverter(messageConverter());
    return factory;
  }

  @Override
  public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
      rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
  }
}
