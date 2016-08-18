package com.android.ice.zhihudaily.support.imageloader.glide;


import android.widget.ImageView;

import com.android.ice.zhihudaily.support.imageloader.DisplayOption;
import com.android.ice.zhihudaily.support.imageloader.ImageLoaderWrapper;

import java.io.File;

/**
 * Created by yangchj on 2016/8/18 0018.
 * email:yangchj@neusoft.com
 */
public class GlideImageLoader implements ImageLoaderWrapper {

    @Override
    public void displayImage(ImageView imageView, File imageFile, DisplayOption option) {
        GlideDisplayOption displayOption= (GlideDisplayOption) option.get();
        GlideWrapper.getDefalt().getGlide()
                .load(imageFile)
                .centerCrop()
                .placeholder(displayOption.loadingRes)
                .error(displayOption.defaulRes)
                .crossFade(displayOption.duration)
                .into(imageView);
    }

    @Override
    public void displayImage(ImageView imageView, String imageUrl, DisplayOption option) {
        GlideDisplayOption displayOption= (GlideDisplayOption) option.get();
        GlideWrapper.getDefalt().getGlide()
                .load(imageUrl)
                .centerCrop()
                .placeholder(displayOption.loadingRes)
                .error(displayOption.defaulRes)
                .crossFade(displayOption.duration)
                .into(imageView);
    }

    @Override
    public void displayImage(ImageView imageView, int resId, DisplayOption option) {
        GlideDisplayOption displayOption= (GlideDisplayOption) option.get();
        GlideWrapper.getDefalt().getGlide()
                .load(resId)
                .centerCrop()
                .placeholder(displayOption.loadingRes)
                .error(displayOption.defaulRes)
                .crossFade(displayOption.duration)
                .into(imageView);
    }

    @Override
    public void cleanImageCache(String imageUrl) {
        GlideWrapper.getDefalt().cleanCache();
    }

    @Override
    public void cleanImageCache() {
        GlideWrapper.getDefalt().cleanCache();
    }
}
