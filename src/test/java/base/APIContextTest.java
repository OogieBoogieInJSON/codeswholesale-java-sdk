package base;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class APIContextTest {
  @Test
  public void clientId() {
    APIContext apiContext = new APIContext();
    apiContext.setClientId("test");

    assertEquals(apiContext.getClientId(), "test");
  }

  @Test
  public void clientSecret() {
    APIContext apiContext = new APIContext();
    apiContext.setClientSecret("test");

    assertEquals(apiContext.getClientSecret(), "test");
  }

  @Test
  public void createUsingClientIdAndClientSecret() {
    APIContext apiContext = new APIContext("test client id", "test client secret");

    assertEquals(apiContext.getClientId(), "test client id");
    assertEquals(apiContext.getClientSecret(), "test client secret");
  }

  @Test
  public void createUsingHostClientIdAndClientSecret() {
    APIContext apiContext = new APIContext("new host", "test client id", "test client secret");

    assertEquals(apiContext.getHost(), "new host");
    assertEquals(apiContext.getClientId(), "test client id");
    assertEquals(apiContext.getClientSecret(), "test client secret");
  }
}
