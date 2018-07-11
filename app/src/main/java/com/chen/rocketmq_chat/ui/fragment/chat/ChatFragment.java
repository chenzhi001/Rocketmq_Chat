package com.chen.rocketmq_chat.ui.fragment.chat;

import com.chen.rocketmq_chat.R;
import com.chen.rocketmq_chat.base.BaseMvpFragment;
import com.chen.rocketmq_chat.presenter.home.chat.ChatContact;
import com.chen.rocketmq_chat.presenter.home.chat.ChatPresenterImpl;

/**
 * Created by ${BaLe} on 2018/7/11.
 */
public class ChatFragment extends BaseMvpFragment<ChatPresenterImpl,ChatContact.ChatView> implements ChatContact.ChatView {
    @Override
    protected void init() {

    }

    @Override
    public ChatContact.ChatView createView() {
        return this;
    }

    @Override
    public ChatPresenterImpl createPresenter() {
        return new ChatPresenterImpl(getActivity());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }
}
