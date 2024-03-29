package com.histudio.base.http.subscribers;


import io.reactivex.disposables.Disposable;

/**
 * 用于在Http请求开始时，自动显示一个loadingview
 * 调用者自己对请求数据进行处理
 * Created by ljh on 16/3/10.
 */
public class LoadingSubscriber<T> extends BaseSubscriber<T> {

    private SubscriberOnNextListener mSubscriberOnNextListener;

    public LoadingSubscriber(SubscriberOnNextListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        showLoadingView();
    }


    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissLoadingView();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        super.onNext(t);
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

}