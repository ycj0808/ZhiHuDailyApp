package com.android.ice.zhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.mvp.base.activity.BaseMvpActivity;
import com.android.ice.zhihudaily.mvp.bean.NewsList;
import com.android.ice.zhihudaily.mvp.presenter.LastNewsRxPresenter;
import com.android.ice.zhihudaily.mvp.view.LastNewsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MainActivity extends BaseMvpActivity<LastNewsView, LastNewsRxPresenter> implements LastNewsView{

    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;

    @BindView(R.id.rvNews)
    RecyclerView rvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
    }

    @NonNull
    @Override
    public LastNewsRxPresenter createPresenter() {
        return new LastNewsRxPresenter();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

    }

    @Override
    public void setData(NewsList data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
