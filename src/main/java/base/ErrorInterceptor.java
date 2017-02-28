package base;

import exception.AuthorizationFailedException;
import exception.ErrorResponseException;
import models.ErrorResponse;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ErrorInterceptor implements Interceptor {
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    Response response = chain.proceed(request);

    if (!response.isSuccessful()) {
      ErrorResponse error = RequestManager.resolveErrorBodyFromJsonString(response.body().string());
      ErrorResponseException errorResponseException = new ErrorResponseException("error", error);

      throw new IOException(error.toString(), errorResponseException);
    }

    return response;
  }
}
