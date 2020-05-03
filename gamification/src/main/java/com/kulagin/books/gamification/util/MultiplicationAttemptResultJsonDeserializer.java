package com.kulagin.books.gamification.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.kulagin.books.gamification.domain.MultiplicationAttemptResult;

import java.io.IOException;

public class MultiplicationAttemptResultJsonDeserializer extends JsonDeserializer<MultiplicationAttemptResult> {
  @Override
  public MultiplicationAttemptResult deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    final ObjectCodec codec = jsonParser.getCodec();
    final JsonNode node = codec.readTree(jsonParser);
    return MultiplicationAttemptResult
        .builder()
        .attemptResult(node.get("multiplicationAttempt").get("attemptResult").asInt())
        .build();
  }
}
