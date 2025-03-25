package kr.co.shortenUrlService.presentation;

import kr.co.shortenUrlService.application.SimpleShortenUrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Controller test
//단위 테스트 중 웹 통신을 테스트하는 코드
//내장 톰켓을 사용해야 하기 때문에 스프링을 띄워야 한다.
@WebMvcTest(ShortenUrlRestController.class)

//이후에 등장하는 Autowired에서 진짜를 주입시킬지 가짜를 주입시킬지 헷갈린다면 MockServiceConfig에 기반해서 주입
@Import(ShortenUrlRestControllerTest.MockServiceConfig.class)
class ShortenUrlRestControllerTest {

  @Autowired
  private MockMvc mockMvc; //가짜 목 객체: 모델 뷰 컨트롤러의 역할을 제공

  @Autowired //진짜와 가짜 중 무엇을 주입해야 할 지 스프링이 헷갈림
  private SimpleShortenUrlService simpleShortenUrlService;

  @Test
  @DisplayName("originalUrl로 리다이렉트 되어야 한다")
  void redirectTest() throws Exception {

    //given
    String expectedOriginalUrl = "http://www.google.com";

    //when
    when(simpleShortenUrlService.getOriginalUrlByShortenUrlKey(any()))
            .thenReturn(expectedOriginalUrl);
    //then
    mockMvc.perform(get("/any-key")) // path라서 슬래시를 넣어야 한다..
            .andExpect(status().isMovedPermanently())
            .andExpect(header().string("Location", expectedOriginalUrl));
  }

  //스프링을 통해서 @Autowired 를 실제 객체로 시킴 -> 스프링 컨테이너에 가짜 객체도 함께 등록
  static class MockServiceConfig {
    @Bean
    public SimpleShortenUrlService simpleShortenUrlService() {
      return mock(SimpleShortenUrlService.class);
    }
  }
}