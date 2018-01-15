package com.unclesunny.floatutil;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.Set;

/**
 * Created by wenshijie on 17/12/13.
 */
public class FloatWindowManager {

    static final String TAG = "FloatWindowManager";

    private static FloatWindowManager instance;
    private WindowManager mSysWindowManager;

    private FloatWindowManager(Context context) {
        this.mSysWindowManager = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
    }

    public static synchronized FloatWindowManager getInstance(Context context) {
        if (instance == null) {
            instance = new FloatWindowManager(context);
        }
        return instance;
    }

    public void addView(View view, WindowManager.LayoutParams params) {
        if (WindowCache.getInstance().isCached(view)) {
            return;
        }
        try {
            mSysWindowManager.addView(view, params);
            WindowCache.getInstance().putCache(view);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void removeView(View view) {
        try {
            mSysWindowManager.removeView(view);
            WindowCache.getInstance().removeCache(view);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void updateViewLayout(View view, WindowManager.LayoutParams params) {
        if (!WindowCache.getInstance().isCached(view)) {
            return;
        }
        try {
            mSysWindowManager.updateViewLayout(view, params);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void removeAllViews() {
        Set<Integer> ids = WindowCache.getInstance().getCacheIds();
        for (Integer id : ids) {
            View view = WindowCache.getInstance().getCache(id);
            removeView(view);
        }
    }

    /**
     * Bring the window corresponding to this id in front of all other windows.
     * The window may flicker as it is removed and restored by the system.
     *
     * @param view The view to bring to the front.
     */
    private void bringToFront(View view) {
        if (view == null) {
            return;
        }
        if (!WindowCache.getInstance().isCached(view)) {
            return;
        }
        WindowManager.LayoutParams params = (WindowManager.LayoutParams) view.getLayoutParams();
        // remove from window Manager then add back
        removeView(view);
        addView(view, params);
    }

}
