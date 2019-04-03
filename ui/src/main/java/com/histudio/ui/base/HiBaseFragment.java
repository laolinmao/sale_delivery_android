package com.histudio.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.histudio.base.GlobalHandler;
import com.histudio.base.HiManager;

import java.lang.ref.WeakReference;

/**
 * 基础的fragment
 *
 * @author qsj
 */
public abstract class HiBaseFragment extends BaseLazyFragment {

    protected HiBaseFrame mHostActivity;
    private FragmentHandler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new FragmentHandler(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mHostActivity = (HiBaseFrame) activity;
    }


    /**
     * 发送空消息
     */
    protected void sendEmptyFragmentMessage(int what) {
        if (mHandler != null) {
            mHandler.sendEmptyMessage(what);
        }
    }

    /**
     * 处理Handler消息
     */
    protected void onHandleFragmentMessage(Message msg) {

    }

    public void finish() {
        mHostActivity.finish();
    }

    /**
     * fragment的处理器
     *
     * @author qsj
     */
    private static class FragmentHandler extends Handler {

        private WeakReference<HiBaseFragment> socialFragmentWrf;

        public FragmentHandler(HiBaseFragment fragment) {
            socialFragmentWrf = new WeakReference<HiBaseFragment>(fragment);
            HiManager.getBean(GlobalHandler.class).registeHandler(this);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (socialFragmentWrf != null) {
                HiBaseFragment fragment = socialFragmentWrf.get();
                if (fragment != null) {
                    try {
                        fragment.onHandleFragmentMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
