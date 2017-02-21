package api;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface Products {
  @GET("/v1/products")
  Call<List<Products>> getAllProducts();
}
