package com.android.ice.zhihudaily.mvp.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 下载拦截器
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public class DownloadProgressInterceptor implements Interceptor{

    private ProgressResponseListener listener;
    public DownloadProgressInterceptor(ProgressResponseListener listener) {
        this.listener = listener;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse=chain.proceed(chain.request());
        return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(),listener)).build();
    }
}
