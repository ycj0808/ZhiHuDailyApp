package com.android.ice.zhihudaily.mvp.upload;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public class UploadManager {

    private static final int DEFAULT_TIMEOUT = 15;
    public Retrofit retrofit;
    public UploadManager(ProgressRequestListener listener){
        UploadProgressInterceptor interceptor=new UploadProgressInterceptor(listener);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        //http://218.24.170.149:8051

        //http://218.24.170.149:8004
        retrofit=new Retrofit.Builder()
                .baseUrl("http://218.24.170.149:8004/epm/index.php/")
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T createApi(Class<T> clazz){
        return retrofit.create(clazz);
    }
}
