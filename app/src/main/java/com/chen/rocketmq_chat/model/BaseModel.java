package com.chen.rocketmq_chat.model;

import android.content.Context;

import com.chen.rocketmq_chat.base.MqApplication;

/**
 * Created by Orange_zhi on 2018/9/18.
 */

public abstract class BaseModel<T>{
    protected Context mContext;
    protected T mDao;

    public BaseModel() {
        this.mContext= MqApplication.getInstance();
        mDao=createDao();
    }
    public abstract T createDao();
}
