package kr.co.shortenUrlService.application;

import kr.co.shortenUrlService.domain.ShortenUrlRepository;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//1. 단축 URL 생성 후 조회 테스트(통합 테스트)
//통합 테스트 진행 시에는 스프링 컨테이너가 필요하기 때문에 아래 어노테이션을 작성해야 한다
@SpringBootTest
class SimpleShortenUrlServiceTest {

  //spring 컨테이너에 등록 된 service 객체를 불러와서 연결시키는 코드
  @Autowired
  private ShortenUrlRepository shortenUrlRepository;
  @Autowired
  private SimpleShortenUrlService simpleShortenUrlService;

  @Test
  //어떤 테스트인지 명시
  @DisplayName("URL을 단축한 후 단축된 URL 키로 조회하면 원래 URL이 조회되어야 한다.")
  void setShortenUrlAddTest(){
    //given: 어떤것이 주어졌을 때
    String expectedOriginalUrl = "https://www.google.com";

    ShortenUrlCreateRequestDto shortenUrlCreateRequestDto = new ShortenUrlCreateRequestDto(expectedOriginalUrl);

    //when: 주어진 것으로 어떤 것을 했을 때
    ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
    String shortenUrlKey = shortenUrlCreateResponseDto.getShortenUrlKey();
    String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);

    //then: 기대하는 결과값이 나왔는지
    //expected url == original url
    assertThat(originalUrl).isEqualTo(expectedOriginalUrl);
    assertEquals(originalUrl, expectedOriginalUrl);
    assertTrue(originalUrl.equals(expectedOriginalUrl));
  }
}