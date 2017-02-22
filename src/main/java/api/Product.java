package api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Product {
  @GET("/v1/products/{id}")
  Call<resources.Product> getProduct(@Path("id") String id);
}
