package com.chen.rocketmq_chat.base;

import com.chen.rocketmq_chat.presenter.MvpBasePresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * @author:Orange Created by 2018/7/9.
 */
public class BaseMvpFragment<V extends MvpView,P extends MvpBasePresenter> extends RxFragment implements MvpView {
    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }

    @Override
    public void showLodingText(String text) {

    }
}