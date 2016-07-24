package com.android.ice.zhihudaily.mvp.presenter;

import com.android.ice.zhihudaily.mvp.view.UploadView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

/**
 * Created by yangchj on 16/7/24.
 */
public interface UploadPresenter extends MvpPresenter<UploadView>{

    public void upload(String filePath);

    public void upload(List<String> photos,String content);
}
