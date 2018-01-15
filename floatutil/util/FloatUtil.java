package com.unclesunny.floatutil.util;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import com.unclesunny.floatutil.FloatLayoutParams;
import com.unclesunny.floatutil.FloatWindowManager;


/**
 * 显示浮窗的工具类
 * <p>
 * Created by wenshijie on 17/12/13.
 */
public class FloatUtil {

    private static final String TAG = "FloatUtil";

    /**
     * 添加浮窗，默认对齐方式：左上,默认浮窗层级：TYPE_PHONE
     * 需要权限：SYSTEM_ALERT_WINDOW。
     *
     * @param context
     * @param view
     */
    public static void addView(Context context, View view) {
        WindowManager.LayoutParams params = FloatLayoutParams.newLayoutParams();
        addView(context, view, params);
    }


    /**
     * 添加浮窗，默认对齐方式：左上,默认浮窗层级：TYPE_PHONE。
     *
     * @param context
     * @param view
     * @param type    浮窗层级
     */
    public static void addView(Context context, View view, int type) {
        WindowManager.LayoutParams params = FloatLayoutParams.newLayoutParams(type);
        addView(context, view, params);
    }

    /**
     * 添加浮窗，默认浮窗层级：TYPE_PHONE。
     *
     * @param context
     * @param view
     * @param x       横坐标
     * @param y       纵坐标
     */
    public static void addView(Context context, View view, int x, int y) {
        WindowManager.LayoutParams params = FloatLayoutParams.newLayoutParams(x, y);
        addView(context, view, params);
    }

    /**
     * 添加浮窗
     *
     * @param context
     * @param view
     * @param type    浮窗层级
     * @param x       横坐标
     * @param y       纵坐标
     */
    public static void addView(Context context, View view, int type, int x, int y) {
        WindowManager.LayoutParams params = FloatLayoutParams.newLayoutParams(type, x, y);
        addView(context, view, params);
    }

    /**
     * 添加浮窗，默认浮窗层级：TYPE_PHONE。
     *
     * @param context
     * @param view
     * @param x       横坐标
     * @param y       纵坐标
     * @param width   浮窗的宽
     * @param height  浮窗的高
     */
    public static void addView(Context context, View view, int x, int y, int width, int height) {
        WindowManager.LayoutParams params = FloatLayoutParams.newLayoutParams(x, y, width, height);
        addView(context, view, params);
    }

    /**
     * 添加浮窗
     *
     * @param context
     * @param view
     * @param type    浮窗层级
     * @param x       横坐标
     * @param y       纵坐标
     * @param width   浮窗的宽
     * @param height  浮窗的高
     */
    public static void addView(Context context, View view, int type, int x, int y, int width, int height) {
        WindowManager.LayoutParams params = FloatLayoutParams.newLayoutParams(type, x, y, width, height);
        addView(context, view, params);
    }

    /**
     * 添加浮窗
     *
     * @param context
     * @param view
     * @param params
     */
    public static void addView(Context context, View view, WindowManager.LayoutParams params) {
        FloatWindowManager.getInstance(context).addView(view, params);
    }

    /**
     * 更新浮窗
     *
     * @param context
     * @param view
     * @param x       横坐标
     * @param y       纵坐标
     */
    public static void updateViewLayout(Context context, View view, int x, int y) {
        WindowManager.LayoutParams params = (WindowManager.LayoutParams) view.getLayoutParams();
        params.x = x;
        params.y = y;
        updateViewLayout(context, view, params);
    }

    /**
     * 更新浮窗
     *
     * @param context
     * @param view
     * @param x       横坐标
     * @param y       纵坐标
     * @param width   浮窗的宽
     * @param height  浮窗的高
     */
    public static void updateViewLayout(Context context, View view, int x, int y, int width, int height) {
        WindowManager.LayoutParams params = (WindowManager.LayoutParams) view.getLayoutParams();
        params.x = x;
        params.y = y;
        params.width = width;
        params.height = height;
        updateViewLayout(context, view, params);
    }

    /**
     * 更新浮窗
     *
     * @param context
     * @param view
     * @param params
     */
    public static void updateViewLayout(Context context, View view, WindowManager.LayoutParams params) {
        FloatWindowManager.getInstance(context).updateViewLayout(view, params);
    }

    /**
     * 移除浮窗
     *
     * @param context
     * @param view
     */
    public static void removeView(Context context, View view) {
        FloatWindowManager.getInstance(context).removeView(view);
    }

    /**
     * 移除全部浮窗
     *
     * @param context
     */
    public static void removeAllViews(Context context) {
        FloatWindowManager.getInstance(context).removeAllViews();
    }

}
