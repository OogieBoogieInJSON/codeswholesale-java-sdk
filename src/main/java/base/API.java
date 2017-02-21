package base;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Retrofit;

import java.io.IOException;

@Getter
@Setter
public class API {
  private APIContext apiContext;
  private AccessToken accessToken;

  private Retrofit requestManager;

  /**
   * default constructor
   * @constructor
   * @param apiContext
   */
  public API(APIContext apiContext) {
    this.apiContext = apiContext;

    RequestManager.setHost(apiContext.getHost());
    requestManager = RequestManager.getInstance();
  }

  public void authorize() {
    try {
      accessToken = OAuthTokenCredential.generateToken(apiContext);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean isTokenRegenerationNeeded() {
    return accessToken.getExpiresIn() >= 0;
  }
}
