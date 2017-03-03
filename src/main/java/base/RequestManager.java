package base;

import com.google.gson.Gson;
import exception.AuthenticationFailedException;
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

  /**
   * Creates an unauthenticated client which is used for authentication
   * @param serviceClass
   * @param <S>
   * @return
     */
  public static <S> S createUnauthenticatedService(Class<S> serviceClass) {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(host).addConverterFactory(GsonConverterFactory.create());

    httpClient.addInterceptor(new ErrorInterceptor());
    builder.client(httpClient.build());

    return builder.build().create(serviceClass);
  }

  /**
   * Check if client is authenticated or not. Try to authenticate or create the authenticated service
   * @param serviceClass
   * @param <S>
   * @return
   * @throws IOException
   * @throws AuthenticationFailedException
     */
  public static <S> S createService(Class<S> serviceClass) throws IOException, AuthenticationFailedException {
    if (!isAuthenticated) {
      accessToken = authenticate(apiContext);
    }

    return createAuthenticatedService(serviceClass);
  }

  public static AccessToken authenticate(APIContext apiContext) throws AuthenticationFailedException, IOException {
    AccessToken accessToken = OAuthTokenCredential.generateToken(apiContext);

    isAuthenticated = true;

    return accessToken;
  }

  /**
   * Create an authenticated client with additional interceptors
   * @param serviceClass
   * @param <S>
   * @return a new client instance for desired class that is used for sending requests
     */
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
   * Convert error body string from response into an ErrorResponse object
   * @param response
   * @return converted error body string
   */
  public static ErrorResponse resolveErrorBodyFromResponse(retrofit2.Response response) throws IOException {
    return gson.fromJson(response.errorBody().string(), ErrorResponse.class);
  }

  /**
   * Convert error body string into an ErrorResponse object
   * @param body
   * @return converted error body string
     */
  public static ErrorResponse resolveErrorBodyFromJsonString(String body) {
    return gson.fromJson(body, ErrorResponse.class);
  }
}
