package com.android.ice.zhihudaily.support.imageloader;

import android.content.Context;

import com.android.ice.zhihudaily.support.imageloader.glide.GlideFactory;
import com.android.ice.zhihudaily.support.imageloader.glide.GlideWrapper;

import java.io.File;

/**
 * Created by yangchj on 2016/8/18 0018.
 * email:yangchj@neusoft.com
 */
public class ImageLoaderUtils {
    /**
     * 配置不同的图片加载器
     * @param context
     * @param file
     * @param debug
     */
    public static void init(Context context, File file, boolean debug){
//         ImageLoaderWrapper.initDefault(context, file,debug);
        GlideWrapper.init(context);
    }

    public static ImageFactory getImageFactory(int type){
        switch (type){
            case 1:
                return new GlideFactory();
        }
        return null;
    }
}
