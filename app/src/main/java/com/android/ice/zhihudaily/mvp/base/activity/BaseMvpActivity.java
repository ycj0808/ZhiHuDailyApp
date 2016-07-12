package com.android.ice.zhihudaily.mvp.base.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.support.widget.FlatButton;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * 基类继承了SwipeBack类
 * Created by yangchj on 2016/7/7 0007.
 * email:yangchj@neusoft.com
 */
public abstract class BaseMvpActivity<V extends MvpView,P extends MvpPresenter<V>> extends MvpActivity<V, P> {

    protected View emptyLayout;
    protected FlatButton layoutReload;
    protected Toolbar mToolbar;
    protected TextView mToolBarTitle;

    private boolean canBack=true;

    public void setCanBack(boolean canBack) {
        this.canBack = canBack;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBar();
        if(getLayoutId()==0){
            super.setContentView(R.layout.activity_base);
        }else{
            super.setContentView(getLayoutId());
        }
        afterCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        RelativeLayout rootLayout= (RelativeLayout) findViewById(R.id.root_view);
        emptyLayout=findViewById(R.id.layoutEmpty);
        layoutReload= (FlatButton) emptyLayout.findViewById(R.id.layoutReload);
        emptyLayout.setVisibility(View.GONE);
        if(rootLayout==null) return;
        rootLayout.addView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolBar();
    }

    private void initToolBar(){
        mToolbar= (Toolbar) findViewById(R.id.toolBar);
        if(mToolbar!=null){
            setSupportActionBar(mToolbar);
            mToolBarTitle= (TextView) mToolbar.findViewById(R.id.toolBarTitle);
            if(mToolBarTitle==null){
                mToolBarTitle= (TextView) findViewById(R.id.toolBarTitle);
            }
            if(mToolBarTitle!=null&&getSupportActionBar()!=null){
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
            if(canBack){
                mToolbar.setNavigationIcon(R.drawable.ic_back);
            }
        }
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if(mToolBarTitle!=null){
            mToolBarTitle.setText(title);
        }
    }
    public Toolbar getmToolbar() {
        return mToolbar;
    }
    private void initSystemBar(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(true);
            SystemBarTintManager tintManager=new SystemBarTintManager(this);
            //enable status bar tint
            tintManager.setStatusBarTintEnabled(true);
            //enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true);
            // set a custom tint color for all system bars
            tintManager.setTintColor(R.color.colorPrimary);
            tintManager.setStatusBarTintColor(R.color.colorPrimaryDark);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on){
        Window window=getWindow();
        WindowManager.LayoutParams winParams=window.getAttributes();
        final int bits=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if(on){
            winParams.flags|=bits;
        }else{
            winParams.flags&=~bits;
        }
        window.setAttributes(winParams);
    }

    protected abstract int getLayoutId();
    protected abstract void afterCreate(Bundle savedInstanceState);
}
