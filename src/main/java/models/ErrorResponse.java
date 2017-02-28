package models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ErrorResponse {
  private String error;

  private int status;

  private long code;

  private String message;

  private String developerMessage;

  private String moreInfo;

  @SerializedName(value = "error_description")
  private String errorDescription;
}
