package kr.co.shortenUrlService.application;

import kr.co.shortenUrlService.domain.ShortenUrl;
import kr.co.shortenUrlService.domain.ShortenUrlRepository;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateResponseDto;
import kr.co.shortenUrlService.presentation.ShortenUrlInformationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//서비스는 레포지토리를 의존한다.
//핵심 비즈니스 로직을 가져다 쓴다.
@Service
public class SimpleShortenUrlService {

  private final ShortenUrlRepository shortenUrlRepository;
  @Autowired
  public SimpleShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
    this.shortenUrlRepository = shortenUrlRepository;
  }

  public ShortenUrlCreateResponseDto generateShortenUrl(
          ShortenUrlCreateRequestDto shortenUrlCreateRequestDto
  ){
    //구현 사항 4가지

    //원래 URL과 단축 URL키 생성
    String originalUrl = shortenUrlCreateRequestDto.getOriginalUrl();
    String shortenUrlKey = ShortenUrl.generateShortenedUrl();

    //원래 URL과 단축 URL 키를 통해 ShortenURL 도메인 객체 생성
    ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);

    //생성된 ShortenURL을 레포지토리를 통해 저장
    shortenUrlRepository.saveShortenUrl(shortenUrl);

    //ShortenURL을 ShortenUrlCreatResponseDto로 변환하여 최종 반환
    ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = new ShortenUrlCreateResponseDto(shortenUrl);
    return shortenUrlCreateResponseDto;
  }

  public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
    ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
    ShortenUrlInformationDto shortenUrlInformationDto = new ShortenUrlInformationDto(shortenUrl);
    return shortenUrlInformationDto;
  }

  public String getOriginalUrlByShortenUrlKey(String shortenUrlKey) {
    ShortenUrl shortenUrl =  shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

    shortenUrl.setRedirectCount(shortenUrl.getRedirectCount() + 1);

    shortenUrl.getOriginalUrl();
    return null;
  }
}
