package com.android.ice.zhihudaily.support.imageloader.glide;

import com.android.ice.zhihudaily.support.imageloader.DisplayOption;

/**
 * Created by yangchj on 2016/8/18 0018.
 * email:yangchj@neusoft.com
 */
public class GlideDisplayOption implements DisplayOption {

    //default true
    public boolean isSupportMemoryCache;
    public boolean isSupportDiskCache;
    public int defaulRes;
    public int loadingRes;
    public int failRes;
    public int duration;

    public GlideDisplayOption() {
        this.isSupportMemoryCache = true;
        this.isSupportDiskCache = true;
        this.loadingRes = 0;
        this.failRes = 0;
        this.duration = 0;
    }

    @Override
    public void setDefaulRes(int defaulRes) {
        this.defaulRes = defaulRes;
    }

    @Override
    public void setLoadingRes(int loadingRes) {
        this.loadingRes = loadingRes;
    }

    @Override
    public void setFailRes(int failRes) {
        this.failRes = failRes;
    }

    @Override
    public void setsupportMemoryCache(boolean flag) {
        this.isSupportMemoryCache = flag;
    }

    @Override
    public void setsupportDiskCache(boolean flag) {
        this.isSupportDiskCache = flag;
    }

    @Override
    public void setFadeIn(int duration) {
        this.duration = duration;
    }

    public boolean isSupportMemoryCache() {
        return isSupportMemoryCache;
    }

    public boolean isSupportDiskCache() {
        return isSupportDiskCache;
    }

    public int getDefaulRes() {
        return defaulRes;
    }

    public int getLoadingRes() {
        return loadingRes;
    }

    public int getFailRes() {
        return failRes;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public Object get() {
        return this;
    }
}
