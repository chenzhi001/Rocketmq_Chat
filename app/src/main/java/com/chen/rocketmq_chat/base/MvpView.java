package com.chen.rocketmq_chat.base;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author:Orange Created by 2018/7/6.
 */
public interface MvpView {


    void showToast(String msg);

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载c
     */
    void dismissLoading();

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();

    /**
     * 显示带文字的加载动画
     */
    void showLodingText(String text);

}
