package com.android.ice.zhihudaily.mvp.presenter;

import com.android.ice.zhihudaily.mvp.api.ZhihuService;
import com.android.ice.zhihudaily.mvp.base.presenter.NullObjRxBasePresenter;
import com.android.ice.zhihudaily.mvp.bean.NewsList;
import com.android.ice.zhihudaily.mvp.http.HttpManager;
import com.android.ice.zhihudaily.mvp.view.LastNewsView;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yangchj on 2016/7/11 0011.
 * email:yangchj@neusoft.com
 */
public class LastNewsRxPresenter extends NullObjRxBasePresenter<LastNewsView> implements LastNewsPresenter{

    @Override
    public void attachView(LastNewsView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }

    @Override
    public void loadLastNews(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        addSubscription(HttpManager.getInstance().createApi(ZhihuService.class).getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsList>() {
                    @Override
                    public void call(NewsList newsList) {
                        if(isViewAttached()){
                            if(newsList!=null){
                                getView().setData(newsList);
                                getView().showContent();
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().showError(throwable,pullToRefresh);
                    }
                })
        );
    }
}
