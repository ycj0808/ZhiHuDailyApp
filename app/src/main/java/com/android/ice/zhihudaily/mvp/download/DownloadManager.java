package com.android.ice.zhihudaily.mvp.download;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public class DownloadManager {

    private static final int DEFAULT_TIMEOUT = 15;
    public Retrofit retrofit;
    public DownloadManager(String url,ProgressResponseListener listener){
        DownloadProgressInterceptor interceptor=new DownloadProgressInterceptor(listener);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> T createApi(Class<T> clazz){
        return retrofit.create(clazz);
    }
}
