package com.chen.rocketmq_chat.ui;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.graphics.drawable.ArgbEvaluator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chen.rocketmq_chat.R;
import com.chen.rocketmq_chat.base.BaseMvpAppCompatActivity;
import com.chen.rocketmq_chat.presenter.home.HomeContract;
import com.chen.rocketmq_chat.presenter.home.HomePresenterImpl;
import com.chen.rocketmq_chat.ui.fragment.chat.ChatFragment;
import com.chen.rocketmq_chat.ui.fragment.contacts.ContactsFragment;
import com.chen.rocketmq_chat.ui.fragment.mine.MineFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ${BaLe} on 2018/7/6.
 */
public class HomeActivity extends BaseMvpAppCompatActivity<HomePresenterImpl, HomeContract.IHomeView> implements HomeContract.IHomeView {

    @BindView(R.id.vpHome)
    ViewPager mHomePager;
    @BindView(R.id.ivChat)
    ImageView ivChat;
    @BindView(R.id.ivContact)
    ImageView ivContact;
    @BindView(R.id.ivMine)
    ImageView ivMine;
    @BindView(R.id.tv_chat)
    TextView tvChat;
    @BindView(R.id.tv_contacts)
    TextView tvContact;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    @BindView(R.id.rl_chat)
    RelativeLayout rlChat;
    @BindView(R.id.rl_contacts)
    RelativeLayout rlContacts;
    @BindView(R.id.rl_mine)
    RelativeLayout rlMine;
    private Map<Integer, Fragment> mfragemts = new HashMap<>();
    private List<ImageView> mBottomImageViews = new ArrayList<>();
    private List<Drawable> mBottomImageDrawables = new ArrayList<>();
    private List<TextView> mBottomtextViews = new ArrayList<>();
    private int mBottomColorSelect;
    private int mBottomColorUnSelect;
    @SuppressLint("RestrictedApi")
    private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    @Override
    protected HomePresenterImpl createPresenter() {
        return new HomePresenterImpl(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected HomeContract.IHomeView createView() {
        return this;
    }

    @Override
    protected void init() {
        initFragments();
        initImageViewDrawable();
        mHomePager.setAdapter(new RxFragmentPagerAdpter(getSupportFragmentManager()));
        mHomePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private final int DELAY_TIME = 100;
            private Handler handler = new Handler();

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changeImageViewDrawable(position, positionOffset);
            }

            @Override
            public void onPageSelected(final int position) {
                setPagerSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }

    private void changeImageViewDrawable(int position, float positionOffset) {
        ImageView ivFrom = null;
        ImageView ivTo = null;
        TextView tvFrom = null;
        TextView tvTo = null;
        Drawable drawableFrom = null;
        Drawable drawableTo = null;
        ivFrom = mBottomImageViews.get(position);
        tvFrom = mBottomtextViews.get(position);
        drawableFrom = mBottomImageDrawables.get(position);
        if (position != mBottomImageDrawables.size() - 1) {
            ivTo = mBottomImageViews.get(position + 1);
            drawableTo = mBottomImageDrawables.get(position + 1);
            tvTo = mBottomtextViews.get(position + 1);
        } else {
            ivTo = null;
            drawableTo = null;
            tvTo = null;
        }

        if (ivFrom != null) {
            @SuppressLint("RestrictedApi") int colorStart = (int) mArgbEvaluator.evaluate(positionOffset, mBottomColorSelect, mBottomColorUnSelect);
            Drawable drawableColorStart = tintDrawable(drawableFrom, ColorStateList.valueOf(colorStart));
            ivFrom.setImageDrawable(drawableColorStart);
            tvFrom.setTextColor(colorStart);
        }
        if (ivTo != null) {
            @SuppressLint("RestrictedApi") int colorStart = (int) mArgbEvaluator.evaluate(positionOffset, mBottomColorUnSelect, mBottomColorSelect);
            Drawable drawableColorEnd = tintDrawable(drawableTo, ColorStateList.valueOf(colorStart));
            ivTo.setImageDrawable(drawableColorEnd);
            tvTo.setTextColor(colorStart);
        }
    }

    public Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.MULTIPLY);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }


    private void initFragments() {
        ChatFragment chatFragment = new ChatFragment();
        ContactsFragment contactsFragment = new ContactsFragment();
        MineFragment mineFragment = new MineFragment();
        mfragemts.put(0, chatFragment);
        mfragemts.put(1, contactsFragment);
        mfragemts.put(2, mineFragment);
    }

    private void initImageViewDrawable() {
        mBottomColorSelect = ContextCompat.getColor(this, R.color.buttonSelect);
        mBottomColorUnSelect = ContextCompat.getColor(this, R.color.buttonUnSelect);

        mBottomImageViews.add(ivChat);
        mBottomImageViews.add(ivContact);
        mBottomImageViews.add(ivMine);
        mBottomtextViews.add(tvChat);
        mBottomtextViews.add(tvContact);
        mBottomtextViews.add(tvMine);

        mBottomImageDrawables.add(ContextCompat.getDrawable(this, R.drawable.icon_chat).mutate());
        mBottomImageDrawables.add(ContextCompat.getDrawable(this, R.drawable.icon_contact).mutate());
        mBottomImageDrawables.add(ContextCompat.getDrawable(this, R.drawable.icon_mine).mutate());

    }

    private void setPagerSelect(int selectIndex) {
        for (int index = 0; index < mBottomImageViews.size(); index++) {
            ImageView imageView = mBottomImageViews.get(index);
            TextView  textView =mBottomtextViews.get(index);
            Drawable drawable = mBottomImageDrawables.get(index);
            if (index == selectIndex) {
                imageView.setImageDrawable(tintDrawable(drawable, ColorStateList.valueOf(mBottomColorSelect)));
                textView.setTextColor(mBottomColorSelect);
            } else {
                imageView.setImageDrawable(tintDrawable(drawable, ColorStateList.valueOf(mBottomColorUnSelect)));
                textView.setTextColor(mBottomColorUnSelect);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_chat, R.id.rl_contacts, R.id.rl_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_chat:
                setPagerSelect(0);
                break;
            case R.id.rl_contacts:
                setPagerSelect(1);
                break;
            case R.id.rl_mine:
                setPagerSelect(2);
                break;
            default:
        }
    }

    private class RxFragmentPagerAdpter extends FragmentPagerAdapter {

        public RxFragmentPagerAdpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mfragemts.get(position);
        }

        @Override
        public int getCount() {
            return mfragemts.size();
        }
    }
}
