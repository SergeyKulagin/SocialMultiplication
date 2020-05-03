package com.kulagin.books.gamification;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kulagin.books.gamification.controller.LeaderBoardController;
import com.kulagin.books.gamification.domain.LeaderBoardRow;
import com.kulagin.books.gamification.service.LeaderBoardService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LeaderBoardController.class)
public class LeaderBoardControllerTest {
  @MockBean
  private LeaderBoardService leaderBoardService;
  @Autowired
  MockMvc mvc;
  private JacksonTester<List<LeaderBoardRow>> json;

  @Before
  public void setUp() throws Exception {
    JacksonTester.initFields(this, new ObjectMapper());
  }

  @Test
  public void getLeaders_shouldBeOk() throws Exception {
    //arrange
    final List<LeaderBoardRow> leaders = Arrays.asList(
        LeaderBoardRow
            .builder()
            .userId(1L)
            .totalScore(100L)
            .build(),

        LeaderBoardRow
            .builder()
            .userId(2L)
            .totalScore(300L)
            .build()
    );
    BDDMockito.given(leaderBoardService.getLeaders()).willReturn(leaders);

    //act
    MockHttpServletResponse mockHttpServletResponse = mvc.perform(
        MockMvcRequestBuilders.get(
            "/leaders"
        )
    ).andReturn().getResponse();


    Assertions.assertThat(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
    Assertions.assertThat(mockHttpServletResponse.getContentAsString()).isEqualTo(json.write(leaders).getJson());
  }
}
