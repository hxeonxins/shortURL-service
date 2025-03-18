package kr.co.shortenUrlService.domain;

import kr.co.shortenUrlService.presentation.ShortenUrlInformationDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenUrlRepository {
  void saveShortenUrl(ShortenUrl shortenUrl);
  ShortenUrlInformationDto findShortenUrlByShortenUrlKey(String shortenUrlKey);
}
