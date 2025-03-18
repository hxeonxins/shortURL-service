package kr.co.shortenUrlService.presentation;

public class ShortenUrlInformationDto {
  private String originalUrl;
  private String shortenUrlKey;
  private Long redirectCount;

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
