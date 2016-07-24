package com.android.ice.zhihudaily.mvp.presenter;

import android.os.Looper;

import com.android.ice.zhihudaily.App;
import com.android.ice.zhihudaily.mvp.base.presenter.NullObjRxBasePresenter;
import com.android.ice.zhihudaily.mvp.download.DownloadManager;
import com.android.ice.zhihudaily.mvp.download.DownloadService;
import com.android.ice.zhihudaily.mvp.download.ProgressResponseListener;
import com.android.ice.zhihudaily.mvp.view.DownloadView;
import com.android.ice.zhihudaily.support.utils.FileUtils;
import com.orhanobut.logger.Logger;

import java.io.File;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 下载
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public class DownloadRxPresenter extends NullObjRxBasePresenter<DownloadView> implements DownloadPresenter{

    private String urlImg="http://business.cdn.qianqian.com/cms/BaiduMusic-pcwebdownload.apk";

    @Override
    public void attachView(DownloadView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
    @Override
    public void download() {

        addSubscription(new DownloadManager(downloadListener).createApi(DownloadService.class)
                .download(urlImg)
                .subscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, File>() {
                    @Override
                    public File call(ResponseBody responseBody) {
                        String saveName=System.currentTimeMillis()+".apk";
                        boolean flag=FileUtils.saveResponseBodyToDisk(responseBody,saveName);
                        if(flag){
                            return new File(FileUtils.getDownloadDic()+File.separator+ App.getInstance().getPackageName()+File.separator+saveName);
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if(isViewAttached()){
                            getView().startDownload();
                        }
                    }
                })
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        if (file != null) {
                            getView().downloadSuccess();
                        }else {
                            getView().downloadFailure();
                        }
                        getView().endDownload();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().downloadFailure();
                        getView().endDownload();
                    }
                })

        );
    }

    private ProgressResponseListener downloadListener=new ProgressResponseListener() {
        @Override
        public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
            Logger.d("是否在主线程中运行:%s",String.valueOf(Looper.getMainLooper()==Looper.myLooper()));
            Logger.d("onPreogress---%d%% done\n",(100 * bytesRead) / contentLength);
            getView().setDownloadProgress(bytesRead,contentLength,done);
        }
    };
}
