package base;

import api.Auth;
import com.google.gson.Gson;
import exception.AuthorizationFailedException;
import models.ErrorResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

//https://sandbox.codeswholesale.com

public final class OAuthTokenCredential {
  public static AccessToken generateToken(String grantType, String clientId, String clientSecret) throws IOException, AuthorizationFailedException {
    Call<AccessToken> call = RequestManager
      .createService(Auth.class)
      .generateOAuthToken(grantType, clientId, clientSecret);

    Response response = call.execute();

    if (response.isSuccessful()) {
      return (AccessToken) response.body();
    }

    ErrorResponse errorResponse = RequestManager.resolveErrorBodyFromResponse(response);

    throw new AuthorizationFailedException(errorResponse.getErrorDescription());
  }
}
