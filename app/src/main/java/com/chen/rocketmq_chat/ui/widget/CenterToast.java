package com.chen.rocketmq_chat.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.rocketmq_chat.R;
import com.chen.rocketmq_chat.utils.AppManager;


/**
 * 位于屏幕中心，圆角的toast，屏蔽多次显示
 * Created by xp on 2017/1/11.
 */

public class CenterToast {
    private static long oneTime = 0;
    private static long twoTime = 0;
    private static String msg = "";
    private static Context mContext;

    public static void show(String str) {
        mContext = AppManager.appContext();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        TextView textView = (TextView) View.inflate(mContext, R.layout.toast_view, null);
        textView.setText(str);
        Toast toast = new Toast(mContext);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(textView);
        if (msg.equals(str)) {
            twoTime = System.currentTimeMillis();
            if (twoTime - oneTime > 600) {
                toast.show();
                oneTime = twoTime;
            }
        } else {
            msg = str;
            toast.show();
            oneTime = System.currentTimeMillis();
        }
        oneTime = twoTime;
    }
}
