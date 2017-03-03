package base;

import api.Account;
import api.Orders;
import exception.AuthenticationFailedException;
import lombok.Getter;
import lombok.Setter;
import resources.*;

import java.io.IOException;

@Getter
@Setter
/**
 * Has all the API available methods
 */
public class API {
  public API(APIContext apiContext) {
    RequestManager.setApiContext(apiContext);
  }

  public Products getAllProducts() throws IOException, AuthenticationFailedException {
    return RequestManager.createService(api.Products.class).getAllProducts().execute().body();
  }

  public Product getProduct(String id) throws IOException, AuthenticationFailedException {
    return RequestManager.createService(api.Product.class).getProduct(id).execute().body();
  }

  public AccountDetails getAccountDetails() throws IOException, AuthenticationFailedException {
    return RequestManager.createService(Account.class).getAccountDetails().execute().body();
  }

  public Order createSingleCodeOrder(String id) throws IOException, AuthenticationFailedException {
    return RequestManager.createService(Orders.class).createSingleCodeOrder(id).execute().body();
  }

  public MultipleOrder createMultipleCodesOrder(String id, int quantity) throws IOException, AuthenticationFailedException {
    return RequestManager.createService(Orders.class).createMultipleCodesOrder(id, quantity).execute().body();
  }
}
