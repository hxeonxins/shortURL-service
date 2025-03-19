package kr.co.shortenUrlService.domain;

import org.springframework.stereotype.Repository;

public interface ShortenUrlRepository {
  void saveShortenUrl(ShortenUrl shortenUrl);
  ShortenUrl findShortenUrlByShortenUrlKey(String shortenUrlKey);
}
