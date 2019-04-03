package com.histudio.ui.base;

import com.histudio.base.GlobalHandler;
import com.histudio.base.HiManager;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;

import rx.Subscription;

/**
 * 基础的page
 *
 * @author qsj
 */
public abstract class HiBasePage extends LinearLayout {

    protected PageHandler pageHandler = new PageHandler(this);

    public Subscription rxSubscription;

    public HiBasePage(Context context) {
        super(context);
    }

    public HiBasePage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 填充view数据
     */
    public void flushViewData() {

    }

    protected void onPause() {

    }

    protected void onDestroy() {
        if (rxSubscription != null && !rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }

    /**
     * 消息处理
     */
    public void onHandlePageMessage(Message message) {
    }

    /**
     * 获取界面消息处理器
     */
    protected PageHandler getPageHandler() {
        return pageHandler;
    }

    /**
     * 视图内部消息处理
     */
    protected static class PageHandler extends Handler {

        private final WeakReference<HiBasePage> socialViewRef;

        private PageHandler(HiBasePage view) {
            socialViewRef = new WeakReference<HiBasePage>(view);
            HiManager.getBean(GlobalHandler.class).registeHandler(this);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (socialViewRef != null && socialViewRef.get() != null) {
                Context context = socialViewRef.get().getContext();
                if (context instanceof Activity) {
                    if (((Activity) context).isFinishing()) {
                        HiManager.getBean(GlobalHandler.class).unregistHandler(this);
                        return;
                    }
                }
                HiBasePage view = socialViewRef.get();
                if (view != null) {
                    try {
                        view.onHandlePageMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 结束activity
     */
    public void finish() {
        HiManager.getBean(GlobalHandler.class).unregistHandler(pageHandler);
        if (getContext() instanceof Activity) {
            ((Activity) getContext()).finish();
        }
    }

}
