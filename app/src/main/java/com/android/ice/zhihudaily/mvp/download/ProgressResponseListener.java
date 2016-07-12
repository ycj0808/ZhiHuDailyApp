package com.android.ice.zhihudaily.mvp.download;

/**
 * 响应体进度回调接口，用于文件下载进度回调
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public interface ProgressResponseListener {
    void onResponseProgress(long bytesRead, long contentLength, boolean done);
}
