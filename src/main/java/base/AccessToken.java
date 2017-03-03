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
  /**
   * Unique access token
   */
  private String accessToken;

  @SerializedName(value = "token_type")
  /**
   * Type of token. It's bearer
   */
  private String tokenType;

  @SerializedName(value = "expires_in")
  /**
   * Number of seconds until the token will expire
   */
  private long expiresIn = 0;

  /**
   * Calculates the time difference between current time and token expiration time
   * @return {Boolean} returns if token expired or not
     */
  public boolean hasExpired() {
    return (new java.util.Date().getTime() / 1000 - expiresIn) < expiresIn;
  }
}
