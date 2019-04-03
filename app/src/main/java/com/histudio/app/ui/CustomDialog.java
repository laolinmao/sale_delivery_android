package com.histudio.app.ui;

import android.app.Activity;
import android.app.Dialog;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.histudio.app.R;

public class CustomDialog extends Dialog {
    private Activity mActivity;
    private Window window = null;

    public CustomDialog(Activity context, boolean cancelable,
                        OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mActivity = context;
    }

    public CustomDialog(Activity context, int theme) {
        super(context, theme);
        this.mActivity = context;
    }

    public CustomDialog(Activity context) {
        super(context, R.style.myDialog);
        this.mActivity = context;
    }

    public void setView(View view) {
        setContentView(view);
        setProperty();
    }

    public void setView(int id) {
        setContentView(id);
        setProperty();
    }

    public void setProperty() {
        WindowManager windowManager = mActivity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        setProperty(0, 0, (int) (display.getWidth() * 0.8), WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void setProperty(int x, int y, int w, int h) {
        window = getWindow();//得到对话框的窗口．
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = x;//设置对话框的位置．0为中间
        wl.y = y;
        wl.width = w;
        wl.height = h;
        wl.alpha = 1f;// 设置对话框的透明度,1f不透明
        wl.gravity = Gravity.CENTER;//设置显示在中间
        window.setAttributes(wl);
    }

}
