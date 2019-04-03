package com.histudio.app.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.histudio.base.GlobalHandler;
import com.histudio.base.HiApplication;
import com.histudio.base.HiManager;
import com.histudio.base.cache.InfoCache;
import com.histudio.base.constant.BConstants;
import com.histudio.base.entity.User;

/**
 * Created by laolin on 2015/9/21.
 */
public class ActivityUtil {

    /**
     * 先判断是否登录，然后进行跳转
     */
    public static void launchLoginActivity(Context context, Class<?> clazz) {
        User userInfo = HiManager.getBean(InfoCache.class).getUserInfo();
        if (userInfo == null ) {
            Message msg = Message.obtain();
            msg.what = BConstants.GOTO_FRAM_BY_MARK;
            msg.arg1 = BConstants.FRAME_MARK_LOGIN_FRAME;
            HiManager.getBean(GlobalHandler.class).sendMessage(msg);

        } else {
            Intent intent = new Intent(context, clazz);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            HiApplication.instance.startActivity(intent);
        }
    }

    /**
     * 开启activity
     */
    public static void launchActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        //如果给Intent对象设置了这个标记，那么将会导致任务历史堆栈中既存的Activity被带到前台。
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        //Android 5.0一出来后，其中有个特性就是Service Intent  must be explitict，也就是说从Lollipop开始，service服务必须采用显示方式启动
        intent.setPackage(context.getPackageName());
        context.startActivity(intent);
    }

    /**
     * 开启activity(带参数)
     */
    public static void launchActivity(Context context, Class<?> activity, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.setPackage(context.getPackageName());
        context.startActivity(intent);
    }

    /**
     * 开启activity(带参数)
     */
    public static void launchActivity(Context context, Class<?> activity, String key, int value) {
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        launchActivity(context, activity, bundle);
    }

    public static void launchActivity(Context context, Class<?> activity, String key, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        launchActivity(context, activity, bundle);
    }

    public static void launchActivityForResult(Activity context, Class<?> activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, activity);
        intent.putExtras(bundle);
        intent.setPackage(context.getPackageName());
        context.startActivityForResult(intent, requestCode);
    }

    public static void launchActivityForResult(Activity context, Intent intent, int requestCode) {
        intent.setPackage(context.getPackageName());
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动一个服务
     */
    public static void launchService(Context context, Class<?> service) {
        Intent intent = new Intent(context, service);
        //Android 5.0一出来后，其中有个特性就是Service Intent  must be explitict，也就是说从Lollipop开始，service服务必须采用显示方式启动
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    public static void stopService(Context context, Class<?> service) {
        Intent intent = new Intent(context, service);
        intent.setPackage(context.getPackageName());
        context.stopService(intent);
    }


}
