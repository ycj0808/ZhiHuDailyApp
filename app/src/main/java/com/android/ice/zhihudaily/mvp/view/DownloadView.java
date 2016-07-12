package com.android.ice.zhihudaily.mvp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public interface DownloadView extends MvpView{
    public void start();
    public void setProgress(int progress);
}
