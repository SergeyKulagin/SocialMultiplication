package com.kulagin.books.socialmultiplication.services;

import com.kulagin.books.socialmultiplication.repository.AttemptRepository;
import com.kulagin.books.socialmultiplication.repository.MultiplicationRepository;
import com.kulagin.books.socialmultiplication.repository.UserRepository;
import com.kulagin.books.socialmultiplication.repository.model.AttemptEntity;
import com.kulagin.books.socialmultiplication.repository.model.MultiplicationEntity;
import com.kulagin.books.socialmultiplication.repository.model.UserEntity;
import com.kulagin.books.socialmultiplication.services.dto.Multiplication;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttempt;
import com.kulagin.books.socialmultiplication.services.dto.MultiplicationAttemptResult;
import com.kulagin.books.socialmultiplication.services.dto.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.kulagin.books.socialmultiplication.TestUtil.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
  @MockBean
  private MultiplicationRepository multiplicationRepository;
  @Autowired
  private SocialMultiplicationService socialMultiplicationService;


  @Test
  public void getSocialMultiplication_shouldReturnExpected() {
    //set up mocks (arrange)
    given(randomNumberService.getRandomInt()).willReturn(2, 3);

    //when (act)
    Multiplication multiplication = socialMultiplicationService.getRandomMultiplication();

    //then
    assertThat(multiplication.getA()).isEqualTo(2);
    assertThat(multiplication.getB()).isEqualTo(3);
    assertThat(multiplication.getResult()).isEqualTo(6);
  }

  @Test
  public void getLastAttempts_shouldReturnOK() {
    UserEntity user = new UserEntity(USER);
    AttemptEntity a1 = new AttemptEntity(
        user,
        new MultiplicationEntity(2, 3),
        true,
        6
    );
    AttemptEntity a2 = new AttemptEntity(
        user,
        new MultiplicationEntity(2, 5),
        true,
        11
    );
    given(attemptRepository.findLastAttempts(any(), any()))
        .willReturn(Arrays.asList(a1, a2));
    given(userRepository.findByAlias(USER)).willReturn(Optional.of(user));
    //when
    List<MultiplicationAttemptResult> attemptResult = socialMultiplicationService.getLastAttempts(USER);

    //then
    Assertions.assertThat(attemptResult.size()).isEqualTo(2);
    Assertions.assertThat(attemptResult.get(0)).isEqualTo(MultiplicationAttemptResult
        .builder()
        .correct(true)
        .multiplicationAttempt(
            MultiplicationAttempt
                .builder()
                .attemptResult(6)
                .user(new User(USER))
                .multiplication(new Multiplication(2,3))
                .build()
        )
        .build()
    );
  }

  @Test
  public void getAttempt_shouldConvertOk(){
    //arrange
    final Long attemptId = 1L;
    BDDMockito.given(attemptRepository.findById(attemptId)).willReturn(Optional.of(
        AttemptEntity
            .builder()
            .attemptResult(42)
            .correct(true)
            .date(new Date())
            .multiplication(
                MultiplicationEntity
                .builder()
                .a(7)
                .b(8)
                .build()
            )
        .user(UserEntity.builder().alias(USER).build()).build()
    ));

    //act
    MultiplicationAttemptResult attemptResult = socialMultiplicationService.getAttempt(attemptId);
    Assertions.assertThat(attemptResult).isEqualTo(
        MultiplicationAttemptResult
            .builder()
            .correct(true)
            .multiplicationAttempt(
                MultiplicationAttempt
                    .builder()
                    .multiplication(Multiplication.builder().a(7).b(8).build())
                    .user(User.builder().alias(USER).build())
                    .attemptResult(42)
                    .build()
            )
         .build()
    );
  }

  @Test
  public void checkResult_shouldBeCorrect() {
    checkResult_generic(true);
  }

  @Test
  public void checkResult_shouldBeWrong() {
    checkResult_generic(false);
  }

  private void checkResult_generic(boolean correct) {
    int a = 3, b = 2, correctResult = a * b;
    final MultiplicationAttempt attempt = new MultiplicationAttempt(new Multiplication(a, b), new User("Sergey"), correct ? correctResult : correctResult + 100);
    MultiplicationAttemptResult multiplicationAttemptResult = socialMultiplicationService.checkAttempt(attempt);
    Assertions.assertThat(multiplicationAttemptResult.isCorrect()).isEqualTo(correct);
    AttemptEntity expectedAttempt = new AttemptEntity(
        new UserEntity(USER),
        new MultiplicationEntity(a, b),
        correct,
        correct ? correctResult : correctResult + 100
    );
    verify(attemptRepository).save(expectedAttempt);
  }

}
