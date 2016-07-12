package com.android.ice.zhihudaily.mvp.presenter;

import com.android.ice.zhihudaily.mvp.view.DownloadView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public interface DownloadPresenter extends MvpPresenter<DownloadView>{
    public void download();
}
