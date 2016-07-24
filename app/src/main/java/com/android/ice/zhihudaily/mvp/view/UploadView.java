package com.android.ice.zhihudaily.mvp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by yangchj on 16/7/24.
 */
public interface UploadView extends MvpView{

    public void start();
    public void end();
    public void uploadSuccess();
    public void uploadFailure();
    public void setUploadProgress(long bytesWritten, long contentLength, boolean done);
}
