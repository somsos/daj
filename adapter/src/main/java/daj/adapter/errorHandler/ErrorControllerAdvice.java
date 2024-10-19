package daj.adapter.errorHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
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

import daj.adapter.user.outDB.utils.ErrorConstrainToUserMsg;
import daj.common.error.ErrorResponse;
import daj.common.error.ErrorResponseBody;

@ControllerAdvice
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ErrorResponse.class)
  public ResponseEntity<ErrorResponseBody> handleErrorResponse(ErrorResponse ex, WebRequest request) {
    var headers = new HttpHeaders();
    var status = HttpStatus.valueOf(ex.getHttpStatus());
    return new ResponseEntity<>(ex.getBody(), headers, status);
  }

  @Override
  @SuppressWarnings("null")
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });
    return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
  }

  @SuppressWarnings("null")
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponseBody> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
    final var userError = ErrorConstrainToUserMsg.getUserMsg(ex);

    var headers = new HttpHeaders();
    var status = HttpStatus.valueOf(userError.getHttpStatus());
    return new ResponseEntity<>(userError.getBody(), headers, status);
  }

}



