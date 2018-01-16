package com.unclesunny.floatutil;


import android.util.SparseArray;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

/**
 * 浮窗缓存管理
 * <p>
 * Created by wenshijie on 17/12/13.
 */
public class WindowCache {

    private volatile static WindowCache sInstance = null;

    private SparseArray<View> sWindows;

    private WindowCache() {
        sWindows = new SparseArray<>();
    }

    public static WindowCache getInstance() {
        if (sInstance == null) {
            synchronized (WindowCache.class) {
                if (sInstance == null) {
                    sInstance = new WindowCache();
                }
            }
        }
        return sInstance;
    }


    public boolean isCached(View view) {
        if (view == null) {
            return false;
        }
        return getCache(view.hashCode()) != null;
    }

    public View getCache(int id) {
        return sWindows.get(id);
    }

    public void putCache(View view) {
        if (view == null) {
            return;
        }

        sWindows.put(view.hashCode(), view);
    }

    public void removeCache(int id) {
        sWindows.remove(id);
    }

    public void removeCache(View view) {
        if (view == null) {
            return;
        }
        removeCache(view.hashCode());
    }

    public int getCacheSize() {
        return sWindows.size();
    }

    public Set<Integer> getCacheIds() {
        Set<Integer> keys = new HashSet<>();
        for (int i = 0; i < sWindows.size(); i++) {
            keys.add(sWindows.keyAt(i));
        }
        return keys;
    }

    /*public Set<View> getCacheWindows() {
        Set<View> values = new HashSet<>();
        for (int i = 0; i < sWindows.size(); i++) {
            values.add(sWindows.valueAt(i));
        }
        return values;
    }*/

    public void clear() {
        sWindows.clear();
    }

}
