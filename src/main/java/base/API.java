package base;

import api.Account;
import api.Orders;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import resources.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

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

      if (accessToken == null) {
        return;
      }

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

  public ProductsResponse getAllProducts() throws IOException {
    Call<ProductsResponse> allProductsCall = requestManager.create(api.Products.class).getAllProducts();

    try {
      Response allProductsResponse = allProductsCall.execute();
      ProductsResponse allProductsResponseBody = (ProductsResponse) allProductsResponse.body();

      return allProductsResponseBody;
    } catch (IOException e) {
      throw e;
    }
  }

  public Product getProduct(String id) throws IOException {
    Call<Product> productCall = requestManager.create(api.Product.class).getProduct(id);

    try {
      Response productResponse = productCall.execute();
      Product productResponseBody = (Product) productResponse.body();

      return productResponseBody;
    } catch (IOException e) {
      throw e;
    }
  }

  public AccountDetails getAccountDetails() throws IOException {
    Call<AccountDetails> accountCall = requestManager.create(Account.class).getAccountDetails();

    try {
      Response accountResponse = accountCall.execute();
      AccountDetails accountResponseBody = (AccountDetails) accountResponse.body();

      return accountResponseBody;
    } catch (IOException e) {
      throw e;
    }
  }

  public Order createSingleCodeOrder(String id) throws IOException {
    Call<Order> orderCall = requestManager.create(Orders.class).createSingleCodeOrder(id);

    try {
      Response orderResponse = orderCall.execute();
      Order orderResponseBody = (Order) orderResponse.body();

      return orderResponseBody;
    } catch (IOException e) {
      throw e;
    }
  }

  public MultipleOrder createMultipleCodesOrder(String id, int quantity) throws IOException {
    Call<MultipleOrder> multipleOrderCall = requestManager.create(Orders.class).createMultipleCodesOrder(id, quantity);

    try {
      Response multipleOrderResponse = multipleOrderCall.execute();
      MultipleOrder multipleOrderResponseBody = (MultipleOrder) multipleOrderResponse.body();

      return multipleOrderResponseBody;
    } catch (IOException e) {
      throw e;
    }
  }
}
