package com.android.ice.zhihudaily.mvp.upload;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yangchj on 16/7/23.
 */
public class UploadProgressInterceptor implements Interceptor{
    private ProgressRequestListener listener;

    public UploadProgressInterceptor(ProgressRequestListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest=chain.request();
        Request request=originalRequest.newBuilder().method(originalRequest.method(),new ProgressRequestBody(originalRequest.body(),listener)).build();
        return chain.proceed(request);
    }
}
