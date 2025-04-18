package kr.co.shortenUrlService.presentation;

import kr.co.shortenUrlService.domain.LackOfShortenUrlKeyException;
import kr.co.shortenUrlService.domain.NotFoundShortenUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(NotFoundShortenUrlException.class)
  public ResponseEntity<?> handleNotFoundShortenUrlException(
          NotFoundShortenUrlException e
  ) {
    return new ResponseEntity<>("shortenUrl was not found : ", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(LackOfShortenUrlKeyException.class)
  public ResponseEntity<?> handleLackOfShortenUrlKeyException(
    LackOfShortenUrlKeyException e
  ){
    return  new ResponseEntity<>("Lack Of shorten Url: ", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
