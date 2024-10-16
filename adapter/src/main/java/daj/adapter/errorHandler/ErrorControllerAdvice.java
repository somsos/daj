package daj.adapter.errorHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import daj.common.error.ErrorResponse;

@ControllerAdvice
public class ErrorControllerAdvice {

  @ExceptionHandler(ErrorResponse.class)
  public ResponseEntity<Object> handleErrorResponse(ErrorResponse ex, WebRequest request) {
    
    var body = new Object(){
      @SuppressWarnings("unused")
      public String message = ex.getMessage();
      @SuppressWarnings("unused")
      public String cause = ex.getCause().getLocalizedMessage();
    };

    var headers = new HttpHeaders();
    var status = HttpStatus.valueOf(ex.getHttpStatus());
    return new ResponseEntity<>(body, headers, status);
  }

  
  
}
