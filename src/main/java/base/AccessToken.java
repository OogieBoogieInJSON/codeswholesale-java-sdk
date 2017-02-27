package base;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccessToken {
  @SerializedName(value = "access_token")
  private String accessToken;

  @SerializedName(value = "token_type")
  private String tokenType;

  @SerializedName(value = "expires_in")
  private long expiresIn = 0;

  public boolean hasExpired() {
    return (new java.util.Date().getTime() / 1000 - expiresIn) < expiresIn;
  }
}
