package com.histudio.app.manager;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.azhon.appupdate.manager.DownloadManager;
import com.histudio.app.R;
import com.histudio.app.util.Util;
import com.histudio.base.GlobalHandler;
import com.histudio.base.HiManager;
import com.histudio.base.constant.BConstants;
import com.histudio.base.entity.Update;
import com.histudio.base.http.ApiException;
import com.histudio.base.http.CommonMethods;
import com.histudio.base.http.subscribers.BaseSubscriber;
import com.histudio.base.util.ApkUtil;
import com.histudio.base.util.NetWorkUtil;
import com.histudio.ui.base.HiBaseFrame;

/**
 * 升级相关
 * Created by apple on 16/7/8.
 */
public class UpdateManager extends HiManager {

    /**
     * 自动更新
     * isManual 是否手动
     * 手动要弹出是最新版，非手动不需要
     */
    public void update(final Activity activity, final boolean isManual) {
        final int vcode = ApkUtil.getVersionCode(activity);
        if (!NetWorkUtil.isNetWorkAvailable(activity)) {
            Util.showToastTip("当前网络不可用，请检查网络情况");
            return;
        }
        HiManager.getBean(CommonMethods.class).checkUpdate(new BaseSubscriber<Update>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof ApiException) {
                    ApiException exception = (ApiException) e;
                    switch (exception.getResultCode()) {
                        case ApiException.ERROR_10105:
                            HiManager.getBean(GlobalHandler.class).sendEmptyMessage(BConstants.UPDATE_SUC);
                            if (isManual) {
                                Util.showToastTip("恭喜您，这是最新版");
                            }
                            break;
                    }
                }
            }

            @Override
            public void onNext(Update data) {
                if (data != null) {
                    if (data.getVcode() > vcode) {
                        showUpdateDialog(data, activity);
                    }
                }
            }
        }, vcode);

    }


    /**
     * 显示升级弹窗
     */
    public void showUpdateDialog(final Update update, final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("升级提示");
        builder.setMessage(update.getContent());
        if (update.getIs_force() == 1) {
            builder.setCancelable(false);
        } else {
            builder.setCancelable(true);
        }
        builder.setNegativeButton(mContext
                .getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (update.getIs_force() == 1) {
                    HiBaseFrame.currentFrame.onExit();
                }
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(mContext
                .getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String url = update.getUrl();
                if (TextUtils.isEmpty(url)) {
                    Util.showToastTip("下载地址为空。");
                } else {
                    try {
                        DownloadManager.getInstance().release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    DownloadManager manager = DownloadManager.getInstance(mContext);
                    manager.setApkName("updateGas.apk")
                            .setApkUrl(url)
                            .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate")
                            .setSmallIcon(R.drawable.logo)
                            .download();
                }
            }
        });
        builder.show();
    }

}
