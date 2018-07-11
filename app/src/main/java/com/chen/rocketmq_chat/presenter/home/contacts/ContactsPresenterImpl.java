package com.chen.rocketmq_chat.presenter.home.contacts;

import android.content.Context;

import com.chen.rocketmq_chat.base.MvpBasePresenter;
import com.chen.rocketmq_chat.presenter.home.chat.ChatContact;

/**
 * Created by ${BaLe} on 2018/7/11.
 */
public class ContactsPresenterImpl extends MvpBasePresenter<ContactsContact.IContactsView> implements ChatContact.IChatPresenter {
    public ContactsPresenterImpl(Context weakeContext) {
        super(weakeContext);
    }
}
