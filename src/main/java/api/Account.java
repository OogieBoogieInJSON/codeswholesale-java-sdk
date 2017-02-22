package api;

import resources.AccountDetails;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Account {
  @GET("/v1/accounts/current")
  Call<AccountDetails> getAccountDetails();
}
