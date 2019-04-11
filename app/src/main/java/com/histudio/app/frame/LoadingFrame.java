package com.histudio.app.frame;

import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;

import com.histudio.app.main.MainTabFrame;
import com.histudio.app.util.ActivityUtil;
import com.histudio.app.util.Constants;
import com.histudio.base.constant.BConstants;
import com.histudio.ui.base.HiBaseFrame;
import com.socks.library.KLog;

import butterknife.ButterKnife;

/**
 * 加载界面
 *
 * @data 2014-6-21
 */
public class LoadingFrame extends HiBaseFrame {

    // 准备界面等待时间
    private static final int PREVIEW_WAITING_TIME = 1500;
    private boolean isTimeout = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        sendEmptyFrameMessage(Constants.LOADING_FRAME_AFTER_LOADING, PREVIEW_WAITING_TIME);
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.transparentBar().init();
    }


    @Override
    public void onHandleFrameMessage(Message msg) {
        super.onHandleFrameMessage(msg);
        switch (msg.what) {
            case Constants.LOADING_FRAME_AFTER_LOADING:
                ActivityUtil.launchLoginActivity(LoadingFrame.this, MainTabFrame.class);
                finish();
                break;

            case BConstants.INIT_APP_IN_BACKGROUD_SUCC:
            case BConstants.INIT_APP_IN_BACKGROUD_FAIL:
                if (isTimeout) {
                    finish();
                    KLog.i("异步初始化时间太长");
                }
                break;


        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 按下的如果是BACK，不做操作
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 禁止右划退出
    @Override
    protected boolean supportSlideBack() {
        return false;
    }


}