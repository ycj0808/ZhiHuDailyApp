package com.android.ice.zhihudaily.mvp.presenter;

import com.android.ice.zhihudaily.mvp.base.presenter.NullObjRxBasePresenter;
import com.android.ice.zhihudaily.mvp.view.DownloadView;

/**
 * 下载
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public class DownloadRxPresenter extends NullObjRxBasePresenter<DownloadView> implements DownloadPresenter{
    @Override
    public void attachView(DownloadView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
    @Override
    public void download() {

    }
}
