package com.histudio.app.util;

import com.histudio.app.PhoApplication;
import com.histudio.base.GlobalHandler;
import com.histudio.base.HiManager;
import com.histudio.base.manager.SharedPrefManager;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 工具类
 *
 * @author ljh
 * @data 2014-6-21
 */
public class Util {

    /**
     * 是否显示引导
     */
    public static boolean isShowGuidActivity() {
        String isInstall = HiManager.getBean(SharedPrefManager.class).getStringByKey("guid_version_1", "");
        if (TextUtils.isEmpty(isInstall)) {
            HiManager.getBean(SharedPrefManager.class).saveStringValue("guid_version_1", "guid_version_1");
            return true;
        }
        return false;
    }

    /**
     * 隐藏输入法
     */
    public static void hideInputMethodWindow(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示输入法
     */
    public static void showInputMethodWindow(final Context context, final View view) {
        view.requestFocus();
        view.post(new Runnable() {
            @Override
            public void run() {
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
            }
        });
    }


    public static void showToastTip(final String text) {
        HiManager.getBean(GlobalHandler.class).post(new Runnable() {

            @Override
            public void run() {
                try {
                    //解决使用沉浸式状态栏后Toast不显示在中间的方法
                    //toast的context就必须用getapplicationcontext，不能用this或getbasecontext了
                    Toast.makeText(PhoApplication.instance.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    //                    ToastUtil toastUtil = new ToastUtil();
                    //                    toastUtil.Short(context, text).setToastBackground(Color.WHITE, R.drawable.common_blue_black).show();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 判断手机格式
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    // 判断email格式是否正确
    public static boolean isEmail(String email) {
        String pattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);
        return m.matches();
    }

    /**
     * 跳转到市场评论
     */
    public static void marketComments(Context mContext, String mPackageName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + mPackageName));
        mContext.startActivity(intent);
    }

    /**
     * 实现文本复制功能
     */
    public static void copy(Context context, String content) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     * add by wangqianzhou
     */
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }

    /**
     * 读取Assets目录下面指定文件并返回String数据
     */
    public static String getJsonDataFromAssets(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = context.getClass().getClassLoader().getResourceAsStream("assets/" + fileName);
        try {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            String json = new String(buffer, "utf-8");
            stringBuilder = stringBuilder.append(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public static int px(float dipValue) {
        Resources r = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
