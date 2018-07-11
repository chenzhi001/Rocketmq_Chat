package com.chen.rocketmq_chat.base;

/**
 * @author:Orange Created by 2018/7/6.
 */
public interface MvpPresenter<V extends MvpView> {
    /**
     * 绑定View
     */
    public void attachView(V view);

    /**
     * 解除绑定View
     */
    public void dettachView();
}
