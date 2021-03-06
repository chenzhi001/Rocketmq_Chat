package com.chen.rocketmq_chat.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.inputmethod.InputMethodManager;

import com.chen.rocketmq_chat.R;
import com.chen.rocketmq_chat.ui.widget.CenterToast;
import com.chen.rocketmq_chat.ui.widget.DialogLoading;
import com.chen.rocketmq_chat.utils.StatusBarUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author:Orange Created by 2018/7/6.
 */
public abstract class BaseMvpAppCompatActivity<P extends MvpBasePresenter, V extends MvpView> extends RxAppCompatActivity {

    protected P mPresenter;
    private Unbinder unbinder;
    protected Context mContext;
    protected DialogLoading mDialogLoading;
    protected V mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = getApplicationContext();
        mDialogLoading = new DialogLoading(this);
        unbinder = ButterKnife.bind(this);
        setStatusBarColor();
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mView == null) {
            mView = createView();
        }
        if (mPresenter != null && mView != null) {
            mPresenter.attachView(mView);
        }
        init();
    }

    protected abstract void init();

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract V createView();


    public void showToast(String msg) {
        CenterToast.show(msg);
    }

    public void showLoading() {
        if (!mDialogLoading.isShowing()) {
            mDialogLoading.show();
        }
    }

    public void dismissLoading() {
        if (mDialogLoading.isShowing()) {
            mDialogLoading.dismiss();
        }
    }

    public void showLodingText(String text) {
        if (mDialogLoading.isShowing()) {
            mDialogLoading.dismiss();
        }
        mDialogLoading.setLoadingMassage(text);
        mDialogLoading.show();
    }

    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    /**
     * 关闭软键盘
     */
    public void closeKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置状态栏颜色
     */
    protected void setStatusBarColor() {
        StatusBarUtils.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary), 30);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        // 解绑view
        if (mPresenter != null) {
            mPresenter.dettachView();
            this.mPresenter = null;
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
