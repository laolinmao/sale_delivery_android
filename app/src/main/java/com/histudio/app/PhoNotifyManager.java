package com.histudio.app;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.histudio.app.main.MainTabFrame;
import com.histudio.base.HiApplication;
import com.histudio.base.HiManager;
import com.histudio.base.entity.JPushInfo;

/**
 * 通知栏
 *
 * @author
 */
public class PhoNotifyManager extends HiManager {

    private NotificationManager notificationManager = (NotificationManager) mContext
            .getSystemService(Context.NOTIFICATION_SERVICE);

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) HiApplication.instance.getSystemService(
                HiApplication.instance.NOTIFICATION_SERVICE);
        channel.enableLights(true); //是否在桌面icon右上角展示小红点

        channel.setLightColor(Color.RED); //小红点颜色
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 系统通知栏
     **/
    public void notifyCommonFeed(JPushInfo info) {
        Intent intent = new Intent(mContext, MainTabFrame.class);
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, (int) SystemClock.elapsedRealtime(), intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        String title = info.getData().getNAME();
        createNotificationChannel(HiApplication.instance.getPackageName(), "配送系统", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(HiApplication.instance, HiApplication.instance.getPackageName()); //与channelId对应
        } else {
            builder = new NotificationCompat.Builder(HiApplication.instance);
        }

        builder.setSmallIcon(R.drawable.logo)
                .setTicker(title).setContentTitle(title).setContentText("你有一条新订单。")
                .setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(contentIntent);

        Notification notification = builder.build();
        notificationManager.notify(R.id.notify_pho_sys_info, notification);
    }

}
