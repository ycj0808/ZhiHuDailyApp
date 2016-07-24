package com.android.ice.zhihudaily.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.widget.Button;

import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.mvp.base.activity.BaseSwipeBackMvpActivity;
import com.android.ice.zhihudaily.mvp.presenter.UploadRxPresenter;
import com.android.ice.zhihudaily.mvp.view.UploadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangchj on 16/7/24.
 */
public class UploadActivity extends BaseSwipeBackMvpActivity<UploadView, UploadRxPresenter> implements UploadView {


    @BindView(R.id.btnSingleUpload)
    Button btnSingleUpload;

    ProgressDialog progressDialog;
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanBack(false);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
        initProgressDialog();
    }

    private void initProgressDialog(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("文件上传");
        progressDialog.setMessage("正在上传,请稍后...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
    }

    @Override
    protected void requestData() {
    }

    @NonNull
    @Override
    public UploadRxPresenter createPresenter() {
        return new UploadRxPresenter();
    }

    @Override
    public void start() {
        if(progressDialog==null){
            initProgressDialog();
        }
        progressDialog.show();
    }

    @Override
    public void end() {
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
        }
    }

    @Override
    public void uploadSuccess() {
        Snackbar.make(btnSingleUpload,"上传成功",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void uploadFailure() {
        Snackbar.make(btnSingleUpload,"上传失败",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setUploadProgress(long bytesWritten, long contentLength, boolean done) {
        progressDialog.setMax((int) (contentLength/1024));
        progressDialog.setProgress((int) (bytesWritten/1024));
    }

    @OnClick(R.id.btnSingleUpload)
    public void onClick() {
        String imgPath="/storage/emulated/0/sina/weibo/weibo/img-3acf8c5132a2e16b63b49fe9ac0311bf.jpg";
        presenter.upload(imgPath);
    }
}
