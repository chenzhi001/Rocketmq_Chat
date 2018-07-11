package com.chen.rocketmq_chat.ui.fragment.mine;
import com.chen.rocketmq_chat.R;
import com.chen.rocketmq_chat.base.BaseMvpFragment;
import com.chen.rocketmq_chat.presenter.home.mine.MineContact;
import com.chen.rocketmq_chat.presenter.home.mine.MinePresenterImpl;

/**
 * Created by ${BaLe} on 2018/7/11.
 */
public class MineFragment extends BaseMvpFragment<MinePresenterImpl,MineContact.MineView> implements MineContact.MineView {
    @Override
    protected void init() {

    }

    @Override
    public MineContact.MineView createView() {
        return this;
    }

    @Override
    public MinePresenterImpl createPresenter() {
        return new MinePresenterImpl(getActivity());
    }


    @Override
    protected int getLayoutId() {
        return  R.layout.fragment_mine;
    }
}
