package com.kulagin.books.gamification;


import com.kulagin.books.gamification.domain.LeaderBoardRow;
import com.kulagin.books.gamification.repository.ScoreCardRepository;
import com.kulagin.books.gamification.service.LeaderBoardService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeaderBoardServiceTest {
  @MockBean
  private ScoreCardRepository scoreCardRepository;
  @Autowired
  private LeaderBoardService leaderBoardService;


  @Test
  public void getLeaders_shouldReturnExpected() throws Exception {
    //arrange
    BDDMockito.given(scoreCardRepository.getLeaders(BDDMockito.any())).willReturn(Arrays.asList(
        LeaderBoardRow.builder().totalScore(200L).userId(1L).build(),
        LeaderBoardRow.builder().totalScore(100L).userId(2L).build()
    ));

    // act
    List<LeaderBoardRow> leaders = leaderBoardService.getLeaders();
    Assertions.assertThat(leaders.size()).isEqualTo(2);
    Assertions.assertThat(leaders.get(0)).isEqualTo(LeaderBoardRow.builder().totalScore(200L).userId(1L).build());

  }
}
