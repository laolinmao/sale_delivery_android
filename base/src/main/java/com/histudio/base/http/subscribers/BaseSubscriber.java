package com.histudio.base.http.subscribers;

import android.os.Message;
import android.widget.Toast;

import com.histudio.base.GlobalHandler;
import com.histudio.base.HiApplication;
import com.histudio.base.HiManager;
import com.histudio.base.constant.BConstants;
import com.histudio.base.util.NetWorkUtil;
import com.socks.library.KLog;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.histudio.base.http.BaseMethods.CUSTOM_REPEAT_REQ_PROTOCOL;


public class BaseSubscriber<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
        //        showLoadingView();
        if (!NetWorkUtil.isNetworkAvailable(HiApplication.instance)) {

            // 非网络环境
            Toast.makeText(HiApplication.instance, "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
        }

        // **一定要主动调用下面这一句**
        onComplete();
    }

    @Override
    public void onComplete() {
    }


    protected void showLoadingView() {
        HiManager.getBean(GlobalHandler.class).sendEmptyMessage(BConstants.SHOW_LOADING_DIALOG);
    }

    protected void dismissLoadingView() {
        HiManager.getBean(GlobalHandler.class).sendEmptyMessage(BConstants.HIDE_LOADING_DIALOG);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof IOException) {   //飞行模式等
            //这里处理一下重复请求的自定义返回的Response 导致的错误，
            if (e.getMessage().contains(CUSTOM_REPEAT_REQ_PROTOCOL)) {
                KLog.i("------------可以忽略的重复请求");
                return;
            }
        }

        Message msg = new Message();
        msg.obj = e;
        msg.what = BConstants.TASK_LOADFAIL;
        HiManager.getBean(GlobalHandler.class).sendMessage(msg);
    }

    @Override
    public void onNext(T t) {
        dismissLoadingView();
    }


}
