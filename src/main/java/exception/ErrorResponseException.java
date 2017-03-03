package exception;

import lombok.Getter;
import models.ErrorResponse;

@Getter
/**
 * Generic error response exception
 */
public class ErrorResponseException extends Exception {
  /**
   * Throwable holding an ErrorResponse object
   */
  private ErrorResponse errorResponse;

  public ErrorResponseException(String message, ErrorResponse errorResponse) {
    super(message);

    this.errorResponse = errorResponse;
  }
}
