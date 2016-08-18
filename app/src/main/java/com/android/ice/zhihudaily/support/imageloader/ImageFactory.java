package com.android.ice.zhihudaily.support.imageloader;

/**
 * Created by yangchj on 2016/8/18 0018.
 * email:yangchj@neusoft.com
 */
public interface ImageFactory {
    ImageLoaderWrapper createImageLoader();
    DisplayOption createImageConfig();
}
