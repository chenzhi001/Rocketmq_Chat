package com.chen.rocketmq_chat.base;

import android.content.Context;

import com.chen.rocketmq_chat.model.BaseModel;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author:Orange Created by 2018/7/6.
 */
public abstract class MvpBasePresenter<V extends MvpView,M extends BaseModel> implements MvpPresenter<V> {
    private WeakReference<V> mWeakView;
    private WeakReference<Context> mWeakeContext;
    protected V View;
    protected M mMode;

    public MvpBasePresenter(Context weakeContext) {
        this.mWeakeContext = new WeakReference<>(weakeContext);
        this.mMode=createModel();
    }
    public abstract M createModel();

    @Override
    public void attachView(V view) {
        this.mWeakView = new WeakReference<>(view);
        MvpInvocationHandler invocationHandler = new MvpInvocationHandler(mWeakView.get());
        View = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), invocationHandler);
    }

    @Override
    public void dettachView() {
        if (mWeakView != null && mWeakView.get() != null) {
            mWeakView.clear();
            mWeakView = null;
        }
    }

    public boolean isViewAttached() {
        if (mWeakView != null && mWeakView.get() != null) {
            return true;
        }
        return false;
    }

    public Context getContext() {
        if (mWeakeContext != null) {
            return mWeakeContext.get();
        }
        return null;
    }

    public V getView() {
        return View;
    }

    private class MvpInvocationHandler implements InvocationHandler {
        private V view;

        public MvpInvocationHandler(V view) {
            this.view = view;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isViewAttached()) {
                return method.invoke(view, args);
            } else {
                return new RuntimeException("the View is null...! ");
            }
        }
    }
}
