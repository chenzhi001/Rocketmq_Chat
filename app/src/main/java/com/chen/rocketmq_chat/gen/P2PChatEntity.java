package com.chen.rocketmq_chat.gen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Orange_zhi on 2018/9/18.
 */

/**
 * 聊天的表，与某个联系人聊天会产生的记录表
 */
@Entity
public class P2PChatEntity {
    @Id
    private long id;

    @Generated(hash = 282802742)
    public P2PChatEntity(long id) {
        this.id = id;
    }

    @Generated(hash = 74161779)
    public P2PChatEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
