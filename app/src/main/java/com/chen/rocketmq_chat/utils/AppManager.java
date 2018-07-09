package com.chen.rocketmq_chat.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 需要在application里初始化，提供一些api用于app状态判断和activity栈管理等
 *
 * @author: xp
 * @date: 2017/1/4
 */
public class AppManager {

    private static Application sContext;
    private static final int STATUS_FORCE_KILLED = -1;
    private static final int STATUS_NORMAL = 0;
    /**
     * 当前应用的状态，默认为被回收状态；
     * ps：当最小化应用后，如果被强杀或者回收了，此值会重置为默认；
     * 我们在应用的第一个界面（WelcomeActivity）修改此值为normal状态，在BaseActivity中判断此值，
     * 如果处于回收状态，则可以进行重启等炒作，否则才进行初始化等操作，以此规避回收后恢复界面时某些对象为空引起crash问题；
     */
    private int appStatus = STATUS_FORCE_KILLED;
    /**
     * 当前活动的activity数
     */
    private int activeCount = 0;
    /**
     * 应用是否在前台
     */
    private boolean isForeground = false;


    private AppManager() {
    }

    public static AppManager getInstance() {
        return InstanceHolder.sInstance;
    }

    private static class InstanceHolder {
        private static final AppManager sInstance = new AppManager();
    }

