package com.histudio.app;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.multidex.MultiDex;

import com.histudio.app.crash.CrashHandler;
import com.histudio.app.login.LoginFrame;
import com.histudio.app.main.MainTabFrame;
import com.histudio.app.util.Constants;
import com.histudio.base.HiApplication;
import com.histudio.base.HiManager;
import com.histudio.base.cache.InfoCache;
import com.histudio.base.constant.BConstants;
import com.histudio.base.constant.Configuration;
import com.histudio.ui.base.HiBaseFrame;
import com.histudio.ui.manager.FrescoManager;
import com.socks.library.KLog;

import cn.jpush.android.api.JPushInterface;

/**
 * 应用程序基础
 *
 * @data 2014-6-21
 */
public class PhoApplication extends HiApplication {

    public static PhoApplication instance;


    @Override
    public void onCreate() {
        KLog.init(BuildConfig.DEBUG);
        super.onCreate();
        // 崩溃异常保存
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

        JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this); // 初始化 JPush

    }


    /**
     * 这里的初始化在主线程进行，注意不能执行耗时操作，不然会黑屏
     */


    @Override
    protected void syncInitApplication() {
        instance = this;
        // 初始化图片加载器
        HiManager.getBean(FrescoManager.class).init();

        // 基础信息
        HiManager.initContextConfig();

        HiManager.initAlarm();
        super.syncInitApplication();
    }


//    @Override
//    protected void preSubHandler(Message msg) {
//        super.preSubHandler(msg);
//        switch (msg.what) {
//            case BConstants.UPDATE_ORDER:
//                AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.order);
//                MediaManager.playOrderSound(file);
//
//                break;
//
//        }
//    }

    @Override
    protected void onRemoteProcessInit(String processName) throws Exception {
    }

    @Override
    public void afterSubHandler(Message msg) {
        super.afterSubHandler(msg);
        switch (msg.what) {
            case BConstants.GOTO_FRAM_BY_MARK:
                Class<?> clazz = null;
                Intent intent = null;
                switch (msg.arg1) {
                    case BConstants.FRAME_MARK_LOGIN_FRAME:
                        if (HiBaseFrame.currentFrame != null) {
                            HiManager.getBean(InfoCache.class).clearUserData();
                            clazz = LoginFrame.class;
                            intent = new Intent(this, clazz);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            HiBaseFrame.currentFrame.startActivity(intent);
                        }
                        break;
                    case BConstants.FRAME_MARK_MAIN_FRAME:
                        if (HiBaseFrame.currentFrame != null) {
                            clazz = MainTabFrame.class;
                            intent = new Intent(this, clazz);
                            intent.putExtra(Constants.MAIN_TAB_INDEX, 0);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            HiBaseFrame.currentFrame.startActivity(intent);
                        }
                        break;
                }

            case BConstants.ALERT_CLOCK_1_MIN:
//                boolean isRead = HiManager.getBean(SharedPrefManager.class).getBooleanByKey("isread", true);
//                if (!isRead) {
//                    AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.order);
//                    MediaManager.playOrderSound(file);
//                }
                break;
            case BConstants.ALERT_CLOCK_5_MIN:
                break;
            case BConstants.ALERT_CLOCK_15_MIN:
                break;
        }
    }

    @Override
    public boolean isDebug() {
        return Configuration.IS_DEBUG;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
