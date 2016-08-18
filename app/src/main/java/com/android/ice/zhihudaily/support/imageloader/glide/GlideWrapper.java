package com.android.ice.zhihudaily.support.imageloader.glide;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by yangchj on 2016/8/18 0018.
 * email:yangchj@neusoft.com
 */
public class GlideWrapper {
    private static GlideWrapper sDefaultInstance;
    private Context mContext;

    public GlideWrapper(Context context) {
        mContext = context;
    }

    public static GlideWrapper init(Context context) {
        if (sDefaultInstance == null) {
            sDefaultInstance = new GlideWrapper(context);
        }
        return sDefaultInstance;
    }

    public static GlideWrapper getDefalt() {
        if (sDefaultInstance == null) {
            throw new RuntimeException(
                    "Must be call init(Context) befor!");
        }
        return sDefaultInstance;
    }

    public RequestManager getGlide(){
        return Glide.with(mContext);
    }

    public void cleanCache(){
        // crash: method on a background thread
        // you should put it on threadpool
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(mContext).clearMemory();
                Glide.get(mContext).clearDiskCache();
            }
        });
    }
}
