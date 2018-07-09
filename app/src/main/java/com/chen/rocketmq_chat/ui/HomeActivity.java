package com.chen.rocketmq_chat.ui;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.graphics.drawable.ArgbEvaluator;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.rocketmq_chat.R;
import com.chen.rocketmq_chat.base.BaseMvpAppCompatActivity;
import com.chen.rocketmq_chat.presenter.home.HomeContract;
import com.chen.rocketmq_chat.presenter.home.HomePresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ${BaLe} on 2018/7/6.
 */
public class HomeActivity extends BaseMvpAppCompatActivity<HomePresenterImpl, HomeContract.HomeView> {

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
    @BindView(R.id.tv_ship)
    TextView tvShip;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    private ArrayList<TextView> mTipList = new ArrayList<>();
    private List<ImageView> mBottomImageViews = new ArrayList<>();
    private List<Drawable> mBottomImageDrawables = new ArrayList<>();
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
    protected void init() {
        initTapList();
        initImageViewDrawable();
        mHomePager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mTipList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView textView = mTipList.get(position);
                container.addView(textView);
                return textView;
            }
        });
        mHomePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private final int DELAY_TIME = 100;
            private Handler handler = new Handler();

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changeImageViewDrawable(position, positionOffset);
            }

            @Override
            public void onPageSelected(final int position) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setImageViewSelect(position);
                    }
                }, DELAY_TIME);
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
        drawableFrom = mBottomImageDrawables.get(position);
        if (position != mBottomImageDrawables.size() - 1) {
            ivTo = mBottomImageViews.get(position + 1);
            drawableTo = mBottomImageDrawables.get(position + 1);
        } else {
            ivTo = null;
            drawableTo = null;
        }

        if (ivFrom != null) {
            @SuppressLint("RestrictedApi") int colorStart = (int) mArgbEvaluator.evaluate(positionOffset, mBottomColorSelect,
                    mBottomColorUnSelect);
            Drawable drawableColorStart = tintDrawable(drawableFrom, ColorStateList.valueOf
                    (colorStart));
            ivFrom.setImageDrawable(drawableColorStart);
        }
        if (ivTo != null) {
            @SuppressLint("RestrictedApi") int colorStart = (int) mArgbEvaluator.evaluate(positionOffset, mBottomColorUnSelect,
                    mBottomColorSelect);
            Drawable drawableColorEnd = tintDrawable(drawableTo, ColorStateList.valueOf
                    (colorStart));
            ivTo.setImageDrawable(drawableColorEnd);
        }
    }

    public Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }


    private void initTapList() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < 3; i++) {
            TextView textView = new TextView(this);
            textView.setText("第" + (i + 1) + "个页面");
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            mTipList.add(textView);
        }
    }

    @OnClick({R.id.ivChat, R.id.ivContact, R.id.ivMine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivChat:
                break;
            case R.id.ivContact:
                break;
            case R.id.ivMine:
                break;
            default: {
                break;
            }
        }
    }

    private void initImageViewDrawable() {
        mBottomColorSelect = ContextCompat.getColor(this, R.color.buttonSelect);
        mBottomColorUnSelect = ContextCompat.getColor(this, R.color.buttonUnSelect);

        mBottomImageViews.add((ImageView) findViewById(R.id.ivChat));
        mBottomImageViews.add((ImageView) findViewById(R.id.ivContact));
        mBottomImageViews.add((ImageView) findViewById(R.id.ivMine));
        mBottomImageDrawables.add(ContextCompat.getDrawable(this, R.drawable.icon_chat).mutate());
        mBottomImageDrawables.add(ContextCompat.getDrawable(this, R.drawable.icon_contact).mutate());
        mBottomImageDrawables.add(ContextCompat.getDrawable(this, R.drawable.icon_mine).mutate());
        for (int i = 0; i < mBottomImageViews.size(); i++) {
            ImageView imageView = mBottomImageViews.get(i);
            final int index = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHomePager.setCurrentItem(index);
                }
            });
        }
        setImageViewSelect(0);
    }

    private void setImageViewSelect(int selectIndex) {
        for (int index = 0; index < mBottomImageViews.size(); index++) {
            ImageView imageView = mBottomImageViews.get(index);
            Drawable drawable = mBottomImageDrawables.get(index);
            if (index == selectIndex) {
                imageView.setImageDrawable(tintDrawable(drawable, ColorStateList.valueOf(mBottomColorSelect)));
            } else {
                imageView.setImageDrawable(tintDrawable(drawable, ColorStateList.valueOf(mBottomColorUnSelect)));
            }
        }
    }
}
