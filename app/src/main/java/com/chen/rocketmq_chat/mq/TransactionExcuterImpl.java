package com.chen.rocketmq_chat.mq;


import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ${BaLe} on 2018/7/6.
 */
public class TransactionExcuterImpl implements LocalTransactionExecuter {
    private AtomicInteger transitionIndex = new AtomicInteger(1);

    @Override
    public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
        String Tag = msg.getTags();
        //这里有一个分阶段提交任务的概念
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
