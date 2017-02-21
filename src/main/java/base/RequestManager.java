package base;

import com.google.gson.Gson;
import lombok.Setter;
import okhttp3.OkHttpClient;
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

  public static Retrofit createNewWithHttpClient(OkHttpClient.Builder httpClient) {
    OkHttpClient client = httpClient.build();
    retrofit = new Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(host)
      .client(client)
      .build();

    return retrofit;
  }
}
