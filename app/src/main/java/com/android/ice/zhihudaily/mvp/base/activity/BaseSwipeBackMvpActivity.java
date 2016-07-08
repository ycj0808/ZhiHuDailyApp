package com.android.ice.zhihudaily.mvp.base.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.support.swipeback.SwipeBackLayout;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * 基类继承了SwipeBack类
 * Created by yangchj on 2016/7/7 0007.
 * email:yangchj@neusoft.com
 */
public abstract class BaseSwipeBackMvpActivity<V extends MvpView,P extends MvpPresenter<V>> extends MvpSwipeBackActivity<V, P> {

    protected SwipeBackLayout mSwipeBackLayout;

    /**
     * 返回按键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            scrollToFinishActivity();
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBar();
        if(getLayoutId()==0){

        }else{
            super.setContentView(getLayoutId());
        }

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
