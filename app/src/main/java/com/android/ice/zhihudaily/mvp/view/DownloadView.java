package com.android.ice.zhihudaily.mvp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * 下载
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public interface DownloadView extends MvpView{
    public void startDownload();
    public void setProgress(int progress);
    public void endDownload();
}
