package base;

import api.Auth;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

//https://sandbox.codeswholesale.com

public final class OAuthTokenCredential {
  public static AccessToken generateToken(APIContext apiContext) throws IOException {
    Call<AccessToken> call = RequestManager
      .getInstance()
      .create(Auth.class)
      .generateOAuthToken(apiContext.getGrantType(), apiContext.getClientId(), apiContext.getClientSecret());


    try {
      Response response = call.execute();

      if (response.isSuccessful()) {
        return (AccessToken) response.body();
      }

      System.out.print(response.errorBody());
      throw new IOException("yolo");
    } catch (IOException e) {
      throw e;
    }
  }
}
