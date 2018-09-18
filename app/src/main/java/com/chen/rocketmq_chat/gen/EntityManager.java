package com.chen.rocketmq_chat.gen;

import com.chen.rocketmq_chat.gen.dao.P2PChatEntityDao;

/**
 * Created by Orange_zhi on 2018/9/18.
 */

public class EntityManager {
    private static EntityManager mEntityManager;

    public static EntityManager  getInstance(){
        if (mEntityManager==null){
            mEntityManager=new EntityManager();
        }
        return mEntityManager;
    }
    public P2PChatEntityDao getP2PChatEntityDao(){
        return DaoManager.getInstance().getDaoSession().getP2PChatEntityDao();
    }
}
