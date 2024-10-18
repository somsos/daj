package daj.adapter.errorHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import daj.common.error.ErrorResponse;

@ControllerAdvice
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {

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

  
  @Override
  @SuppressWarnings("null")
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {        
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) ->{
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });
    return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
  }
  
}
