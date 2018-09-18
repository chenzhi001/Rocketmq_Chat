package com.chen.rocketmq_chat.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by ${BaLe} on 2018/7/10.
 */
public class MqApplication extends Application {

    private static Context  instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
    public static Context  getInstance(){
        return  instance;
    }
}
