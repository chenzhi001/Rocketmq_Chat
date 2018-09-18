package com.chen.rocketmq_chat.gen;

import com.chen.rocketmq_chat.base.MqApplication;
import com.chen.rocketmq_chat.gen.dao.DaoMaster;
import com.chen.rocketmq_chat.gen.dao.DaoSession;

/**
 * Created by Orange_zhi on 2018/9/18.
 */

public class DaoManager {
    private static final String DB_NAME = "MqChat";
    private static DaoManager mInstance;
    private DaoMaster mDaomaster;
    private DaoSession mDaoSession;

    public DaoManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MqApplication.getInstance(), DB_NAME, null);
        mDaomaster = new DaoMaster(devOpenHelper.getWritableDb());
        mDaoSession = mDaomaster.newSession();
    }

    public static DaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new DaoManager();
        }
        return mInstance;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
