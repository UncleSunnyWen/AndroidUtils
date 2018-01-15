package com.unclesunny.lottieutil;

import android.support.annotation.RawRes;
import android.text.TextUtils;
import android.util.SparseArray;

import com.airbnb.lottie.LottieComposition;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Lottie缓存工具类(全局缓存)，不同于LottieAnimationView自带的缓存
 * <p>
 * (
 * LottieAnimationView 自带一个可选的缓存机制 ——
 * LottieAnimationView#setAnimation(final String animationName, final CacheStrategy cacheStrategy)
 * LottieAnimationView#setAnimation(@RawRes final int animationResId, final CacheStrategy cacheStrategy)
 * )
 *
 * @author wenshijie
 * @date 17-12-13
 */

public class LottieCache {

    /**
     * Caching strategy for compositions that will be reused frequently.
     * Weak or Strong indicates the GC reference strength of the composition in the cache.
     */
    public enum CacheStrategy {
        None,
        Weak,
        Strong
    }

    private static final SparseArray<LottieComposition> RAW_RES_STRONG_REF_CACHE = new SparseArray<>();
    private static final SparseArray<WeakReference<LottieComposition>> RAW_RES_WEAK_REF_CACHE =
            new SparseArray<>();
    private static final Map<String, LottieComposition> ASSET_STRONG_REF_CACHE = new HashMap<>();
    private static final Map<String, WeakReference<LottieComposition>> ASSET_WEAK_REF_CACHE =
            new HashMap<>();
    private static LottieCache instance;

    private LottieCache() {
    }

    public static synchronized LottieCache getInstance() {
        if (instance == null) {
            instance = new LottieCache();
        }
        return instance;
    }

    public void putCache(@RawRes final int animationResId, LottieComposition composition,
                         final CacheStrategy cacheStrategy) {
        if (animationResId == -1 || composition == null || cacheStrategy == null) {
            return;
        }

        if (cacheStrategy == CacheStrategy.Strong) {
            RAW_RES_STRONG_REF_CACHE.put(animationResId, composition);
        } else if (cacheStrategy == CacheStrategy.Weak) {
            RAW_RES_WEAK_REF_CACHE.put(animationResId, new WeakReference<>(composition));
        }
    }

    public void putCache(final String animationName, LottieComposition composition,
                         final CacheStrategy cacheStrategy) {
        if (TextUtils.isEmpty(animationName) || composition == null || cacheStrategy == null) {
            return;
        }

        if (cacheStrategy == CacheStrategy.Strong) {
            ASSET_STRONG_REF_CACHE.put(animationName, composition);
        } else if (cacheStrategy == CacheStrategy.Weak) {
            ASSET_WEAK_REF_CACHE.put(animationName, new WeakReference<>(composition));
        }
    }

    public LottieComposition getCache(@RawRes final int animationResId) {
        if (animationResId == -1) {
            return null;
        }

        if (RAW_RES_WEAK_REF_CACHE.indexOfKey(animationResId) > 0) {
            WeakReference<LottieComposition> compRef = RAW_RES_WEAK_REF_CACHE.get(animationResId);
            LottieComposition ref = compRef.get();
            if (ref != null) {
                return ref;
            }
        } else if (RAW_RES_STRONG_REF_CACHE.indexOfKey(animationResId) > 0) {
            return RAW_RES_STRONG_REF_CACHE.get(animationResId);
        }

        return null;
    }

    public LottieComposition getCache(final String animationName) {
        if (TextUtils.isEmpty(animationName)) {
            return null;
        }

        if (ASSET_WEAK_REF_CACHE.containsKey(animationName)) {
            WeakReference<LottieComposition> compRef = ASSET_WEAK_REF_CACHE.get(animationName);
            LottieComposition ref = compRef.get();
            if (ref != null) {
                return ref;
            }
        } else if (ASSET_STRONG_REF_CACHE.containsKey(animationName)) {
            return ASSET_STRONG_REF_CACHE.get(animationName);
        }

        return null;
    }

    public void removeCache(@RawRes final int animationResId) {
        RAW_RES_WEAK_REF_CACHE.remove(animationResId);
        RAW_RES_STRONG_REF_CACHE.remove(animationResId);
    }

    public void removeCache(final String animationName) {
        ASSET_WEAK_REF_CACHE.remove(animationName);
        ASSET_STRONG_REF_CACHE.remove(animationName);
    }

    public void clear() {
        RAW_RES_WEAK_REF_CACHE.clear();
        RAW_RES_STRONG_REF_CACHE.clear();
        ASSET_WEAK_REF_CACHE.clear();
        ASSET_STRONG_REF_CACHE.clear();
    }

}
