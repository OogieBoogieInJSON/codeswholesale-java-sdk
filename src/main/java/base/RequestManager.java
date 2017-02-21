package base;

import lombok.Setter;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public final class RequestManager {
  @Setter
  private static String host;
  private static Retrofit retrofit;

  public static Retrofit getInstance() {
    synchronized (RequestManager.class) {
      if (retrofit == null) {
        retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(host)
          .build();
      }

      return retrofit;
    }
  }
}
