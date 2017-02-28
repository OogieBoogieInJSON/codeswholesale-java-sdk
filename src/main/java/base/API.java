package base;

import api.Account;
import api.Orders;
import exception.AuthorizationFailedException;
import lombok.Getter;
import lombok.Setter;
import resources.*;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Getter
@Setter
public class API {
  /**
   * default constructor
   * @constructor
   * @param apiContext
   */
  public API(APIContext apiContext) {
    RequestManager.setApiContext(apiContext);
  }

  public Products getAllProducts() throws IOException, AuthorizationFailedException {
    return RequestManager.createService(api.Products.class).getAllProducts().execute().body();
  }

  public Product getProduct(String id) throws IOException, AuthorizationFailedException {
    return RequestManager.createService(api.Product.class).getProduct(id).execute().body();
  }

  public AccountDetails getAccountDetails() throws IOException, AuthorizationFailedException {
    return RequestManager.createService(Account.class).getAccountDetails().execute().body();
  }

  public Order createSingleCodeOrder(String id) throws IOException, AuthorizationFailedException {
    return RequestManager.createService(Orders.class).createSingleCodeOrder(id).execute().body();
  }

  public MultipleOrder createMultipleCodesOrder(String id, int quantity) throws IOException, AuthorizationFailedException {
    return RequestManager.createService(Orders.class).createMultipleCodesOrder(id, quantity).execute().body();
  }
}
