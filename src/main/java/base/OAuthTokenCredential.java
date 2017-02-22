package base;

import api.Auth;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

//https://sandbox.codeswholesale.com

public final class OAuthTokenCredential {
  public static AccessToken generateToken(APIContext apiContext) throws IOException {
    Auth accessTokenRequestFactory = RequestManager.getInstance().create(Auth.class);
    Call<AccessToken> accessTokenCall = accessTokenRequestFactory.generateOAuthToken(apiContext.getGrantType(), apiContext.getClientId(), apiContext.getClientSecret());

    try {
      Response accessTokenResponse = accessTokenCall.execute();
      AccessToken accessTokenResponseBody = (AccessToken) accessTokenResponse.body();

      return accessTokenResponseBody;
    } catch (IOException e) {
      throw e;
    }
  }
}
