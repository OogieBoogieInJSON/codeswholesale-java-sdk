package exception;

public class AuthorizationFailedException extends Exception {
  private String message;

  public AuthorizationFailedException(String message) {
    super(message);

    this.message = message;
  }

  public String getError() {
    return message;
  }
}
