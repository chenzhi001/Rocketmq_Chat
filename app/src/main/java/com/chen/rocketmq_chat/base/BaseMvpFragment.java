package com.chen.rocketmq_chat.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.rocketmq_chat.ui.widget.DialogLoading;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author:Orange Created by 2018/7/9.
 */
public abstract class BaseMvpFragment<P extends MvpBasePresenter, V extends MvpView> extends RxFragment {

    protected P mPresenter;
    private Unbinder unbinder;
    protected Context mContext;
    protected DialogLoading mDialogLoading;
    protected V mView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        return rootView;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getContext();
        mDialogLoading = new DialogLoading(getActivity());
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mView == null) {
            mView = createView();
        }
        if (mPresenter == null && mView == null) {
            mPresenter.attachView(mView);
        }
        init();
    }

    protected abstract void init();

    public abstract V createView();

    public abstract P createPresenter();

    protected abstract int getLayoutId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.dettachView();
            mPresenter = null;
        }
    }

    public void showToast(String msg) {

    }

    public void showLoading() {

    }

    public void dismissLoading() {

    }

    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    public void showLodingText(String text) {

    }

}
