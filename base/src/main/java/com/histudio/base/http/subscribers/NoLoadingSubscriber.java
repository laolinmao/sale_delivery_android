package com.histudio.base.http.subscribers;


/**
 * 用于在Http请求开始时，自动显示一个loadingview
 * 调用者自己对请求数据进行处理
 * Created by ljh on 16/3/10.
 */
public class NoLoadingSubscriber<T> extends BaseSubscriber<T>  {

    private SubscriberOnNextListener mSubscriberOnNextListener;

    public NoLoadingSubscriber(SubscriberOnNextListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
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