package com.yscoco.uppernest.commonlibrary.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public class CustomViewPager extends ViewPager {

    private boolean enable = false;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (enable) {//触摸事件不触发
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (enable) {//不处理触摸拦截事件
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (enable) {
            return super.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setPagingEnabled(boolean enabled) {
        enable = enabled;
    }

}
