package api;

import base.AccessToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Auth {
  @FormUrlEncoded
  @POST("/oauth/token")
  Call<AccessToken> generateOAuthToken(@Field("grant_type") String grantType,
                                       @Field("client_id") String clientId,
                                       @Field("client_secret") String clientSecret);
}
