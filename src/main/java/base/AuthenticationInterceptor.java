package base;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {
  private final String AUTH_HEADER_NAME = "Authorization";

  private String authToken;

  public AuthenticationInterceptor(String authToken) {
    this.authToken = authToken;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request original = chain.request();

    Request.Builder builder = original.newBuilder()
      .header(AUTH_HEADER_NAME, authToken);

    Request request = builder.build();

    return chain.proceed(request);
  }
}
