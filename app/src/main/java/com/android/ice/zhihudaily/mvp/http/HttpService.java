package com.android.ice.zhihudaily.mvp.http;

import com.android.ice.zhihudaily.App;
import com.android.ice.zhihudaily.support.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangchj on 2016/6/8 0008.
 * email:yangchj@neusoft.com
 */
public class HttpService {

    private final static String BASE_URL = "";
    private static Retrofit retrofit;
    private static OkHttpClient mOkHttpClient;

    //短缓存有效期为1分钟
    public static final int CACHE_STALE_SHORT = 60;
    //长缓存有效期为7天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;
    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";


    private HttpService(){
        /*OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();*/
        initOkHttpClient();
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void initOkHttpClient(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(mOkHttpClient==null){
            synchronized (HttpService.class){
                if(mOkHttpClient==null){
                    //指定缓存路径，缓存大小
                    Cache cache=new Cache(new File(App.getInstance().getCacheDir(),"HttpCache"),124*1024*100);
                    mOkHttpClient=new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }

    }
    //云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request();
            if(!NetUtil.isNetworkConnected()){
                request=request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse=chain.proceed(request);
            if(NetUtil.isNetworkConnected()){
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl=request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            }else{
                return originalResponse.newBuilder().header("Cache-Control","public, only-if-cached, max-stale=" + CACHE_STALE_LONG).removeHeader("").build();
            }
        }
    };

    private static class HttpServiceHolder{
        public static final HttpService INSTANCE=new HttpService();
    }

    public static HttpService getInstance(){
        return HttpServiceHolder.INSTANCE;
    }

    public <T> T createApi(Class<T> clazz){
        return retrofit.create(clazz);
    }
}
