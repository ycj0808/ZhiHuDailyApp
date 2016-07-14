package com.android.ice.zhihudaily.mvp.presenter;

import com.android.ice.zhihudaily.mvp.view.NewsDetailView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Created by yangchj on 2016/7/14 0014.
 * email:yangchj@neusoft.com
 */
public interface NewsDetailPresenter extends MvpPresenter<NewsDetailView>{
    public void loadNewsDetail(int id);
}
