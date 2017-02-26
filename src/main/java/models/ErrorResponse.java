package models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
  private String error;

  @SerializedName(value = "error_description")
  private String errorDescription;
}
