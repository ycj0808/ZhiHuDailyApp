package com.android.ice.zhihudaily.ui.adapter;

import android.content.Context;

import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.mvp.bean.News;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by yangchj on 16/7/11.
 */
public class LastNewsAdapter extends BGAAdapterViewAdapter<News>{

    public LastNewsAdapter(Context context) {
        super(context, R.layout.item_last_news);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, News news) {


    }
}
