package com.histudio.ui.base;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;

import com.histudio.base.GlobalHandler;
import com.histudio.base.HiManager;
import com.histudio.base.constant.BConstants;
import com.histudio.base.manager.SharedPrefManager;
import com.histudio.ui.R;
import com.histudio.ui.base.permission.PermissionActivity;
import com.histudio.ui.custom.bar.ImmersionBar;
import com.histudio.ui.manager.AppManager;

import java.lang.ref.WeakReference;
import java.util.Locale;

/**
 * 基础的activity
 * SwipeBackActivity 右划返回
 */
public abstract class HiBaseFrame extends PermissionActivity {

    private FrameHandler mHandler;
    public static HiBaseFrame currentFrame;
    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new FrameHandler(this);
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        //判断app状态
        if (HiManager.getBean(AppManager.class).getAppStatus() != AppManager.STATUS_NORMAL) {
            //被回收，跳转到启动页面
            Message msg = Message.obtain();
            msg.what = BConstants.GOTO_FRAM_BY_MARK;
            msg.arg1 = BConstants.FRAME_MARK_LOADING_FRAME;
            HiManager.getBean(GlobalHandler.class).sendMessage(msg);
            return;
        }


        HiManager.getBean(AppManager.class).addActivity(this);


    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarColor(R.color.white).statusBarDarkFont(true).navigationBarColor(R.color.white).init();
        mImmersionBar.navigationBarColor(R.color.white).init();
    }


    @Override
    protected void onResume() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onResume();
        currentFrame = this;
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HiManager.getBean(GlobalHandler.class).unregistHandler(mHandler);
        HiManager.getBean(AppManager.class).finishActivity(this);

        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }

    @Override
    public void finish() {
        super.finish();
        HiManager.getBean(GlobalHandler.class).unregistHandler(mHandler);
    }

    /**
     * 退出全部
     */
    public void onExit() {
        HiManager.getBean(AppManager.class).AppExit(this);
    }

    public void changeAppLanguage() {
        String lang = HiManager.getBean(SharedPrefManager.class).getStringByKey(BConstants.LANGUAGE, "zh");//这是SharedPreferences工具类，用于保存设置，代码很简单，自己实现吧
        // 本地语言设置
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }


    /**
     * 发送处理交给Activity解决的消息，延迟
     *
     * @param what  消息
     * @param delay 延迟多久
     */
    public void sendEmptyFrameMessage(int what, long delay) {
        mHandler.sendEmptyMessageDelayed(what, delay);
    }

    protected void sendFrameMessage(Message msg) {
        mHandler.sendMessage(msg);
    }

    /**
     * @param msg
     */
    public void onHandleFrameMessage(Message msg) {

    }


    /**
     * 处理Activity上消息的Handler
     */
    private static class FrameHandler extends Handler {

        private WeakReference<HiBaseFrame> activityWrf;

        public FrameHandler(HiBaseFrame activity) {
            activityWrf = new WeakReference<HiBaseFrame>(activity);
            HiManager.getBean(GlobalHandler.class).registeHandler(this);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (activityWrf != null) {
                HiBaseFrame activity = activityWrf.get();
                if (activity != null && !activity.isFinishing()) {
                    try {
                        activity.onHandleFrameMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}