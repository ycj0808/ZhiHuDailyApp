package com.android.ice.zhihudaily.mvp.base;

import android.os.Bundle;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * 基类继承了SwipeBack类
 * Created by yangchj on 2016/7/7 0007.
 * email:yangchj@neusoft.com
 */
public abstract class BaseSwipeBackMvpActivity<V extends MvpView,P extends MvpPresenter<V>> extends MvpSwipeBackActivity<V, P> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
