package com.android.ice.zhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.mvp.base.activity.BaseMvpActivity;
import com.android.ice.zhihudaily.mvp.bean.News;
import com.android.ice.zhihudaily.mvp.presenter.LastNewsRxPresenter;
import com.android.ice.zhihudaily.mvp.view.LastNewsView;
import com.android.ice.zhihudaily.support.utils.Utils;
import com.android.ice.zhihudaily.support.widget.RecycleViewDivider;
import com.android.ice.zhihudaily.ui.adapter.LastNewsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MainActivity extends BaseMvpActivity<LastNewsView, LastNewsRxPresenter> implements LastNewsView, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;

    @BindView(R.id.rvNews)
    RecyclerView rvNews;

    LastNewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
    }

    protected void init() {
        rvNews.setLayoutManager(new LinearLayoutManager(mContext));
        RecycleViewDivider divider=new RecycleViewDivider(mContext,LinearLayoutManager.VERTICAL, Utils.dip2px(10),R.color.line_divider);
        rvNews.addItemDecoration(divider);
        newsAdapter=new LastNewsAdapter(rvNews);
        BGANormalRefreshViewHolder viewHolder=new BGANormalRefreshViewHolder(mContext,true);
        refreshLayout.setRefreshViewHolder(viewHolder);
        rvNews.setAdapter(newsAdapter);
        refreshLayout.setDelegate(this);
    }

    @Override
    protected void requestData() {
        loadData(false);
    }

    private void loadData(boolean flag){
        if(presenter!=null){
            presenter.loadLastNews(flag);
        }
    }

    @NonNull
    @Override
    public LastNewsRxPresenter createPresenter() {
        return new LastNewsRxPresenter();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        if(!pullToRefresh){
            showLoadingDialog("数据加载中,请稍后...");
        }
    }


    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        dissmissDialog();
    }

    @Override
    public void setData(List<News> data) {
        dissmissDialog();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        loadData(true);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
