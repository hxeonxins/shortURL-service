package kr.co.shortenUrlService.presentation;

import jakarta.validation.Valid;
import kr.co.shortenUrlService.application.SimpleShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

//컨트롤러는 서비스를 의존한다
@RestController
public class ShortenUrlRestController {
  private final SimpleShortenUrlService simpleShortenUrlService; //final 생성자 초기화 시킴(생성자 만들어 줘야 함)
  @Autowired
  public ShortenUrlRestController(SimpleShortenUrlService simpleShortenUrlService) {
    this.simpleShortenUrlService = simpleShortenUrlService;
  }

  //1. post 매핑으로 짧은 URL 생성
  @PostMapping("/shortenUrl")
  public ResponseEntity<ShortenUrlCreateResponseDto> createShortenUrl(
          @Valid @RequestBody ShortenUrlCreateRequestDto shortenUrlCreateRequestDto//URL 만 받겠다는 뜻
  ) {//중괄호 안에서 service 호출 가능
    ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
    return ResponseEntity.ok(shortenUrlCreateResponseDto);
  }

  //2. get 매핑으로 원본주소
  @GetMapping("/{shortenUrlKey}")
  public ResponseEntity<?> redirectToShortenUrl(
          @PathVariable String shortenUrlKey
  ) {
    String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);

    URI redirectUri = URI.create(originalUrl); //type캐스팅
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(redirectUri);

    return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
  }

  //3. url 몇번 redirect 했는지 정보 조회
  @GetMapping("/shortenUrl/{shortenUrlKey}")
  public ResponseEntity<ShortenUrlInformationDto> getShortenUrlInformation(
          @PathVariable String shortenUrlKey
  ){
    ShortenUrlInformationDto shortenUrlInformationDto =
            simpleShortenUrlService.getShortenUrlInformationByShortenUrlKey(shortenUrlKey);
    return ResponseEntity.ok(shortenUrlInformationDto);
  }
}
