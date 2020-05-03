package com.kulagin.books.gamification;


import com.kulagin.books.gamification.domain.Badge;
import com.kulagin.books.gamification.domain.BadgeCard;
import com.kulagin.books.gamification.domain.MultiplicationAttemptResult;
import com.kulagin.books.gamification.domain.ScoreCard;
import com.kulagin.books.gamification.repository.BadgeCardRepository;
import com.kulagin.books.gamification.repository.ScoreCardRepository;
import com.kulagin.books.gamification.service.GamificationService;
import com.kulagin.books.gamification.service.MultiplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
// not necessary here
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration")
public class GamificationServiceTest {
  public static final long USER_ID = 1L;
  public static final long ATTEMPT_ID = 1L;
  public static final MultiplicationAttemptResult NOT_LUCKY_ATTEMPT = MultiplicationAttemptResult.builder().attemptResult(45).build();
  public static final MultiplicationAttemptResult LUCKY_ATTEMPT = MultiplicationAttemptResult.builder().attemptResult(42).build();
  @Autowired
  private GamificationService gamificationService;
  @MockBean
  private ScoreCardRepository scoreCardRepository;
  @MockBean
  private BadgeCardRepository badgeCardRepository;
  @MockBean
  private MultiplicationService multiplicationService;

  @Test
  public void incorrectAttempt_shouldHaveNoInteractions() throws Exception {
    // arrange
    BDDMockito.given(scoreCardRepository.getTotalScore(USER_ID)).willReturn(10);

    gamificationService.createNewAttempt(USER_ID, ATTEMPT_ID, false);

    BDDMockito.then(badgeCardRepository).should(Mockito.never()).save(BDDMockito.any());
    BDDMockito.then(scoreCardRepository).should(Mockito.never()).save(BDDMockito.any());
  }

  @Test
  public void firstCorrectAttempt_shouldBeRewarded() throws Exception {

    // arrange
    BDDMockito.given(scoreCardRepository.getTotalScore(USER_ID)).willReturn(0);
    BDDMockito.given(multiplicationService.getAttempt(ATTEMPT_ID)).willReturn(NOT_LUCKY_ATTEMPT);

    // act
    gamificationService.createNewAttempt(USER_ID, ATTEMPT_ID, true);

    // assert
    assertCardsPresence(Badge.FIRST_ATTEMPT);
  }

  @Test
  public void bronzeResultAttempt_shouldBeRewarded() throws Exception {
    // arrange
    final Long userId = USER_ID;
    BDDMockito.given(scoreCardRepository.getTotalScore(userId)).willReturn(12);
    BDDMockito.given(scoreCardRepository.getCorrectAttemptsCount(userId)).willReturn(Badge.BRONZE_ATTEMPTS_NUM - 1);
    BDDMockito.given(multiplicationService.getAttempt(ATTEMPT_ID)).willReturn(NOT_LUCKY_ATTEMPT);

    // act
    gamificationService.createNewAttempt(userId, ATTEMPT_ID, true);

    // assert
    assertCardsPresence(Badge.BRONZE);
  }

  @Test
  public void silverResultAttempt_shouldBeRewarded() throws Exception {
     // arrange
    final Long userId = USER_ID;
    BDDMockito.given(scoreCardRepository.getTotalScore(userId)).willReturn(12);
    BDDMockito.given(scoreCardRepository.getCorrectAttemptsCount(userId)).willReturn(Badge.SILVER_ATTEMPTS_NUM - 1);
    BDDMockito.given(multiplicationService.getAttempt(ATTEMPT_ID)).willReturn(NOT_LUCKY_ATTEMPT);

    // act
    gamificationService.createNewAttempt(userId, ATTEMPT_ID, true);

    // assert
    assertCardsPresence(Badge.SILVER);
  }

  @Test
  public void goldResultAttempt_shouldBeRewarded() throws Exception {
     // arrange
    final Long userId = USER_ID;
    BDDMockito.given(scoreCardRepository.getTotalScore(userId)).willReturn(12);
    BDDMockito.given(scoreCardRepository.getCorrectAttemptsCount(userId)).willReturn(Badge.GOLD_ATTEMPTS_NUM - 1);
    BDDMockito.given(multiplicationService.getAttempt(ATTEMPT_ID)).willReturn(NOT_LUCKY_ATTEMPT);

    // act
    gamificationService.createNewAttempt(userId, ATTEMPT_ID, true);

    // assert
    assertCardsPresence(Badge.GOLD);
  }

  @Test
  public void luckyNumber_shouldBeRewarded() throws Exception {
    BDDMockito.given(scoreCardRepository.getTotalScore(USER_ID)).willReturn(15);
    BDDMockito.given(scoreCardRepository.getCorrectAttemptsCount(USER_ID)).willReturn(Badge.BRONZE_ATTEMPTS_NUM - 5);
    BDDMockito.given(multiplicationService.getAttempt(ATTEMPT_ID)).willReturn(LUCKY_ATTEMPT);

    //act
    gamificationService.createNewAttempt(USER_ID, ATTEMPT_ID, true);

    //assert
    assertCardsPresence(Badge.LUCKY_NUMBER);

  }

  private void assertCardsPresence(Badge... badges) {
    // assert
    BDDMockito.then(scoreCardRepository).should().saveAll(
        BDDMockito.argThat((its -> {
          int idx = 0;
          Iterator<ScoreCard> scoreCardIterator = its.iterator();
          while (scoreCardIterator.hasNext()){
            ScoreCard actualScoreCard = scoreCardIterator.next();
            Badge expectedBadge = badges[idx];
            ScoreCard expectedScoreCard = ScoreCard
                    .builder()
                    .userId(USER_ID)
                    .attemptId(ATTEMPT_ID)
                    .score(expectedBadge.getScore())
                    .build();
            boolean eq = new ReflectionEquals(expectedScoreCard, "timestamp").matches(actualScoreCard);
            if(!eq){
              return false;
            }
            idx++;
          }
          return true;
        }))
    );
    BDDMockito.then(badgeCardRepository).should().saveAll(
        BDDMockito.argThat((its -> {
          int idx = 0;
          Iterator<BadgeCard> badgeCardIterator = its.iterator();
          while (badgeCardIterator.hasNext()){
            BadgeCard actualBadgeCard = badgeCardIterator.next();
            Badge expectedBadge = badges[idx];
            BadgeCard expectedBadgeCard = BadgeCard
                    .builder()
                    .userId(USER_ID)
                    .badge(expectedBadge)
                    .build();
            boolean eq = new ReflectionEquals(expectedBadgeCard, "timestamp").matches(actualBadgeCard);
            if(!eq){
              return false;
            }
            idx++;
          }
          return true;
        }))
    );
  }
}
