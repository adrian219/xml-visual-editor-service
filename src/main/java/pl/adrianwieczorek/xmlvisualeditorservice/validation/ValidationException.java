package pl.adrianwieczorek.xmlvisualeditorservice.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class ValidationException extends RuntimeException {
  public ValidationException(String message) {
    super(message);
  }
}
