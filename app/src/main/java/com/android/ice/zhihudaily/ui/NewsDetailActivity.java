package com.android.ice.zhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.mvp.base.activity.BaseSwipeBackMvpActivity;
import com.android.ice.zhihudaily.mvp.presenter.NewsDetailRxPresenter;
import com.android.ice.zhihudaily.mvp.view.NewsDetailView;

/**
 * Created by yangchj on 2016/7/14 0014.
 * email:yangchj@neusoft.com
 */
public class NewsDetailActivity extends BaseSwipeBackMvpActivity<NewsDetailView,NewsDetailRxPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }
    @Override
    protected void afterCreate(Bundle savedInstanceState) {
    }

    @NonNull
    @Override
    public NewsDetailRxPresenter createPresenter() {
        return new NewsDetailRxPresenter();
    }
}
