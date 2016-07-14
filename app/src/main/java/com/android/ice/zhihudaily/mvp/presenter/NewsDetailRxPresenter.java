package com.android.ice.zhihudaily.mvp.presenter;

import com.android.ice.zhihudaily.mvp.api.ZhihuService;
import com.android.ice.zhihudaily.mvp.base.presenter.NullObjRxBasePresenter;
import com.android.ice.zhihudaily.mvp.bean.NewsDetail;
import com.android.ice.zhihudaily.mvp.http.HttpManager;
import com.android.ice.zhihudaily.mvp.view.NewsDetailView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yangchj on 2016/7/14 0014.
 * email:yangchj@neusoft.com
 */
public class NewsDetailRxPresenter extends NullObjRxBasePresenter<NewsDetailView> implements NewsDetailPresenter {
    @Override
    public void loadNewsDetail(int id) {
        addSubscription(HttpManager.getInstance().createApi(ZhihuService.class).getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        getView().showLoading(false);
                    }
                })
                .subscribe(new Action1<NewsDetail>() {
                    @Override
                    public void call(NewsDetail newsDetail) {
                        getView().hideLoading();
                        if (newsDetail == null) {
                            getView().showEmptyView(true);
                        } else {
                            getView().setData(newsDetail);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().hideLoading();
                        getView().showError(throwable);
                    }
                })
        );
    }
}
