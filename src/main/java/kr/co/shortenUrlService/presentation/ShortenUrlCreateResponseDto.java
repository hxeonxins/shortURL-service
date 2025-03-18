package kr.co.shortenUrlService.presentation;

public class ShortenUrlCreateResponseDto {
  private String originalUrl;
  private String shortenUrlKey;

  public String getOriginalUrl() {
    return originalUrl;
  }

  public String getShortenUrlKey() {
    return shortenUrlKey;
  }
}
