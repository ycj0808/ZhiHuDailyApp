package com.android.ice.zhihudaily.mvp.upload;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by yangchj on 16/7/24.
 */
public interface UploadService {

    @Multipart
    @POST("appUtils/uploadcomment")
    Observable<UploadResult> uploadFile(@Part MultipartBody.Part file);


    //上传多张图片
    @Multipart
    @POST("appUtils/uploadcomment")
    Observable<UploadResult> uploadFile(@PartMap Map<String, RequestBody> params,@Part("content") RequestBody content);
}
