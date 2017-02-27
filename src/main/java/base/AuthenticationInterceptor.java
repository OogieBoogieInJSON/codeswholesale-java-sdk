package base;

import exception.AuthorizationFailedException;
import lombok.AllArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@AllArgsConstructor
public class AuthenticationInterceptor implements Interceptor {
  private final String AUTH_HEADER_NAME = "Authorization";

  private AccessToken accessToken;

  private APIContext apiContext;

  @Override
  public Response intercept(Chain chain) throws IOException {
    if (accessToken.hasExpired()) {
      try {
        accessToken = RequestManager.authenticate(apiContext);
      } catch (AuthorizationFailedException e) {
        throw new IOException("Failed to authenticate", e);
      }
    }

    Request original = chain.request();

    Request.Builder builder = original.newBuilder()
      .header(AUTH_HEADER_NAME, accessToken.getTokenType() + " " + accessToken.getAccessToken());

    Request request = builder.build();

    return chain.proceed(request);
  }
}
