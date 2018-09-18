package com.chen.rocketmq_chat.presenter.home.chat;

import android.content.Context;

import com.chen.rocketmq_chat.base.MvpBasePresenter;
import com.chen.rocketmq_chat.model.BaseModel;

/**
 * Created by ${BaLe} on 2018/7/11.
 */
public class ChatPresenterImpl extends MvpBasePresenter<ChatContact.ChatView,BaseModel> implements ChatContact.IChatPresenter {

    public ChatPresenterImpl(Context weakeContext) {
        super(weakeContext);
    }

    @Override
    public BaseModel createModel() {
        return null;
    }
}
