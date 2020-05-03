package com.kulagin.books.gamification.service.impl;

import com.kulagin.books.gamification.domain.MultiplicationAttemptResult;
import com.kulagin.books.gamification.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {
  private final RestTemplate restTemplate;
  private final String multiplicationServiceUrl;

  public MultiplicationServiceImpl(RestTemplate restTemplate,
                                   @Value("${multiplication-service.url}") String multiplicationServiceUrl) {
    this.restTemplate = restTemplate;
    this.multiplicationServiceUrl = multiplicationServiceUrl;
  }

  @Override
  public MultiplicationAttemptResult getAttempt(Long id) {
    final String url = UriComponentsBuilder.fromUriString(
        multiplicationServiceUrl + "/results/attempt/{id}"
    ).buildAndExpand(id).toUriString();

    ResponseEntity<MultiplicationAttemptResult> result = restTemplate.getForEntity(url, MultiplicationAttemptResult.class);
    return result.getBody();
  }
}
