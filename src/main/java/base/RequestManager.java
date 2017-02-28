package base;

import com.google.gson.Gson;
import exception.AuthorizationFailedException;
import lombok.Setter;
import models.ErrorResponse;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import java.io.IOException;

public final class RequestManager {
  @Setter
  private static String host = "https://api.codeswholesale.com";

  @Setter
  private static APIContext apiContext;

  @Setter
  private static AccessToken accessToken;

  private static Retrofit retrofitInstance;

  private static Gson gson = new Gson();

  private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

  private static Retrofit.Builder builder = new Retrofit.Builder()
    .baseUrl(host)
    .addConverterFactory(GsonConverterFactory.create());

  private static Boolean isAuthenticated = false;

  public static <S> S createUnauthenticatedService(Class<S> serviceClass) {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(host).addConverterFactory(GsonConverterFactory.create());

    httpClient.addInterceptor(new ErrorInterceptor());
    builder.client(httpClient.build());

    return builder.build().create(serviceClass);
  }

  public static <S> S createService(Class<S> serviceClass) throws IOException, AuthorizationFailedException {
    if (!isAuthenticated) {
      accessToken = authenticate(apiContext);
    }

    return createAuthenticatedService(serviceClass);
  }

  public static AccessToken authenticate(APIContext apiContext) throws AuthorizationFailedException, IOException {
    AccessToken accessToken = OAuthTokenCredential.generateToken(apiContext);

    isAuthenticated = true;

    return accessToken;
  }

  private static <S> S createAuthenticatedService(Class<S> serviceClass) {
    AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptor(accessToken, apiContext);
    ErrorInterceptor errorInterceptor = new ErrorInterceptor();

    if (!httpClient.interceptors().contains(errorInterceptor)) {
      httpClient.addInterceptor(errorInterceptor);
    }

    if (!httpClient.interceptors().contains(authenticationInterceptor)) {
      httpClient.addInterceptor(authenticationInterceptor);
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

  public static ErrorResponse resolveErrorBodyFromJsonString(String body) {
    return gson.fromJson(body, ErrorResponse.class);
  }
}
