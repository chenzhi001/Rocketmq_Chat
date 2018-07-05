package com.chen.rocketmq_chat.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionCheckListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * Created by ${BaLe} on 2018/7/6.
 */
public class Productor {


    private final TransactionMQProducer mqProducer;
    private onSenResoultlistner resoultlistner;

    public Productor(String nameser, onSenResoultlistner listner) throws MQClientException {
        String group_name = "transation_productor";
        mqProducer = new TransactionMQProducer(group_name);
        mqProducer.setNamesrvAddr(nameser);
        mqProducer.setCheckThreadPoolMinSize(5);
        mqProducer.setCheckThreadPoolMaxSize(20);
        mqProducer.setCheckRequestHoldMax(2000);
        this.resoultlistner = listner;
        /**
         * producer.start()
         * 在初始化使用之前必须要调用start 初始化一次即可
         * 切记不可再每次发送消息的时候都调用start 方法
         */
        mqProducer.start();
        mqProducer.setTransactionCheckListener(new TransactionCheckListener() {
            @Override
            public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

    }

    /**
     * 下面这段代码表明一个Productord对象可以发送多个Topic 多个tag消息
     * 注意：send 方法是同步调用只要不抛异常就标识成功，但是发送成功也会有多种状态
     * 例如消息写入master 成功 但是save 不成功 这种情况消息属于成功但是对于个别应用如对消息可靠性要求极高
     * 需要对这种情况做处理，另外，消息可能会存在发送失败的情况，失败重试由应用来处理
     */
    public void sendMesage(String message, String topictransaction, String key, String tag) throws MQClientException {
        TransactionExcuterImpl transactionExcuter = new TransactionExcuterImpl();
        Message msg = new Message(topictransaction, tag, message.getBytes());
        SendResult sendResult = mqProducer.sendMessageInTransaction(msg, transactionExcuter, "tq");
        if (sendResult.isTraceOn()) {
            resoultlistner.onSendSuccess();
        } else {
            resoultlistner.onSendFailed();
        }
        resoultlistner.onSenResult(sendResult);
    }

    public interface onSenResoultlistner {

        void onSendSuccess();

        void onSendFailed();

        void onSenResult(SendResult sendResult);
    }
}
