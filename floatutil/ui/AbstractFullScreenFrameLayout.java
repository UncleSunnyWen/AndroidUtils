package com.unclesunny.floatutil.ui.abstractview;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.unclesunny.floatutil.ui.IBaseFloatView;
import com.unclesunny.floatutil.util.FloatUtil;
import com.orhanobut.logger.Logger;


/**
 * 全屏浮窗，监听home键，多任务键，返回键
 *
 * @author wenshijie
 * @date 17-12-13
 */

public abstract class AbstractFullScreenFrameLayout extends FrameLayout implements IBaseFloatView {

    OnKeyListener mOnKeyListener = null;

    private IntentFilter mReasonKeyFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

    private BroadcastReceiver mReasonKeyListenerReceiver = new BroadcastReceiver() {
        final String SYSTEM_DIALOG_REASON_KEY = "reason";
        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
        final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);

            if (!action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS) || reason == null) {
                return;
            }

            if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                onKeyHome();
            } else if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
                onKeyRecentApps();
            }
        }
    };

    public AbstractFullScreenFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public AbstractFullScreenFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractFullScreenFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public AbstractFullScreenFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        || keyCode == KeyEvent.KEYCODE_SETTINGS) {
                    onKeyBack();
                }
                return false;
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getContext().registerReceiver(mReasonKeyListenerReceiver, mReasonKeyFilter);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(mReasonKeyListenerReceiver);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Logger.t("Test_getBitmap").d("dispatchKeyEvent:%s", event.getKeyCode());
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                || event.getKeyCode() == KeyEvent.KEYCODE_SETTINGS) {
            if (mOnKeyListener != null) {
                mOnKeyListener.onKey(this, KeyEvent.KEYCODE_BACK, event);
                return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }

    @Override
    public void setOnKeyListener(OnKeyListener l) {
        mOnKeyListener = l;

        super.setOnKeyListener(l);
    }

    public WindowManager.LayoutParams getDefaultLayoutParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        // 对齐方式
        params.gravity = Gravity.START | Gravity.TOP;
        // 浮窗层级
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        // 背景透明
        params.format = PixelFormat.RGBA_8888;
        // 全屏显示
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        // 让window占满整个手机屏幕，不留任何边界（border）
        params.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //设置悬浮窗的横竖屏,会影响屏幕方向,只要悬浮窗不消失,屏幕方向就会一直保持,可以强制屏幕横屏或竖屏
        params.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        return params;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            hideNavigateBar();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void hideNavigateBar() {
        setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    @Override
    public void attachToWindow() {
        FloatUtil.addView(getContext(), this, getDefaultLayoutParams());
    }


    public void updateToWindow() {
        WindowManager.LayoutParams params = getDefaultLayoutParams();
        params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
        FloatUtil.updateViewLayout(getContext(), this, params);
    }

    @Override
    public void detachFromWindow() {
        FloatUtil.removeView(getContext(), this);
    }

    protected abstract void onKeyBack();

    protected abstract void onKeyHome();

    protected abstract void onKeyRecentApps();

}
