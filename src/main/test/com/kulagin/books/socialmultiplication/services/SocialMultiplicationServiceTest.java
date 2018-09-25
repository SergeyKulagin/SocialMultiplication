package com.kulagin.books.socialmultiplication.services;

import com.kulagin.books.socialmultiplication.repository.AttemptRepository;
import com.kulagin.books.socialmultiplication.repository.UserRepository;
import com.kulagin.books.socialmultiplication.repository.model.Attempt;
import com.kulagin.books.socialmultiplication.services.dto.Multiplication;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttemptResult;
import com.kulagin.books.socialmultiplication.services.dto.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SocialMultiplicationServiceTest {
  @MockBean
  private RandomNumberService randomNumberService;
  @MockBean
  private AttemptRepository attemptRepository;
  @MockBean
  private UserRepository userRepository;
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
  public void checkResult_shouldBeCorrect(){
     checkResult_generic(true);
  }

  @Test
  public void checkResult_shouldBeWrong(){
    checkResult_generic(false);
  }

  private void checkResult_generic(boolean correct){
    int a = 3, b = 2, correctResult = a * b;
    final MultiplicationAttempt attempt = new MultiplicationAttempt(new Multiplication(a,b),new User("Sergey"), correct ? correctResult : correctResult + 100);
    MultiplicationAttemptResult multiplicationAttemptResult = socialMultiplicationService.checkAttempt(attempt);
    Assertions.assertThat(multiplicationAttemptResult.isCorrect()).isEqualTo(correct);
    Attempt expectedAttempt= new Attempt(
        new com.kulagin.books.socialmultiplication.repository.model.User("Sergey"),
        new com.kulagin.books.socialmultiplication.repository.model.Multiplication(a, b),
        correct
    );
    verify(attemptRepository).save(expectedAttempt);
  }

}
