import base.API;
import base.APIContext;

public class Application {
  public static void main(String args[]) {
    APIContext apiContext = new APIContext("https://api.codeswholesale.com", "17f0372c6d8f7ba95180731604ab74c1", "$2a$10$CUYhGrzZRYMMTgpmgVc5NOi7us/5YRx1BMXzB1nd99ApX1ocWSw3e");
    API cwsAPI = new API(apiContext);
    cwsAPI.authorize();

  }
}