    public void init(Application application) {
        sContext = application;
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityStack.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                activeCount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                isForeground = true;
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                activeCount--;
                if (activeCount <= 0) {
                    isForeground = false;
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityStack.remove(activity);
            }
        });
    }

    /**
     * @return 全局context
     */
    public static Context appContext() {
        if (sContext == null) {
            throw new IllegalStateException("AppManager must be init in application!");
        }
        return sContext;
    }

    /**
     * 设置应用为正常状态
     */
    public void setStatusNormal() {
        this.appStatus = STATUS_NORMAL;
    }

    /**
     * @return 当前应用是否在前台
     */
    public boolean isForeground() {
        return isForeground;
    }

    /**
     * @return 当前应用是否被回收
     */
    public boolean isForceKilled() {
        return appStatus == STATUS_FORCE_KILLED;
    }

    // **********************************************

    /**
     * 跳转
     *
     * @param aty
     */
    public static void jump(Class<? extends Activity> aty) {
        Activity context = ActivityStack.getCurrentActivity();
        context.startActivity(new Intent(context, aty));
    }

    /**
     * 跳转关闭
     *
     * @param aty
     */
    public static void jumpAndFinish(Class<? extends Activity> aty) {
        Activity context = ActivityStack.getCurrentActivity();
        context.startActivity(new Intent(context, aty));
        context.finish();
    }

    /**
     * 跳转传值（一个）
     *
     * @param aty
     * @param key
     * @param value
     */
    public static void jump(Class<? extends Activity> aty, String key, Serializable value) {
        Activity context = ActivityStack.getCurrentActivity();
        context.startActivity(new Intent(context, aty).putExtra(key, value));
    }

    /**
     * 跳转传值关闭（一个）
     *
     * @param aty
     * @param key
     * @param value
     */
    public static void jumpAndFinish(Class<? extends Activity> aty, String key, Serializable value) {
        Activity context = ActivityStack.getCurrentActivity();
        context.startActivity(new Intent(context, aty).putExtra(key, value));
        context.finish();
    }

    /**
     * 跳转传值
     *
     * @param aty
     * @param map
     */
    public static void jump(Class<? extends Activity> aty, Map<String, Serializable> map) {
        Activity context = ActivityStack.getCurrentActivity();
        Intent intent = new Intent(context, aty);
        if (map != null) {
            for (String key : map.keySet()) {
                intent.putExtra(key, map.get(key));
            }
        }
        context.startActivity(intent);
    }

    /**
     * 跳转传值关闭
     *
     * @param aty
     * @param map
     */
    public static void jumpAndFinish(Class<? extends Activity> aty, Map<String, Serializable> map) {
        Activity context = ActivityStack.getCurrentActivity();
        Intent intent = new Intent(context, aty);
        if (map != null) {
            for (String key : map.keySet()) {
                intent.putExtra(key, map.get(key));
            }
        }
        context.startActivity(intent);
        context.finish();
    }

    /**
     * 跳转传值返回
     */
    public static void jumpForResult(Class<? extends Activity> aty, String key, Serializable value, int requestCode) {
        Activity context = ActivityStack.getCurrentActivity();
        context.startActivityForResult(new Intent(context, aty).putExtra(key, value), requestCode);
    }

    public static void jumpForResult(Class<? extends Activity> aty, int requestCode) {
        Activity context = ActivityStack.getCurrentActivity();
        context.startActivityForResult(new Intent(context, aty), requestCode);
    }

    public static void jumpForResult(Class<? extends Activity> aty, Map<String, ? extends Serializable> map, int requestCode) {
        Activity context = ActivityStack.getCurrentActivity();
        Intent intent = new Intent(context, aty);
        if (map != null) {
            for (String key : map.keySet()) {
                intent.putExtra(key, map.get(key));
            }
        }
        context.startActivityForResult(intent, requestCode);
    }

    public static Activity getCurrentActivity() {
        return ActivityStack.getCurrentActivity();
    }

    public static boolean isExists(Class<? extends Activity> clazz) {
        return ActivityStack.isExists(clazz);
    }

    public static void finishExcept(Class<? extends Activity> clazz) {
        ActivityStack.finishExcept(clazz);
    }

    public static void finishTargetActivity(Class<? extends Activity> clazz) {
        ActivityStack.finishTargetActivity(clazz);
    }

    public static void exitApp() {
        ActivityStack.exitApp();
    }

    /**
     * 第一次和第二次的退出间隔时间基准
     */
    private static final long EXIT_TWICE_INTERVAL = 2000;
    private static long mExitTime = 0;

    /**
     * 第二次按退出则返回true,否则返回false
     */
    public static boolean exitTwice() {
        long newExitTime = System.currentTimeMillis();
        if (newExitTime - mExitTime > EXIT_TWICE_INTERVAL) {
            mExitTime = newExitTime;
            return false;
        } else {
            return true;
        }
    }

    /**
     * 模拟activity栈，对activity进行管理
     */
    private static class ActivityStack {
        private static final LinkedList<Activity> STACK = new LinkedList<>();

        /**
         * 入栈
         */
        public static void add(Activity aty) {
            synchronized (ActivityStack.class) {
                STACK.addLast(aty);
            }
        }

        /**
         * 出栈
         */
        public static void remove(Activity aty) {
            synchronized (ActivityStack.class) {
                if (STACK.contains(aty)) {
                    STACK.remove(aty);
                }
            }
        }

        /**
         * 关闭指定activity
         */
        public static void finishTargetActivity(Class<? extends Activity> clazz) {
            for (Activity activity : STACK) {
                if (activity.getClass().equals(clazz)) {
                    remove(activity);
                    activity.finish();
                }
            }
        }

        public static Activity getCurrentActivity() {
            return STACK.getLast();
        }

        public static boolean isExists(Class<? extends Activity> clazz) {
            for (Activity aty : STACK) {
                if (aty.getClass().getSimpleName().equals(clazz.getSimpleName())) {
                    return true;
                }
            }
            return false;
        }

        public static void exitApp() {
            synchronized (ActivityStack.class) {
                List<Activity> copy = new LinkedList<>(STACK);
                for (Activity aty : copy) {
                    aty.finish();
                }
                copy.clear();
            }
        }

        public static void finishExcept(Class<? extends Activity> clazz) {
            synchronized (ActivityStack.class) {
                List<Activity> copy = new LinkedList<>(STACK);
                for (Activity aty : copy) {
                    if (!aty.getClass().equals(clazz)) {
                        aty.finish();
                    }
                }
                copy.clear();
            }
        }
    }
}
