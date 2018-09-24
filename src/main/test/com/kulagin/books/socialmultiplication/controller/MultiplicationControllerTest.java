package com.kulagin.books.socialmultiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kulagin.books.socialmultiplication.services.Multiplication;
import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerTest {
  @MockBean
  private SocialMultiplicationService socialMultiplicationService;
  @Autowired
  private MockMvc mvc;
  private JacksonTester<Multiplication> json;

  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
  }

  @Test
  public void getRandomMultiplication_shouldReturnExpected() throws Exception {
    // set up mock behaviour
    BDDMockito.given(socialMultiplicationService.getRandomMultiplication())
        .willReturn(new Multiplication(70, 20));


    // when
    MockHttpServletResponse response = mvc.perform(
        MockMvcRequestBuilders
            .get("/multiplications")
            .accept(MediaType.APPLICATION_JSON)
    ).andReturn()
        .getResponse();

    Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    Assertions.assertThat(response.getContentAsString()).isEqualTo(json.write(new Multiplication(70, 20)).getJson());
  }
}
