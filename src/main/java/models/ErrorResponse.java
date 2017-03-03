package models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
/**
 * Generic error response model
 */
public class ErrorResponse {
  /**
   * Error message
   */
  private String error;

  /**
   * HTTP status code
   */
  private int status;

  /**
   * Error code (for debugging)
   */
  private long code;

  /**
   * Another error message
   */
  private String message;

  /**
   * Developer message (usually it's the same as message)
   */
  private String developerMessage;

  /**
   * A link with informations about the error
   */
  private String moreInfo;

  @SerializedName(value = "error_description")
  /**
   * Error's description
   */
  private String errorDescription;
}
