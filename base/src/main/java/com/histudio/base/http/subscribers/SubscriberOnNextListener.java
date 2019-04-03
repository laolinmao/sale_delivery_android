package com.histudio.base.http.subscribers;

/**
 * Created by ljh on 16/3/10.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
