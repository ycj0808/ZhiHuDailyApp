package com.android.ice.zhihudaily.mvp.upload;

/**
 * 请求体进度回调接口，用于文件上传进度回调
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public interface ProgressRequestListener {
    void onRequestProgress(long bytesWritten, long contentLength, boolean done);
}
