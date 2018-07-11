package com.chen.rocketmq_chat.ui.fragment.contacts;

import com.chen.rocketmq_chat.R;
import com.chen.rocketmq_chat.base.BaseMvpFragment;
import com.chen.rocketmq_chat.presenter.home.contacts.ContactsContact;
import com.chen.rocketmq_chat.presenter.home.contacts.ContactsPresenterImpl;

/**
 * Created by ${BaLe} on 2018/7/11.
 */
public class ContactsFragment extends BaseMvpFragment<ContactsPresenterImpl,ContactsContact.IContactsView> implements ContactsContact.IContactsView
{
    @Override
    protected void init() {

    }

    @Override
    public ContactsContact.IContactsView createView() {
        return this;
    }

    @Override
    public ContactsPresenterImpl createPresenter() {
        return new ContactsPresenterImpl(getActivity());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contacts;
    }
}
