package com.unclesunny.floatutil;

import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * 创建浮窗LayoutParams参数的工具类
 * <p>
 * Created by wenshijie on 17/12/13.
 */
public class FloatLayoutParams {

    /**
     * 默认对齐方式：左上，默认浮窗层级：TYPE_PHONE,需要权限：SYSTEM_ALERT_WINDOW。
     */
    public static WindowManager.LayoutParams newLayoutParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        // 对齐方式
        params.gravity = Gravity.START | Gravity.TOP;
        // 浮窗层级
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        // 背景透明
        params.format = PixelFormat.RGBA_8888;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        /*// 全屏显示
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;*/

        // 让window不能获得焦点，这样用户快就不能向该window发送按键事件及按钮事件
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 让window占满整个手机屏幕，不留任何边界（border）
        params.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
        // window大小不再不受手机屏幕大小限制，即window可能超出屏幕之外，这时部分内容在屏幕之外
        //params.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        return params;
    }

    public static WindowManager.LayoutParams newLayoutParams(int type) {
        WindowManager.LayoutParams params = newLayoutParams();
        params.type = type;
        return params;
    }

    public static WindowManager.LayoutParams newLayoutParams(int x, int y) {
        WindowManager.LayoutParams params = newLayoutParams();
        params.x = x;
        params.y = y;
        return params;
    }

    public static WindowManager.LayoutParams newLayoutParams(int type, int x, int y) {
        WindowManager.LayoutParams params = newLayoutParams();
        params.type = type;
        params.x = x;
        params.y = y;
        return params;
    }

    public static WindowManager.LayoutParams newLayoutParams(int x, int y, int width, int height) {
        WindowManager.LayoutParams params = newLayoutParams();
        params.x = x;
        params.y = y;
        params.width = width;
        params.height = height;
        return params;
    }

    public static WindowManager.LayoutParams newLayoutParams(int type, int x, int y, int width, int height) {
        WindowManager.LayoutParams params = newLayoutParams();
        params.type = type;
        params.x = x;
        params.y = y;
        params.width = width;
        params.height = height;
        return params;
    }

}