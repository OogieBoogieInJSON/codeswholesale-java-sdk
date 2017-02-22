package api;

import resources.ProductsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Products {
  @GET("/v1/products")
  Call<ProductsResponse> getAllProducts();
}
