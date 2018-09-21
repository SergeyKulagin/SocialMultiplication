package com.kulagin.books.socialmultiplication.services;

import org.junit.Test;
import org.junit.runner.RunWith;
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
  @MockBean
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

}
