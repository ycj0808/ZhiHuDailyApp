package com.android.ice.zhihudaily.mvp.base.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public interface BaseMvpView<M> extends MvpView{

    public void showLoading(boolean pullToRefresh);

    public void showError(Throwable e, boolean pullToRefresh);

    public void setData(M data);
}
