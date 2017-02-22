package api;

import resources.MultipleOrder;
import resources.Order;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Orders {
  @POST("/v1/orders")
  Call<Order> createSingleCodeOrder(@Query("productId") String productId);

  @POST("/v1/orders")
  Call<MultipleOrder> createMultipleCodesOrder(@Query("productId") String productId,
                                               @Query("quantity") int quantity);
}
