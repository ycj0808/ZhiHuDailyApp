package com.android.ice.zhihudaily.mvp.view;

import com.android.ice.zhihudaily.mvp.base.view.BaseMvpView;
import com.android.ice.zhihudaily.mvp.bean.News;

import java.util.List;

/**
 * 最新的消息
 * Created by yangchj on 2016/7/11 0011.
 * email:yangchj@neusoft.com
 */
public interface LastNewsView extends BaseMvpView<List<News>> {
    public void setCurrentDate(String curDate,boolean loadMore);

    public void endRefresh(boolean flag);

    public void addMoreData(List<News> newses);

    public void endLoadMore();
}
