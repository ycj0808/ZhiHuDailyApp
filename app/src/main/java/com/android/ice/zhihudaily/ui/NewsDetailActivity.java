package com.android.ice.zhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.mvp.base.activity.BaseSwipeBackMvpActivity;
import com.android.ice.zhihudaily.mvp.bean.News;
import com.android.ice.zhihudaily.mvp.bean.NewsDetail;
import com.android.ice.zhihudaily.mvp.presenter.NewsDetailRxPresenter;
import com.android.ice.zhihudaily.mvp.view.NewsDetailView;
import com.android.ice.zhihudaily.support.utils.HtmlUtil;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangchj on 2016/7/14 0014.
 * email:yangchj@neusoft.com
 */
public class NewsDetailActivity extends BaseSwipeBackMvpActivity<NewsDetailView, NewsDetailRxPresenter> implements NewsDetailView{
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_source)
    TextView tvSource;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.wv_news)
    WebView wvNews;
    @BindView(R.id.nested_view)
    NestedScrollView nestedView;
    @BindView(R.id.tv_load_empty)
    TextView tvLoadEmpty;
    @BindView(R.id.tv_load_error)
    TextView tvLoadError;

    private News news;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        init();
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            if(bundle.containsKey("news")){
                news=bundle.getParcelable("news");
            }
        }
    }

    private void init(){
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitleEnabled(true);
        mSwipeBackLayout.setEdgeDp(120);//设置滑动返回触发范围
    }

    @Override
    protected void requestData() {
        if(news!=null){
            presenter.loadNewsDetail(news.getId());
        }
    }

    @NonNull
    @Override
    public NewsDetailRxPresenter createPresenter() {
        return new NewsDetailRxPresenter();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        showLoadingDialog("数据加载中,请稍候...");
    }

    @Override
    public void hideLoading() {
        dismissDialog();
    }

    @Override
    public void showError(Throwable e) {
        tvLoadError.setVisibility(View.VISIBLE);
        tvLoadEmpty.setVisibility(View.GONE);
    }

    @Override
    public void setData(NewsDetail data) {
        if(data!=null){
            Glide.with(mContext).load(data.getImage())
                    .into(ivHeader);
            tvTitle.setText(data.getTitle());
            tvSource.setText(data.getImage_source());

            StringBuffer stringBuffer= HtmlUtil.handleHtml(data.getBody(),false);
            wvNews.setDrawingCacheEnabled(true);
            wvNews.loadDataWithBaseURL("file:///android_asset/",stringBuffer.toString(),"text/html","utf-8",null);
            tvLoadEmpty.setVisibility(View.GONE);
            tvLoadError.setVisibility(View.GONE);
        }else{
            tvLoadEmpty.setVisibility(View.VISIBLE);
            tvLoadError.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmptyView(boolean flag) {
        tvLoadEmpty.setVisibility(View.VISIBLE);
        tvLoadError.setVisibility(View.GONE);
    }
}
