package base;

import com.google.gson.Gson;
import exception.AuthorizationFailedException;
import lombok.Setter;
import lombok.SneakyThrows;
import models.ErrorResponse;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import java.io.IOException;

public final class RequestManager {
  private static final String AUTH_HEADER_NAME = "Authorization";

  @Setter
  private static String host = "https://api.codeswholesale.com";

  private static Retrofit retrofitInstance;

  private static Gson gson = new Gson();

  private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

  private static Retrofit.Builder builder = new Retrofit.Builder()
    .baseUrl(host)
    .addConverterFactory(GsonConverterFactory.create());

  public static <S> S createService(Class<S> serviceClass) {
    return createService(serviceClass, null);
  }

  public static <S> S createService(Class<S> serviceClass, String grantType, String clientId, String clientSecret) {
    try {
      AccessToken accessToken = OAuthTokenCredential.generateToken(grantType, clientId, clientSecret);

      return createService(serviceClass, accessToken.getTokenType() + " " + accessToken.getAccessToken());
    } catch (IOException e) {
      e.printStackTrace();
    } catch (AuthorizationFailedException e) {
      e.printStackTrace();
    }

    return createService(serviceClass, null);
  }

  public static <S> S createService(Class<S> serviceClass, String authToken) {
    AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);

    if (!httpClient.interceptors().contains(interceptor)) {
      httpClient.addInterceptor(interceptor);
      builder.client(httpClient.build());
      retrofitInstance = builder.build();
    }

    return retrofitInstance.create(serviceClass);
  }

  /**
   * Convert error body string to object
   * @param response
   * @return converted error body string
   */
  public static ErrorResponse resolveErrorBodyFromResponse(retrofit2.Response response) throws IOException {
    return gson.fromJson(response.errorBody().string(), ErrorResponse.class);
  }
}
