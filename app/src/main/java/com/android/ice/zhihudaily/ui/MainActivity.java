package com.android.ice.zhihudaily.ui;

import android.app.ActivityOptions;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.mvp.base.activity.BaseMvpActivity;
import com.android.ice.zhihudaily.mvp.bean.News;
import com.android.ice.zhihudaily.mvp.presenter.LastNewsRxPresenter;
import com.android.ice.zhihudaily.mvp.view.LastNewsView;
import com.android.ice.zhihudaily.support.utils.Utils;
import com.android.ice.zhihudaily.support.widget.RecycleViewDivider;
import com.android.ice.zhihudaily.ui.adapter.LastNewsAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MainActivity extends BaseMvpActivity<LastNewsView, LastNewsRxPresenter> implements LastNewsView, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;

    @BindView(R.id.rvNews)
    RecyclerView rvNews;

    LastNewsAdapter newsAdapter;

    String curDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanBack(false);
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
        if(savedInstanceState!=null&&savedInstanceState.containsKey("curDate")){
            curDate=savedInstanceState.getString("curDate");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(curDate!=null){
            outState.putString("curDate",curDate);
        }
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
        newsAdapter.setOnRVItemClickListener(this);
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

    private void loadMoreData(){
        if(presenter!=null){
            presenter.loadBeforeNews(curDate);
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
            showLoadingDialog("数据加载中,请稍候...");
        }
    }

    @Override
    public void hideLoading() {
        dismissDialog();
    }


    @Override
    public void showError(Throwable e) {
        dismissDialog();
    }

    @Override
    public void setData(List<News> data) {
        newsAdapter.setDatas(data);
        dismissDialog();
    }

    @Override
    public void showEmptyView(boolean flag) {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        loadData(true);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        Logger.d("--------%s--------",curDate);
        loadMoreData();
        return true;
    }

    @Override
    public void setCurrentDate(String curDate,boolean loadMore) {
        this.curDate=curDate;
        if(loadMore){
            loadMoreData();
        }
    }

    @Override
    public void endRefresh(boolean flag) {
        if(flag){
            refreshLayout.endRefreshing();
        }
    }

    @Override
    public void addMoreData(List<News> newses) {
        newsAdapter.addMoreDatas(newses);
    }

    @Override
    public void endLoadMore() {
        refreshLayout.endLoadingMore();
    }

    @Override
    public void onRVItemClick(ViewGroup viewGroup, View view, int i) {
        News news=newsAdapter.getItem(i);
        Bundle bundle=new Bundle();
        bundle.putParcelable("news",news);
        if(Build.VERSION.SDK_INT>=21){
            ImageView shareView= (ImageView) view.findViewById(R.id.ivNews);
            if(shareView!=null){
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(MainActivity.this, shareView, "robot");
                readGoWithOptions(NewsDetailActivity.class,bundle,options);
            }else{
                readyGo(NewsDetailActivity.class,bundle);
            }
        }else{
            readyGo(NewsDetailActivity.class,bundle);
        }
    }
}
