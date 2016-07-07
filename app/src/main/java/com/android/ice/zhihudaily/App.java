package com.android.ice.zhihudaily;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by yangchj on 2016/7/7 0007.
 * email:yangchj@neusoft.com
 */
public class App extends Application{

    private static App _context;

    @Override
    public void onCreate() {
        super.onCreate();
        _context=this;
        Logger.init("ZhiHuDaily");
    }

    public static App getInstance(){
        return _context;
    }
}
