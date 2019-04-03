package com.histudio.app;

import com.histudio.app.login.LoginFrame;
import com.histudio.base.HiManager;
import com.histudio.base.cache.InfoCache;
import com.histudio.base.manager.SharedPrefManager;
import com.histudio.ui.base.HiBaseFrame;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhoManager extends HiManager {

    protected Dialog loadingDialog;
    protected TextView progressText;

    /**
     * 得到自定义的progressDialog
     */
    public void showLoadDialog(final Activity mContext) {
        if (loadingDialog == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
            LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
            // main.xml中的ImageView
            progressText = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
            // 加载动画
            progressText.setText("正在请求服务器...");
            loadingDialog = new Dialog(mContext, R.style.loading_dialog);// 创建自定义样式dialog
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        }
        if(!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public void hideLoadDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    public void showEditOutDialog(final Activity mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("退出此次编辑？");
        builder.setNegativeButton(mContext
                .getResources().getString(R.string.back_up), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(mContext
                .getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mContext.finish();
            }
        });
        builder.show();
    }


    public void showLoginOutDialog(final Activity mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(mContext.getResources().getString(
                R.string.login_out));
        builder.setNegativeButton(mContext
                .getResources().getString(R.string.back_up), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(mContext
                .getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HiManager.getBean(InfoCache.class).clearUserData();
                // 不自动登陆
                HiManager.getBean(SharedPrefManager.class).saveBooleanValue("isAutoLogin", false);
                Intent intent = new Intent(mContext, LoginFrame.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mContext.startActivity(intent);
                mContext.finish();
            }
        });
        builder.show();
    }

    public void showExitDialog(final Activity mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("是否要退出系统？");
        builder.setNegativeButton(mContext
                .getResources().getString(R.string.back_up), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(mContext
                .getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HiBaseFrame.currentFrame.onExit();
            }
        });
        builder.show();
    }

}
