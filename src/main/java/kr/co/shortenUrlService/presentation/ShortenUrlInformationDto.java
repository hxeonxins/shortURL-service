package kr.co.shortenUrlService.presentation;

import kr.co.shortenUrlService.domain.ShortenUrl;

public class ShortenUrlInformationDto {
  private String originalUrl;
  private String shortenUrlKey;
  private Long redirectCount;

  public ShortenUrlInformationDto(ShortenUrl shortenUrl) {
    this.originalUrl = shortenUrl.getOriginalUrl();
    this.shortenUrlKey = shortenUrl.getShortenUrlKey();
    this.redirectCount = shortenUrl.getRedirectCount();
  }

  //JSON으로 바뀌려면 Getter가 필요하다
  public String getOriginalUrl() {
    return originalUrl;
  }

  public String getShortenUrlKey() {
    return shortenUrlKey;
  }

  public Long getRedirectCount() {
    return redirectCount;
  }
}
