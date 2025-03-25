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

//단위 테스트
@ExtendWith(MockitoExtension.class)
public class NotFoundShortenUrlExceptionTest {

  @InjectMocks
  private SimpleShortenUrlService simpleShortenUrlService;
  @Mock
  private ShortenUrlRepository shortenUrlRepository;

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