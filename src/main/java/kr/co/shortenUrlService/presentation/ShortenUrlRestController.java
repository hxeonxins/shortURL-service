package kr.co.shortenUrlService.presentation;

import jakarta.validation.Valid;
import kr.co.shortenUrlService.application.SimpleShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<?> redirectToShortenUrl() {
    return ResponseEntity.ok().body(null);
  }

  //3. url 몇번 바꿨는지에 대한 정보 조회
  @GetMapping("/shortenUrl/{shortenUrlKey}")
  public ResponseEntity<ShortenUrlInformationDto> getShortenUrlInformation(
          @PathVariable String shortenUrlKey
  ){
    ShortenUrlInformationDto shortenUrlInformationDto =
            simpleShortenUrlService.getShortenUrlInfotmationByShortenUrlKey(shortenUrlKey);
    return ResponseEntity.ok().body(null);
  }
}
