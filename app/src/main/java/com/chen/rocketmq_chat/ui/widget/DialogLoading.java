package com.chen.rocketmq_chat.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chen.rocketmq_chat.R;


/**
 * 默认loading
 *
 * @author xp Create on 15/11/10.
 */
public class DialogLoading {

    private Dialog dialog;
    private TextView loadingMsg;
    private Context mContext;
    private LinearLayout loadingRoot;
    private ImageView imgLoading;

    public DialogLoading(Activity context) {
        this.mContext = context;
        dialog = new Dialog(mContext, R.style.LoadDialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_loading, null);

        loadingRoot = view.findViewById(R.id.loadingRoot);

        loadingMsg = view.findViewById(R.id.tv_loading);
        imgLoading = view.findViewById(R.id.img_loading);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setContentView(view);

        loadingRoot.setLayoutParams(new FrameLayout.LayoutParams(dp2px(100), dp2px(100)));
    }

    public DialogLoading setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public DialogLoading setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public DialogLoading setOnCancelListener(DialogInterface.OnCancelListener listener) {
        dialog.setOnCancelListener(listener);
        return this;
    }

    public void show() {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_rotate);
        animation.setInterpolator(new LinearInterpolator());
        imgLoading.startAnimation(animation);
        dialog.show();
    }

    public void dismiss() {
        imgLoading.clearAnimation();
        dialog.dismiss();
    }

    public DialogLoading setLoadingMassage(String msg) {
        loadingMsg.setText(TextUtils.isEmpty(msg) ? "正在加载" : msg);
        return this;
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public int dp2px(int dip) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }
}
