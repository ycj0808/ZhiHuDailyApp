package com.android.ice.zhihudaily.mvp.download;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public interface DownloadService {

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
