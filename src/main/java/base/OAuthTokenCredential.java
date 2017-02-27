package base;

import api.Auth;
import exception.AuthorizationFailedException;
import models.ErrorResponse;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public final class OAuthTokenCredential {
  public static AccessToken generateToken(APIContext apiContext) throws IOException, AuthorizationFailedException {
    Call<AccessToken> call = RequestManager
      .createUnauthenticatedService(Auth.class)
      .generateOAuthToken(apiContext.getGrantType(), apiContext.getClientId(), apiContext.getClientSecret());

    Response response = call.execute();

    if (response.isSuccessful()) {
      return (AccessToken) response.body();
    }

    ErrorResponse errorResponse = RequestManager.resolveErrorBodyFromResponse(response);

    throw new AuthorizationFailedException(errorResponse.getErrorDescription());
  }
}
