package com.kingnew.unlimitedcodeworks.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

/**
 * author : wjy
 * time   : 2019/04/19
 * desc   : 对View使用的一些工具方法集合
 */
public class ViewUtil {

    /**
     * 将一个ViewGroup和其所有的子View给disable掉
     *
     * @param viewGroup 目标ViewGroup
     */
    @UiThread
    public static void disableAllViewsInViewGroup(ViewGroup viewGroup) {
        disableAllViewsInViewGroup(viewGroup, true);
    }

    /**
     * 将一个ViewGroup和其所有的子View给disable掉
     *
     * @param viewGroup   目标ViewGroup
     * @param disableSelf 是否disable自身
     */
    @UiThread
    public static void disableAllViewsInViewGroup(ViewGroup viewGroup, boolean disableSelf) {
        if (viewGroup == null) {
            return;
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView != null) {
                childView.setEnabled(false);
            }
            if (childView instanceof ViewGroup) {
                ViewGroup childGroup = (ViewGroup) childView;
                disableAllViewsInViewGroup(childGroup);
            }
        }
        viewGroup.setEnabled(!disableSelf);
    }

    /**
     * 设置viewPager自动滑动时的速度
     *
     * @param viewPager viewPager对象
     * @param duration  滑动一页的时间
     */
    public static void setViewPagerScrollSpeed(@NonNull ViewPager viewPager, int duration) {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            ViewPagerFixedSpeedScroller scroller = new ViewPagerFixedSpeedScroller(
                    viewPager.getContext(), new AccelerateDecelerateInterpolator());
            scroller.setmDuration(duration);
            mField.set(viewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ViewPagerFixedSpeedScroller extends Scroller {

        private int mDuration = 1500;

        public ViewPagerFixedSpeedScroller(Context context) {
            super(context);
        }

        public ViewPagerFixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }


        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setmDuration(int time) {
            mDuration = time;
        }

        public int getmDuration() {
            return mDuration;
        }
    }


    private ViewUtil() {

    }
}
