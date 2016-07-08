package com.android.ice.zhihudaily.support.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.android.ice.zhihudaily.R;
import com.android.ice.zhihudaily.support.utils.Utils;

/**
 * Created by yangchj on 2016/3/3.
 * email:ycj0808@126.com
 */
public class FlatButton extends TextView {

	public FlatButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0, 0);
	}

	public FlatButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs, defStyleAttr, 0);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public FlatButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context, attrs, defStyleAttr, defStyleRes);
	}

	private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		setBackgroundCompat(Utils.resolveDrawable(context, R.attr.flat_text));

		final int materialBlue = Color.parseColor("#ff0000");
		int widgetColor = Utils.resolveColor(context, R.attr.colorAccent, materialBlue);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			widgetColor = Utils.resolveColor(context, android.R.attr.colorAccent, widgetColor);
		}
		setTextColor(widgetColor);
	}

	private void setBackgroundCompat(Drawable d) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
			//noinspection deprecation
			setBackgroundDrawable(d);
		} else {
			setBackground(d);
		}
	}

}