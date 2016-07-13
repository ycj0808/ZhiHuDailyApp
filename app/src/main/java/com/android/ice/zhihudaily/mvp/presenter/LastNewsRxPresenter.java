package com.android.ice.zhihudaily.mvp.presenter;

import com.android.ice.zhihudaily.mvp.api.ZhihuService;
import com.android.ice.zhihudaily.mvp.base.presenter.NullObjRxBasePresenter;
import com.android.ice.zhihudaily.mvp.bean.NewsList;
import com.android.ice.zhihudaily.mvp.http.HttpManager;
import com.android.ice.zhihudaily.mvp.view.LastNewsView;
import com.android.ice.zhihudaily.support.utils.ExceptionUtils;
import com.orhanobut.logger.Logger;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
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
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                       if(isViewAttached()){
                           getView().showLoading(pullToRefresh);
                       }
                    }
                })
                /*.map(new Func1<NewsList, NewsList>() {
                    @Override
                    public NewsList call(NewsList newsList) {
                        做一些缓存操作
                        return null;
                    }
                })*/
                .subscribe(new Action1<NewsList>() {
                    @Override
                    public void call(NewsList newsList) {
                        if(isViewAttached()){
                            getView().hideLoading();
                            getView().endRefresh(pullToRefresh);
                            if(newsList.getStories()==null){
                                getView().showEmptyView(true);
                            }else{
                                getView().setData(newsList.getStories());
                                getView().showEmptyView(false);
                                String curDate=newsList.getDate();
                                if(newsList.getStories().size()<8){
                                    getView().setCurrentDate(curDate,true);
                                }else{
                                    getView().setCurrentDate(curDate,false);
                                }
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e(throwable, ExceptionUtils.getErrorInfo(throwable));
                        getView().hideLoading();
                        getView().endRefresh(pullToRefresh);
                        getView().showError(throwable);
                    }
                })
        );
    }

    @Override
    public void loadBeforeNews(String date) {
        addSubscription(HttpManager.getInstance().createApi(ZhihuService.class).getBeforeNews(date)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsList>() {
                    @Override
                    public void call(NewsList newsList) {
                        if(newsList.getStories()!=null){
                            getView().addMoreData(newsList.getStories());
                            String curDate=newsList.getDate();
                            getView().setCurrentDate(curDate,false);
                        }
                        getView().endLoadMore();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().showError(throwable);
                        getView().endLoadMore();
                    }
                })
        );
    }
}
