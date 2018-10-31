package com.kulagin.books.socialmultiplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SocialMultiplicationApp {
  public static void main(String[] args) {
    SpringApplication.run(SocialMultiplicationApp.class, args);
  }
}
