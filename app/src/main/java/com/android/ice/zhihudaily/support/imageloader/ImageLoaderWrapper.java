package com.android.ice.zhihudaily.support.imageloader;

import android.widget.ImageView;
import java.io.File;

/**
 * 图片加载功能接口
 * Created by yangchj on 2016/8/18 0018.
 * email:yangchj@neusoft.com
 */
public interface ImageLoaderWrapper {

    /**
     * 显示图片
     * @param imageView
     * @param imageFile
     * @param options
     */
    public void displayImage(ImageView imageView, File imageFile,DisplayOption options);

    /**
     * 显示图片
     * @param imageView
     * @param imageUrl
     * @param option
     */
    public void displayImage(ImageView imageView, String imageUrl,DisplayOption option);

    /**
     * 显示图片
     * @param imageView
     * @param resId
     * @param option
     */
    public void displayImage(ImageView imageView, int resId,DisplayOption option);

    /**
     * 清除指定缓存
     * @param imageUrl
     */
    public void cleanImageCache(String imageUrl);

    /**
     * 清楚缓存
     */
    public void cleanImageCache();
}
