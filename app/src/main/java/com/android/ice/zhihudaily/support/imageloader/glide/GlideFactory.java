package com.android.ice.zhihudaily.support.imageloader.glide;

import com.android.ice.zhihudaily.support.imageloader.DisplayOption;
import com.android.ice.zhihudaily.support.imageloader.ImageFactory;
import com.android.ice.zhihudaily.support.imageloader.ImageLoaderWrapper;

/**
 * Created by yangchj on 2016/8/18 0018.
 * email:yangchj@neusoft.com
 */
public class GlideFactory implements ImageFactory {
    @Override
    public ImageLoaderWrapper createImageLoader() {
        return new GlideImageLoader();
    }

    @Override
    public DisplayOption createImageConfig() {
        return new GlideDisplayOption();
    }
}
