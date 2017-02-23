package base;

import lombok.Setter;
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
  private static String host;

  private static AccessToken accessToken;

  private static Retrofit retrofitInstance;

  public static Retrofit getInstance() {
    synchronized (RequestManager.class) {
      if (retrofitInstance == null) {
        retrofitInstance = buildHttpClient();
      }

      return retrofitInstance;
    }
  }

  public static void addAuthorizationHeader(AccessToken _accessToken) {
    accessToken = _accessToken;

    buildAuthorizedHttpClient();
  }

  /**
   * Creates a new instance of Retrofit with a new interceptor for adding authorization header
   */
  private static void buildAuthorizedHttpClient() {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    httpClient.addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
          .header(AUTH_HEADER_NAME, accessToken.getTokenType() + " " + accessToken.getAccessToken())
          .method(original.method(), original.body())
          .build();

        return chain.proceed(request);
      }
    });

    OkHttpClient authorizedHttpClient = httpClient.build();
    retrofitInstance = new Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .client(authorizedHttpClient)
      .build();
  }

  /**
   * Create a new instance of Retrofit
   */
  private static Retrofit buildHttpClient() {
    return new Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(host)
      .build();
  }
}
