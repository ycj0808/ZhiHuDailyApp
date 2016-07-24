package com.android.ice.zhihudaily.mvp.presenter;

import android.os.Looper;

import com.android.ice.zhihudaily.mvp.base.presenter.NullObjRxBasePresenter;
import com.android.ice.zhihudaily.mvp.upload.ProgressRequestListener;
import com.android.ice.zhihudaily.mvp.upload.UploadManager;
import com.android.ice.zhihudaily.mvp.upload.UploadResult;
import com.android.ice.zhihudaily.mvp.upload.UploadService;
import com.android.ice.zhihudaily.mvp.view.UploadView;
import com.android.ice.zhihudaily.support.utils.ExceptionUtils;
import com.android.ice.zhihudaily.support.utils.JSONUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yangchj on 16/7/24.
 */
public class UploadRxPresenter extends NullObjRxBasePresenter<UploadView> implements UploadPresenter{

//    private static final String imgPath="/storage/emulated/0/Pictures/JPEG_20160707_112942_535242727.jpg";
private static final String imgPath="/storage/emulated/0/sina/weibo/weibo/img-3acf8c5132a2e16b63b49fe9ac0311bf.jpg";
    @Override
    public void upload(String filePath) {
        addSubscription(new UploadManager(uploadListener).createApi(UploadService.class)
            .uploadFile(createMultipartBody(filePath))
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if(isViewAttached()){
                            getView().start();
                        }
                    }
                })
                .subscribe(new Action1<UploadResult>() {
                    @Override
                    public void call(UploadResult result) {
                        try {
                            Logger.d("uploadResult:%s", JSONUtils.toJson(result));
                            if(result.isSuccess()){
                                getView().uploadSuccess();
                            }else{
                                getView().uploadFailure();
                            }
                        }catch (Exception e){
                            Logger.e(e, ExceptionUtils.getErrorInfo(e));
                            getView().uploadFailure();
                        }
                        getView().end();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().uploadFailure();
                        getView().end();
                    }
                })
        );
    }

    @Override
    public void upload(List<String> photos, String content) {
        addSubscription(new UploadManager(uploadListener).createApi(UploadService.class)
        .uploadFile(createMultipartBody(photos),RequestBody.create(null,content))
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UploadResult>() {
                    @Override
                    public void call(UploadResult result) {
                        try {
                            Logger.d("uploadResult:%s", JSONUtils.toJson(result));
                            if(result.isSuccess()){
                                getView().uploadSuccess();
                            }else{
                                getView().uploadFailure();
                            }
                        }catch (Exception e){
                            Logger.e(e, ExceptionUtils.getErrorInfo(e));
                            getView().uploadFailure();
                        }
                        getView().end();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().uploadFailure();
                        getView().end();
                    }
                })
        );
    }

    /**
     * 单张图片上传参数构造
     * @param filePath
     * @return
     */
    private MultipartBody.Part createMultipartBody(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            return null;
        }

        RequestBody requestFile=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("img",file.getName(),requestFile);
        return body;
    }

    private Map<String,RequestBody> createMultipartBody(List<String> photos){
        Map<String,RequestBody> photoMap=new HashMap<>();
        if(photos.size()>0){
            for(int i=0;i<photos.size();i++){
                File file=new File(photos.get(i));
                RequestBody requestFile=RequestBody.create(MediaType.parse("multipart/form-data"),file);
                photoMap.put("AttachmentKey"+i+"\"; filename=\""+file.getName(),requestFile);
            }
        }
        return photoMap;
    }

    private ProgressRequestListener uploadListener=new ProgressRequestListener() {
        @Override
        public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
            Logger.d("是否在主线程中运行:%s",String.valueOf(Looper.getMainLooper()==Looper.myLooper()));
            Logger.d("onPreogress---%d%% done\n",(100 * bytesWritten) / contentLength);
            getView().setUploadProgress(bytesWritten,contentLength,done);
        }
    };
}
