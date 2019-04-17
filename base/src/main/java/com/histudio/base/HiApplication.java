package com.histudio.base;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Message;

import com.histudio.base.util.Foreground;
import com.socks.library.KLog;

import java.util.List;

/**
 * 基础的application
 *
 * @author ljh
 * @data 2014-9-16
 */
public abstract class HiApplication extends Application {

    // 全局的引用
    public static HiApplication instance;
    // 是否主进程(定义:进程名是包名，为主进程。其他的，比如service另起的进程，为子进程)
    private boolean isMainProcess;

    private ActivityLifecycleHelper mActivityLifecycleHelper;

    public ActivityLifecycleHelper getActivityLifecycleHelper() {
        return mActivityLifecycleHelper;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Foreground.init(this);
        /* 是否是主进程,不是主进程，就跳过初始化，比如service的独立进程 */
        String processName = getCurProcessName(this);
        KLog.i("processName: " + processName);
        isMainProcess = getPackageName().equals(processName);
        if (!isMainProcess) {
            KLog.i("服务进程：" + processName + "开始初始化");
            try {
                onRemoteProcessInit(processName);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            KLog.i("服务进程：" + processName + "初始化结束");
            return;
        }

        KLog.i("主进程:" + processName + "开始初始化");

        // 同步执行初始化应用的操作，在UI线程中进行
        syncInitApplication();

        KLog.i("主进程:" + processName + "同步初始化结束");

        registerActivityLifecycleCallbacks(mActivityLifecycleHelper = new ActivityLifecycleHelper());
    }

    @Override
    public final void onLowMemory() {
        super.onLowMemory();

		/* 非主进程，不执行 */
        if (!isMainProcess) {
            return;
        }

        doOnLowMemory();
    }

    @Override
    public final void onTerminate() {
        super.onTerminate();

		/* 非主进程，不执行 */
        if (!isMainProcess) {
            return;
        }
        doOnTerminate();
    }

    @Override
    public final void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        /* 非主进程，不执行 */
        if (!isMainProcess) {
            return;
        }

        doOnConfigurationChanged();
    }

    /**
     * 在主线程初始化应用的一些操作
     */
    protected void syncInitApplication() {
    }

    /**
     * 在后台执行初始化应用的一些操作
     */
    public void asyncInitAppInBackgroud() {
    }

    /**
     * 做远程进程的初始化
     *
     * @param processName
     */
    protected abstract void onRemoteProcessInit(String processName) throws Exception;

    /**
     * 提供给子类覆盖onLowMemory()方法
     */
    protected void doOnLowMemory() {

    }

    /**
     * 提供给子类覆盖onTerminate()方法
     */
    protected void doOnTerminate() {

    }

    /**
     * 提供给子类覆盖onConfigurationChanged()方法
     */
    protected void doOnConfigurationChanged() {

    }

    /**
     * 在子消息处理器之前处理全局消息
     *
     * @param msg
     */
    protected void preSubHandler(Message msg) {
//		switch (msg.what) {
//		case BConstants.INIT_APP_IN_BACKGROUD_FAIL:
//			BUtil.showToastTipByMsg("初始化应用异步操作失败!");
//			break;
//		}
    }

    /**
     * 在子消息处理器之后处理全局消息
     *
     * @param msg
     */
    protected void afterSubHandler(Message msg) {
    }

    /**
     * 是否Debug模式
     *
     * @return
     */
    public abstract boolean isDebug();

    /**
     * 获得进程名
     *
     * @param context
     * @return
     */
    private String getCurProcessName(Context context) {
        String processName = "";
        try {
            int pid = android.os.Process.myPid();
            ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

            List<RunningAppProcessInfo> appProcessList = mActivityManager.getRunningAppProcesses();
            if (appProcessList != null) {
                for (RunningAppProcessInfo appProcess : appProcessList) {
                    if (appProcess.pid == pid) {
                        processName = appProcess.processName;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 保证不为空的返回
        if (processName == null) {
            processName = "";
        }

        return processName;
    }
}
