package com.chen.rocketmq_chat.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

/**
 * @author:Orange Created by 2018/7/9.
 */
public class BaseMvpFragmentActivity<P extends MvpBasePresenter,V extends MvpView> extends RxFragmentActivity implements MvpView{
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
