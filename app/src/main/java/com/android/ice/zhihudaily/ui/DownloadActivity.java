package com.android.ice.zhihudaily.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.widget.Button;

import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.mvp.base.activity.BaseSwipeBackMvpActivity;
import com.android.ice.zhihudaily.mvp.presenter.DownloadRxPresenter;
import com.android.ice.zhihudaily.mvp.view.DownloadView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public class DownloadActivity extends BaseSwipeBackMvpActivity<DownloadView, DownloadRxPresenter> implements DownloadView {

    @BindView(R.id.btnDownload)
    Button btnDownload;

    ProgressDialog progressDialog;
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanBack(true);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        initProgressDialog();
    }

    private void initProgressDialog(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("文件下载");
        progressDialog.setMessage("正在下载,请稍后...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
    }

    @Override
    protected void requestData() {
    }

    @Override
    public void startDownload() {
        Logger.d("--startDownload--");
        if(progressDialog==null){
            initProgressDialog();
        }
        progressDialog.show();
    }

    @Override
    public void setDownloadProgress(long bytesRead, long contentLength, boolean done) {
        Logger.d("--progress--%s",(int)((100 * bytesRead) / contentLength));
        progressDialog.setMax((int) (contentLength/1024));
        progressDialog.setProgress((int) (bytesRead/1024));
    }

    @Override
    public void downloadSuccess() {
        Logger.d("--downloadSuccess--");
        Snackbar.make(btnDownload,"下载成功",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void downloadFailure() {
        Logger.d("--downloadFailure--");
        Snackbar.make(btnDownload,"下载失败",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void endDownload() {
        Logger.d("--endDownload--");
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
        }
    }

    @NonNull
    @Override
    public DownloadRxPresenter createPresenter() {
        return new DownloadRxPresenter();
    }


    @OnClick(R.id.btnDownload)
    public void onClick() {
        presenter.download();
    }
}
