package base;

import api.Account;
import api.Orders;
import lombok.Getter;
import lombok.Setter;
import resources.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

@Getter
@Setter
public class API {
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

      RequestManager.addAuthorizationHeader(accessToken);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean isTokenRegenerationNeeded() {
    return accessToken.getExpiresIn() >= 0;
  }

  public Products getAllProducts() throws IOException {
    Call<Products> call = requestManager.create(api.Products.class).getAllProducts();

    try {
      Response response = call.execute();
      Products body = (Products) response.body();

      return body;
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
