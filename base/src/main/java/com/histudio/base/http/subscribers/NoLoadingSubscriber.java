package com.histudio.base.http.subscribers;


import com.histudio.base.GlobalHandler;
import com.histudio.base.HiManager;
import com.histudio.base.constant.BConstants;

import android.os.Message;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个loadingview
 * 调用者自己对请求数据进行处理
 * Created by ljh on 16/3/10.
 */
public class NoLoadingSubscriber<T> extends Subscriber<T> implements SubscriberCancelListener {

    private SubscriberOnNextListener mSubscriberOnNextListener;

    public NoLoadingSubscriber(SubscriberOnNextListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    }


    private void showLoadingView() {
        HiManager.getBean(GlobalHandler.class).sendEmptyMessage(BConstants.SHOW_LOADING_VIEW);
    }

    private void dismissLoadingView() {
        HiManager.getBean(GlobalHandler.class).sendEmptyMessage(BConstants.HIDE_LOADING_VIEW);
    }



    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
//        showLoadingView();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
            dismissLoadingView();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String error = e.getMessage();
        if (e instanceof SocketTimeoutException || e instanceof ConnectException || e instanceof UnknownHostException) {
            error = "加载失败，请检查网络！";
        }
        Message msg = new Message();
        msg.obj = error;
        msg.what = BConstants.TASK_LOADFAIL;
        HiManager.getBean(GlobalHandler.class).sendMessage(msg);
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
        HiManager.getBean(GlobalHandler.class).sendEmptyMessage(BConstants.TASK_LOADED);
    }

    @Override
    public void onCancelSubscriber() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}