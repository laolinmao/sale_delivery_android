package com.histudio.app;

import com.histudio.app.main.MainTabFrame;
import com.histudio.base.HiManager;
import com.histudio.base.entity.JPushInfo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

/**
 * 通知栏
 *
 * @author
 */
public class PhoNotifyManager extends HiManager {

    private NotificationManager notificationManager = (NotificationManager) mContext
            .getSystemService(Context.NOTIFICATION_SERVICE);

    /** 系统通知栏 **/
    public void notifyCommonFeed(JPushInfo info) {
        Intent intent = new Intent(mContext, MainTabFrame.class);
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, (int) SystemClock.elapsedRealtime(), intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        String title = info.getData().getNAME();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext).setSmallIcon(R.drawable.logo)
                .setTicker(title).setContentTitle(title).setContentText("你有一条新订单。")
                .setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(contentIntent);

        Notification notification = builder.build();
        notificationManager.notify(R.id.notify_pho_sys_info, notification);
    }

}
