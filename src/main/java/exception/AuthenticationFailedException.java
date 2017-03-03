package exception;

/**
 * Exception thrown when the client failed to authenticate
 */
public class AuthenticationFailedException extends Exception {
  private String message;

  public AuthenticationFailedException(String message) {
    super(message);

    this.message = message;
  }

  public String getError() {
    return message;
  }
}
