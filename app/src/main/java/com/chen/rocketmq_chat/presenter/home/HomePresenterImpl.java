package com.chen.rocketmq_chat.presenter.home;

import android.content.Context;

import com.chen.rocketmq_chat.presenter.MvpBasePresenter;

/**
 * @author:Orange Created by 2018/7/9.
 */
public class HomePresenterImpl extends MvpBasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter {

    public HomePresenterImpl(Context weakeContext) {
        super(weakeContext);
    }
}
