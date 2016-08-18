package com.android.ice.zhihudaily.support.imageloader;

/**
 * Created by yangchj on 2016/8/18 0018.
 * email:yangchj@neusoft.com
 */
public interface DisplayOption {
    void setDefaulRes(int defaulRes);
    void setLoadingRes(int loadingRes);
    void setFailRes(int failRes);
    void setsupportMemoryCache(boolean flag);
    void setsupportDiskCache(boolean flag);
    void setFadeIn(int duration);
    Object get();
}
