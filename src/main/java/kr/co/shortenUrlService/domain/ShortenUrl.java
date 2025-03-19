package kr.co.shortenUrlService.domain;

import java.util.Random;

public class ShortenUrl {

  private String originalUrl; //원래 url 저장
  private String shortenedUrlKey; //짧은 url 저장
  private Long redirectCount; //몇 번 생성했는지 저장

  public ShortenUrl(String originalUrl, String shortenUrlKey) {
    this.originalUrl = originalUrl;
    this.shortenedUrlKey = shortenUrlKey;
    this.redirectCount = 0L; //선택사항
  }

  public static String generateShortenedUrl() {
    String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
    Random random = new Random();
    StringBuilder shortenedUrlKey = new StringBuilder(); //StringBuilder 는 append가 됨

    for (int i = 0; i < 8; i++){
      char base56Character = base56Characters.charAt(random.nextInt(base56Characters.length()));//0부터 인덱스 길이만큼 저장
      shortenedUrlKey.append(base56Character);
    }
    return shortenedUrlKey.toString();
  }

  public String getShortenUrlKey() {
    return shortenedUrlKey;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public Long getRedirectCount() {
    return redirectCount;
  }
}