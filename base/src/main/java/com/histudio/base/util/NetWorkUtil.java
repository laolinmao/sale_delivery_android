package com.histudio.base.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 蔡小木 on 2016/3/16 0016.
 */
public class NetWorkUtil {

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * 获取当前网络状态(是否可用)
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null) {
            /**
             * 获取网络信息实体
             * 由于从系统服务中获取数据属于进程间通信，基本类型外的数据必须实现Parcelable接口，
             * NetworkInfo实现了Parcelable，获取到的activeNetInfo相当于服务中网络信息实体对象的一个副本（拷贝），
             * 所以，不管系统网络服务中的实体对象是否置为了null，此处获得的activeNetInfo均不会发生变化
             */
            NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
            if (activeNetInfo != null) {
                return activeNetInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 检测wifi是否连接
     *
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 检测3G是否连接
     *
     * @return
     */
    public static boolean is3gConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }
}
