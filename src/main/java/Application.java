import base.API;
import base.APIContext;
import exception.AuthorizationFailedException;
import resources.Order;
import resources.Product;
import resources.Products;

import java.io.IOException;

public class Application {
  public static void main(String args[]) {
    APIContext apiContext = new APIContext("17f0372c6d8f7ba95180731604ab74c1", "$2a$10$CUYhGrzZRYMMTgpmgVc5NOi7us/5YRx1BMXzB1nd99ApX1ocWSw3e");
    API cwsAPI = new API(apiContext);

//    TODO: add docs
//    TODO: write tests

    try {
      Product order = cwsAPI.getProduct("f4a41974-5373-4862-a5e7-9d28b8c2301f");
      System.out.print(order);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (AuthorizationFailedException e) {
      e.printStackTrace();
    }
  }
}
