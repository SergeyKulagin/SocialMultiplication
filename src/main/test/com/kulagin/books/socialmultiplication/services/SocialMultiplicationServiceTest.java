package com.kulagin.books.socialmultiplication.services;

import com.kulagin.books.socialmultiplication.services.model.Multiplication;
import com.kulagin.books.socialmultiplication.services.model.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.model.MultiplicationAttemptResult;
import com.kulagin.books.socialmultiplication.services.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SocialMultiplicationServiceTest {
  @MockBean
  private RandomNumberService randomNumberService;
  @Autowired
  private SocialMultiplicationService socialMultiplicationService;

  @Test
  public void getSocialMultiplication_shouldReturnExpected(){
    //set up mocks
    given(randomNumberService.getRandomInt()).willReturn(2,3);

    //when
    Multiplication multiplication = socialMultiplicationService.getRandomMultiplication();

    //then
    assertThat(multiplication.getA()).isEqualTo(2);
    assertThat(multiplication.getB()).isEqualTo(3);
    assertThat(multiplication.getResult()).isEqualTo(6);
  }

  @Test
  public void checkResult_shouldSuccess(){
    int a = 3, b = 2, correctResult = a * b;
    final MultiplicationAttempt attempt = new MultiplicationAttempt(new Multiplication(a,b),new User("Sergey"), correctResult);
    MultiplicationAttemptResult multiplicationAttemptResult = socialMultiplicationService.checkAttempt(attempt);
    Assertions.assertThat(multiplicationAttemptResult.isCorrect()).isEqualTo(true);
  }

}
