package pl.adrianwieczorek.xmlvisualeditorservice.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class InvalidUsernameOrPasswordException extends RuntimeException {
  public InvalidUsernameOrPasswordException(String message) {
    super(message);
  }
}
