package exception;

import lombok.Getter;
import models.ErrorResponse;

@Getter
public class ErrorResponseException extends Exception {
  private ErrorResponse errorResponse;

  public ErrorResponseException(String message, ErrorResponse errorResponse) {
    super(message);

    this.errorResponse = errorResponse;
  }
}
