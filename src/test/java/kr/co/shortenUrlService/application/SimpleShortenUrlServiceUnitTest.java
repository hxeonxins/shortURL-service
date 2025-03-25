package kr.co.shortenUrlService.application;

import kr.co.shortenUrlService.domain.LackOfShortenUrlKeyException;
import kr.co.shortenUrlService.domain.NotFoundShortenUrlException;
import kr.co.shortenUrlService.domain.ShortenUrlRepository;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//2. 단축 URL 생성 후 조회 테스트(단위 테스트)
//목 객체를 만들어야 한다
@ExtendWith(MockitoExtension.class)
class SimpleShortenUrlServiceUnitTest {

  //목 객체를 생성
  @Mock
  private ShortenUrlRepository shortenUrlRepository;

  //목을 주입받는 진짜 서비스 객체
  @InjectMocks
  private SimpleShortenUrlService simpleShortenUrlService;

  @Test
  //어떤 테스트인지 명시
  @DisplayName("단축 URL이 계속 중복되면 LackOfShortenUrlKeyException 예외 발생.")
  void throwLackOfShortenUrlKeyException() {
    //given: 어떤것이 주어졌을 때
    //1. 요청
    ShortenUrlCreateRequestDto shortenUrlCreateRequestDto = new ShortenUrlCreateRequestDto(null);


    //when: 주어진 것으로 어떤 것을 했을 때
    //여기에 목 객체의 수행흐름을 적어줘야 한다.
    when(shortenUrlRepository.findShortenUrlByShortenUrlKey(any())).thenReturn(null, null);


    //then: 기대하는 결과값이 나왔는지
    Assertions.assertThrows(LackOfShortenUrlKeyException.class, () -> {
      simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
    });

  }

  @Test
  @DisplayName("NotFoundException 코드")
  void throwNotFoundException() {
    //given
    String invalidShortenUrlKey = "invalidKey";

    //when
    when(shortenUrlRepository.findShortenUrlByShortenUrlKey(any())).thenReturn(null);

    //then
    Assertions.assertThrows(NotFoundShortenUrlException.class, () -> {
      simpleShortenUrlService.getOriginalUrlByShortenUrlKey(invalidShortenUrlKey);
    });
  }
}