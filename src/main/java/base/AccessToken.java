package base;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessToken {
  @SerializedName(value = "access_token")
  private String accessToken;

  @SerializedName(value = "token_type")
  private String tokenType;

  @SerializedName(value = "expires_in")
  private long expiresIn = 0;

  /**
   * default constructor
   * @constructor
   * @param accessToken
   * @param tokenType
   * @param expiresIn
   */
  public AccessToken(String accessToken, String tokenType, long expiresIn) {
    this.accessToken = accessToken;
    this.tokenType = tokenType;
    this.expiresIn = expiresIn;
  }

  /**
   * Specifies how long this token can be used
   * @return remaining lifetime on token in seconds*/
  public long expiresIn() {
    return expiresIn - new java.util.Date().getTime() / 1000;
  }
}
