package com.chen.rocketmq_chat.mq.callback;

import com.alibaba.rocketmq.client.producer.SendResult;

/**
 * Created by ${BaLe} on 2018/7/11.
 */
public interface onSenResoultlistner {

    void onSendSuccess(SendResult sendResult);

    void onSendFailed(String errorMsg);

    void onSenResult(SendResult sendResult);
}
