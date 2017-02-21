package base;

import lombok.Getter;
import lombok.Setter;

/**
 * api config handler
 */
@Getter
@Setter
public class APIContext {
  /**
   * domain
   */
  public String host = "https://api.codeswholesale.com";

  /**
   * i have no ideea what this is
   */
  private final String grantType = "client_credentials";

  /**
   * client id
   */
  public String clientId;

  /**
   * client secret
   */
  public String clientSecret;

  /**
   * default constructor
   * @constructor
   */
  public APIContext() {

  }

  /**
   * Pass the client id and client secret
   * @constructor
   * @param clientId
   * @param clientSecret
   */
  public APIContext(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  /**
   * Pass the host, client id and client secret
   * @constructor
   * @param host
   * @param clientId
   * @param clientSecret
   */
  public APIContext(String host, String clientId, String clientSecret) {
    this.host = host;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }
}
