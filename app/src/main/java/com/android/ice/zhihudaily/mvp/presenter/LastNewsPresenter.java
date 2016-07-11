package com.android.ice.zhihudaily.mvp.presenter;

import com.android.ice.zhihudaily.mvp.view.LastNewsView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * 加载最新的消息
 * Created by yangchj on 2016/7/11 0011.
 * email:yangchj@neusoft.com
 */
public interface LastNewsPresenter extends MvpPresenter<LastNewsView>{
    public void loadLastNews(final boolean pullToRefresh);
}
