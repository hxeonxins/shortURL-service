package kr.co.shortenUrlService.infrastructure;

import kr.co.shortenUrlService.domain.ShortenUrl;
import kr.co.shortenUrlService.domain.ShortenUrlRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapShortenUrlRepository implements ShortenUrlRepository {

  private Map<String, ShortenUrl> shortenUrls = new ConcurrentHashMap<>();

  @Override
  public void saveShortenUrl(ShortenUrl shortenUrl) {
    shortenUrls.put(shortenUrl.getShortenUrlKey(), shortenUrl);
  }
}