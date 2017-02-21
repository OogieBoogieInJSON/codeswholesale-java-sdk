package base;

import api.Auth;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

//https://sandbox.codeswholesale.com
//17f0372c6d8f7ba95180731604ab74c1
//$2a$10$CUYhGrzZRYMMTgpmgVc5NOi7us/5YRx1BMXzB1nd99ApX1ocWSw3e
public final class OAuthTokenCredential {
  public static AccessToken generateToken(APIContext apiContext) throws IOException {
    Auth accessTokenRequestFactory = RequestManager.getInstance().create(Auth.class);
    Call<AccessToken> accessTokenCall = accessTokenRequestFactory.generateOAuthToken(apiContext.getGrantType(), apiContext.getClientId(), apiContext.getClientSecret());

    try {
      Response x = accessTokenCall.execute();
      AccessToken accessTokenResponse = (AccessToken) x.body();

      return accessTokenResponse;
    } catch (IOException e) {
      throw e;
    }
  }
}
