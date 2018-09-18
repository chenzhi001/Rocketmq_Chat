package com.chen.rocketmq_chat.presenter.home.mine;

import android.content.Context;

import com.chen.rocketmq_chat.base.MvpBasePresenter;
import com.chen.rocketmq_chat.model.BaseModel;

/**
 * Created by ${BaLe} on 2018/7/11.
 */
public class MinePresenterImpl extends MvpBasePresenter<MineContact.MineView,BaseModel> implements MineContact.IMinePresenter {

    public MinePresenterImpl(Context weakeContext) {
        super(weakeContext);
    }

    @Override
    public BaseModel createModel() {
        return null;
    }
}
