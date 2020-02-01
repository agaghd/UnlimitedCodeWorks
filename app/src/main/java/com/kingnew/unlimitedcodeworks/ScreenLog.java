package com.kingnew.unlimitedcodeworks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author wjy
 * time ： 2019/7/4
 * desc ： 屏幕直接输出Log的工具。
 */
public class ScreenLog {

    private static final String SCREEN_LOGGER_TAG = "ScreenLoggerTag";

    public static void print(Context context, CharSequence msg) {
        print(context, msg, false, false);
    }

    public static void print(Context context, CharSequence msg, boolean clearMsg) {
        print(context, msg, clearMsg, false);
    }

    public static void println(Context context, CharSequence msg) {
        print(context, msg, false, true);
    }

    public static void println(Context context, CharSequence msg, boolean clearMsg) {
        print(context, msg, clearMsg, true);
    }

    /**
     * 直接向屏幕输出的内容
     *
     * @param context  Activity
     * @param msg      内容，可以是富文本
     * @param clearMsg 是否清空已输出内容，
     * @param lineFeed 换行
     */
    public static void print(final Context context, final CharSequence msg,
                             final boolean clearMsg, final boolean lineFeed) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            final ViewGroup content = activity.findViewById(android.R.id.content);
            content.post(new Runnable() {
                @Override
                public void run() {
                    TextView screenLogger = createScreenLogger(content);
                    if (screenLogger == null) {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                    } else {
                        if (clearMsg) {
                            screenLogger.setText(msg);
                        } else {
                            screenLogger.append(msg);
                            if (lineFeed) {
                                screenLogger.append("\n");
                            }
                        }
                    }
                }
            });
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }

    private static TextView createScreenLogger(ViewGroup content) {
        TextView screenLogger;
        //看logger是否已创建
        for (int i = 0; i < content.getChildCount(); i++) {
            View child = content.getChildAt(i);
            if (child instanceof TextView && SCREEN_LOGGER_TAG.equals(child.getTag())) {
                return (TextView) child;
            }
        }
        //logger未创建，进行创建
        screenLogger = new TextView(content.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 60;
        layoutParams.bottomMargin = 60;
        screenLogger.setPadding(32, 0, 32, 0);
        screenLogger.setTextSize(14);
        screenLogger.setTextColor(Color.WHITE);
        screenLogger.setBackgroundColor(0X80000000);
        screenLogger.setLayoutParams(layoutParams);
        screenLogger.setTag(SCREEN_LOGGER_TAG);
        content.addView(screenLogger);
        return screenLogger;
    }
}
