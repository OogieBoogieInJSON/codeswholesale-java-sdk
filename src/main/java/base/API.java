package base;

import api.Products;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
public class API {
  private String authHeaderName = "Authorization";
  private String authHeaderValue;
  private APIContext apiContext;
  private AccessToken accessToken;

  private Retrofit requestManager;

  /**
   * default constructor
   * @constructor
   * @param apiContext
   */
  public API(APIContext apiContext) {
    this.apiContext = apiContext;

    RequestManager.setHost(apiContext.getHost());
    requestManager = RequestManager.getInstance();
  }

  public void authorize() {
    try {
      accessToken = OAuthTokenCredential.generateToken(apiContext);

      OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
      httpClient.addInterceptor(new Interceptor() {
        @Override
        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
          Request original = chain.request();

          Request request = original.newBuilder()
            .header(authHeaderName, accessToken.getTokenType() + " " + accessToken.getAccessToken())
            .method(original.method(), original.body())
            .build();

          return chain.proceed(request);
        }
      });

      requestManager = RequestManager.createNewWithHttpClient(httpClient);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean isTokenRegenerationNeeded() {
    return accessToken.getExpiresIn() >= 0;
  }

  public List<Products> getAllProducts() throws IOException {
    Call<List<Products>> allProductsCall = requestManager.create(Products.class).getAllProducts();

    try {
      Response allProductsResponse = allProductsCall.execute();
      List<Products> allProductsResponseBody = (List<Products>) allProductsResponse.body();

      return allProductsResponseBody;
    } catch (IOException e) {
      throw e;
    }

  }
}
