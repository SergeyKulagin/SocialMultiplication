package com.kulagin.books.socialmultiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import com.kulagin.books.socialmultiplication.services.dto.Multiplication;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttemptResult;
import com.kulagin.books.socialmultiplication.services.dto.User;
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

import java.util.Arrays;
import java.util.List;

import static com.kulagin.books.socialmultiplication.TestUtil.USER;

@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {
  @MockBean
  private SocialMultiplicationService multiplicationService;
  private JacksonTester<MultiplicationAttempt> postJsonRequest;
  private JacksonTester<MultiplicationAttemptResult> postJsonResponse;
  private JacksonTester<List<MultiplicationAttemptResult>> getJsonResponse;

  @Autowired
  private MockMvc mvc;

  @Before
  public void setUp() {
    // looks 'hacky'
    JacksonTester.initFields(this, new ObjectMapper());
  }


  @Test
  public void postMultiplicationResult_shouldBeOK() throws Exception {
    int a = 2, b = 3, resultCorrect = a * b;
    final MultiplicationAttempt multiplicationAttempt = new MultiplicationAttempt(new Multiplication(a, b), new User("Sergey"), resultCorrect);
    final MultiplicationAttemptResult multiplicationAttemptResult = new MultiplicationAttemptResult(
        new MultiplicationAttempt(new Multiplication(a, b), new User("Sergey"), resultCorrect),
        true // => correct
    );
    // set up behaviour
    BDDMockito
        .given(multiplicationService.checkAttempt(BDDMockito.any(MultiplicationAttempt.class)))
        .willReturn(multiplicationAttemptResult);

    //when
    MockHttpServletResponse response = mvc.perform(
        MockMvcRequestBuilders
            .post("/results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(postJsonRequest.write(multiplicationAttempt).getJson())
    )
        .andReturn()
        .getResponse();
    Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    Assertions.assertThat(response.getContentAsString()).isEqualTo(postJsonResponse.write(multiplicationAttemptResult).getJson());
  }

  @Test
  public void getMultiplicationResult_returnExpected() throws Exception {
    MultiplicationAttemptResult multiplicationAttemptResult = MultiplicationAttemptResult
        .builder()
        .multiplicationAttempt(
            MultiplicationAttempt
                .builder()
                .multiplication(
                    Multiplication
                        .builder()
                        .a(2)
                        .b(3)
                        .build()
                )
                .user(
                    User
                    .builder()
                    .alias(USER)
                    .build()
                )
                .attemptResult(6)
                .build()
        )
        .correct(true)
        .build();
    BDDMockito
        .given(multiplicationService.getLastAttempts(USER))
        .willReturn(Arrays.asList(multiplicationAttemptResult));

    MockHttpServletResponse response = mvc.perform(
        MockMvcRequestBuilders
            .get("/results")
            .param("alias", USER)
    )
        .andReturn()
        .getResponse();

    Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    Assertions.assertThat(response.getContentAsString())
        .isEqualTo(getJsonResponse.write(Arrays.asList(multiplicationAttemptResult)).getJson());
  }
}
