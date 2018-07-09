package com.chen.rocketmq_chat.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

/**
 * Created by ${BaLe} on 2018/7/9.
 */
public class UIutils {
    private static float sNocompatDensity;
    private static float sNocompatScaleDensity;

    public static void setCustomDensity(@Nullable Activity activity, @Nullable final Application application) {
        final DisplayMetrics appdisplayMetricsa = application.getResources().getDisplayMetrics();
        if (sNocompatDensity == 0) {
            sNocompatDensity = appdisplayMetricsa.density;
            sNocompatScaleDensity = appdisplayMetricsa.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNocompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        final float targetDensity = appdisplayMetricsa.widthPixels / 360;
        final float targetScaleDensity = targetDensity * (sNocompatScaleDensity / sNocompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);
        appdisplayMetricsa.density = targetDensity;
        appdisplayMetricsa.scaledDensity = targetScaleDensity;
        appdisplayMetricsa.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;

    }
}
