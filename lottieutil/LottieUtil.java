package com.unclesunny.lottieutil;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;

import com.airbnb.lottie.Cancellable;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;


/**
 * @author wenshijie
 * @date 17-12-13
 */

public class LottieUtil {

    public static void loadAnimation(Context context, @RawRes final int animationResId) {
        loadAnimation(context, animationResId, LottieCache.CacheStrategy.Strong);
    }

    public static void loadAnimation(Context context, @RawRes final int animationResId, final LottieCache.CacheStrategy cacheStrategy) {
        LottieComposition composition = LottieCache.getInstance().getCache(animationResId);
        if (composition != null) {
            return;
        }

        Cancellable compositionLoader = LottieComposition.Factory.fromRawFile(context, animationResId,
                new OnCompositionLoadedListener() {
                    @Override
                    public void onCompositionLoaded(LottieComposition composition) {
                        if (composition != null) {
                            LottieCache.getInstance().putCache(animationResId, composition, cacheStrategy);
                        }
                    }
                });
    }

    public static void loadAnimation(Context context, final String animationName) {
        loadAnimation(context, animationName, LottieCache.CacheStrategy.Strong);
    }

    public static void loadAnimation(Context context, final String animationName, final LottieCache.CacheStrategy cacheStrategy) {
        LottieComposition composition = LottieCache.getInstance().getCache(animationName);
        if (composition != null) {
            return;
        }
        Cancellable compositionLoader = LottieComposition.Factory.fromAssetFileName(context, animationName,
                new OnCompositionLoadedListener() {
                    @Override
                    public void onCompositionLoaded(@Nullable LottieComposition composition) {
                        if (composition != null) {
                            LottieCache.getInstance().putCache(animationName, composition, cacheStrategy);
                        }
                    }
                });
    }

    public static void setAnimation(Context context, final LottieAnimationView animationView, @RawRes final int animationResId) {
        setAnimation(context, animationView, null, animationResId);
    }

    public static void setAnimation(Context context, final LottieAnimationView animationView, final String imageFolder, @RawRes final int animationResId) {
        if (animationView == null) {
            return;
        }
        LottieComposition composition = LottieCache.getInstance().getCache(animationResId);
        if (composition != null) {
            if (imageFolder != null && imageFolder.length() > 0) {
                animationView.setImageAssetsFolder(imageFolder);
            }
            animationView.setComposition(composition);
        } else {
            Cancellable compositionLoader = LottieComposition.Factory.fromRawFile(context, animationResId,
                    new OnCompositionLoadedListener() {
                        @Override
                        public void onCompositionLoaded(LottieComposition composition) {
                            if (composition == null) {
                                return;
                            }
                            if (imageFolder != null && imageFolder.length() > 0) {
                                animationView.setImageAssetsFolder(imageFolder);
                            }
                            animationView.setComposition(composition);
                            LottieCache.getInstance().putCache(animationResId, composition, LottieCache.CacheStrategy.Strong);
                        }
                    });
        }
    }

    public static void setAnimation(Context context, final LottieAnimationView animationView, final String animationName) {
        setAnimation(context, animationView, null, animationName);
    }

    public static void setAnimation(Context context, final LottieAnimationView animationView, final String imageFolder, final String animationName) {
        if (animationView == null) {
            return;
        }
        LottieComposition composition = LottieCache.getInstance().getCache(animationName);
        if (composition != null) {
            if (imageFolder != null && imageFolder.length() > 0) {
                animationView.setImageAssetsFolder(imageFolder);
            }
            animationView.setComposition(composition);
        } else {
            Cancellable compositionLoader = LottieComposition.Factory.fromAssetFileName(context, animationName,
                    new OnCompositionLoadedListener() {
                        @Override
                        public void onCompositionLoaded(LottieComposition composition) {
                            if (composition == null) {
                                return;
                            }
                            if (imageFolder != null && imageFolder.length() > 0) {
                                animationView.setImageAssetsFolder(imageFolder);
                            }
                            animationView.setComposition(composition);
                            LottieCache.getInstance().putCache(animationName, composition, LottieCache.CacheStrategy.Strong);
                        }
                    });
        }
    }

    public static void playAnimation(Context context, final LottieAnimationView animationView, @RawRes final int animationResId) {
        playAnimation(context, animationView, null, animationResId);
    }

    public static void playAnimation(Context context, final LottieAnimationView animationView, final String imageFolder, @RawRes final int animationResId) {
        if (animationView == null) {
            return;
        }
        LottieComposition composition = LottieCache.getInstance().getCache(animationResId);
        if (composition != null) {
            if (imageFolder != null && imageFolder.length() > 0) {
                animationView.setImageAssetsFolder(imageFolder);
            }
            animationView.setComposition(composition);
            animationView.playAnimation();
        } else {
            Cancellable compositionLoader = LottieComposition.Factory.fromRawFile(context, animationResId,
                    new OnCompositionLoadedListener() {
                        @Override
                        public void onCompositionLoaded(LottieComposition composition) {
                            if (composition == null) {
                                return;
                            }
                            if (imageFolder != null && imageFolder.length() > 0) {
                                animationView.setImageAssetsFolder(imageFolder);
                            }
                            animationView.setComposition(composition);
                            animationView.playAnimation();
                            LottieCache.getInstance().putCache(animationResId, composition, LottieCache.CacheStrategy.Strong);
                        }
                    });
        }
    }


    public static void playAnimation(Context context, final LottieAnimationView animationView, final String animationName) {
        playAnimation(context, animationView, null, animationName);
    }

    public static void playAnimation(Context context, final LottieAnimationView animationView, final String imageFolder, final String animationName) {
        if (animationView == null) {
            return;
        }
        LottieComposition composition = LottieCache.getInstance().getCache(animationName);
        if (composition != null) {
            if (imageFolder != null && imageFolder.length() > 0) {
                animationView.setImageAssetsFolder(imageFolder);
            }
            animationView.setComposition(composition);
            animationView.playAnimation();
        } else {
            Cancellable compositionLoader = LottieComposition.Factory.fromAssetFileName(context, animationName,
                    new OnCompositionLoadedListener() {
                        @Override
                        public void onCompositionLoaded(LottieComposition composition) {
                            if (composition == null) {
                                return;
                            }
                            if (imageFolder != null && imageFolder.length() > 0) {
                                animationView.setImageAssetsFolder(imageFolder);
                            }
                            animationView.setComposition(composition);
                            animationView.playAnimation();
                            LottieCache.getInstance().putCache(animationName, composition, LottieCache.CacheStrategy.Strong);
                        }
                    });
        }
    }

    public static void playAnimation(LottieAnimationView animationView, LottieComposition composition) {
        if (animationView == null || composition == null) {
            return;
        }
        animationView.setComposition(composition);
        animationView.playAnimation();
    }

    public static void playAnimation(LottieAnimationView animationView, String imageFolder, LottieComposition composition) {
        if (animationView == null || composition == null) {
            return;
        }
        if (imageFolder != null && imageFolder.length() > 0) {
            animationView.setImageAssetsFolder(imageFolder);
        }
        animationView.setComposition(composition);
        animationView.playAnimation();
    }

}
