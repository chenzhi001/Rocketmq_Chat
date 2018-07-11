package com.chen.rocketmq_chat.presenter.home;

import android.content.Context;

import com.chen.rocketmq_chat.base.MvpBasePresenter;

/**
 * @author:Orange Created by 2018/7/9.
 */
public class HomePresenterImpl extends MvpBasePresenter<HomeContract.IHomeView> implements HomeContract.IHomePresenter {

    public HomePresenterImpl(Context weakeContext) {
        super(weakeContext);
    }
}
