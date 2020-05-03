package com.kulagin.books.gamification.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration {
  @Bean
  RestTemplate restTemplate(){
    return new RestTemplateBuilder().build();
  }

  @Bean
  WebMvcConfigurer webMvcConfigurer(){
      return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/leaders").allowedOrigins("*");
        }
      };
  }
}
