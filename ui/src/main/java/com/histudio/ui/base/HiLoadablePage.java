package com.histudio.ui.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.histudio.base.GlobalHandler;
import com.histudio.base.HiApplication;
import com.histudio.base.HiManager;
import com.histudio.base.constant.BConstants;
import com.histudio.base.util.BUtil;
import com.histudio.base.util.Foreground;
import com.histudio.ui.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 有加载行为的page
 *
 * @author qsj
 */
public abstract class HiLoadablePage extends HiBasePage implements SwipeRefreshLayout.OnRefreshListener {
    // 顶部视图
    protected View topView;
    // 底部视图
    protected View bottomView;
    // 当前主视图，只是一个容器
    protected FrameLayout contentContainer;
    // 当前显示的内容view
    protected View contentView;
    // 加载视图
    protected View loadingView;
    // 空视图
    protected View emptyView;
    // 下拉刷新组件
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    protected Bundle mBundle;
    protected Activity mActivity;

    public HiLoadablePage(Activity context) {
        super(context);
        mActivity = context;
    }

    // 延迟初始化，把创建视图和初始化视图分离
    public void initBaseView() {
        // 初始化主界面
        initMainView();
        // 是否需要加载业务
        onLoadData();
    }

    protected void onLoadData() {
        if (isNeedLoadTask()) {
            // 加载任务,返回数据直接在主线程更新数据
            //            showLoadingView();
            if (mSwipeRefreshLayout != null) {
                // 先转1秒，然后请求，比较好看
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }
                });
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onRefresh();
                    }
                }, 1000);
            }
            // 申请数据,如果有SwipeRefreshLayout，则下拉刷新，否则调用loadingtask
            else {
                loadingTask();
            }

        }
    }

    /**
     * 初始化主视图
     */
    private void initMainView() {
        // 设置竖屏
        setOrientation(VERTICAL);
        // 先清空视图
        removeAllViews();

        // 顶部视图
        topView = createTopView(getContext());
        if (topView != null) {
            addView(topView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
        // 主视图
        LinearLayout.LayoutParams linerLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .MATCH_PARENT, 0);
        linerLayoutParams.weight = 1;
        contentContainer = new FrameLayout(getContext());
        // 不要OverScroll效果
        String sdkVersion = HiManager.getContextConfig().getSdkVersion();
        if (BUtil.isNumeric(sdkVersion)) {
            int sdkVersionCode = Integer.parseInt(sdkVersion);
            if (sdkVersionCode > 8) {
                contentContainer.setOverScrollMode(OVER_SCROLL_NEVER);
            }
        }
        addView(contentContainer, linerLayoutParams);
        // 底部视图
        bottomView = createBottomView(getContext());
        if (bottomView != null) {
            addView(bottomView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
        // 创建页面，无数据
        contentContainer.removeAllViews();
        contentView = createContentView();
        contentContainer.addView(contentView);

    }

    /**
     * 创建提示视图
     */
    protected View createTipView(String msg) {
        View emptyView = LayoutInflater.from(getContext()).inflate(R.layout.list_empty_view, null);
        // 提示
        TextView emptyViewText = (TextView) emptyView.findViewById(R.id.empty_text);
        emptyViewText.setText(msg);

        return emptyView;

    }

    /**
     * 创建空视图
     */
    protected View createEmptyView() {
        emptyView = createTipView("暂无数据");
        emptyView.setId(R.id.hi_empty_view_id);
        return emptyView;
    }

    protected void addEmptyView(String msg){
        View emptyView = createTipView(msg);
        contentContainer.removeAllViews();
        contentContainer.addView(emptyView);

    }

    /**
     * 隐藏空视图
     */
    protected void removeEmptyView() {
        if (emptyView != null) {
            contentContainer.removeView(emptyView);
        }
    }

    /**
     * 创建加载失败视图
     */
    protected View createLoadErrorView() {
        View errorView = LayoutInflater.from(getContext()).inflate(R.layout.list_empty_view, null);
        // 加载失败提示
        TextView errorTextView = (TextView) errorView.findViewById(R.id.empty_text);
        errorTextView.setText("网络数据加载失败，点击重新加载。。。");
        TextView errorButton = (TextView) errorView.findViewById(R.id.empty_button);
        errorButton.setVisibility(VISIBLE);
        errorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                contentView = null;
                loadingTask();
            }
        });


        return errorView;
    }

    /**
     * 创建加载视图
     */
    protected View getLoadingView() {
        if (loadingView == null) {
            loadingView = new LoadingView(getContext());
        }

        return loadingView;
    }

    /**
     * 展示加载界面
     */
    public void showLoadingView() {
        //        pageHandler.post(new Runnable() {
        //
        //            @Override
        //            public void run() {
        //                if (contentContainer.findViewById(R.id.progress_bar) == null) {
        //                    contentContainer.addView(getLoadingView());
        //                }
        //            }
        //        });

        onLoadData();
    }

    /**
     * 展示加载界面
     */
    public void showLoadingView(final String tip) {
        //        pageHandler.post(new Runnable() {
        //
        //            @Override
        //            public void run() {
        //                if (contentContainer.findViewById(R.id.progress_bar) == null) {
        //                    contentContainer.removeAllViews();
        //                    contentContainer.addView(getLoadingView());
        //                    ((LoadingView) loadingView).setProgressBarText(tip);
        //                }
        //            }
        //        });
    }

    /**
     * 隐藏加载界面
     */
    public void hideLoadingView() {

        // 如果包含mSwipeRefreshLayout，则停止刷新
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }

    }


    @Override
    public void onHandlePageMessage(Message message) {
        super.onHandlePageMessage(message);
        switch (message.what) {
            case BConstants.SHOW_LOADING_VIEW:

                showLoadingView();
                break;
            case BConstants.TASK_LOADFAIL:
                Throwable error = (Throwable) message.obj;
                message.arg1 = GlobalHandler.MESSAGE_HANDLED;
                handlerError(error);

                break;
            case BConstants.HIDE_LOADING_VIEW:

                hideLoadingView();
                break;

            case BConstants.SHOW_LOADING_DIALOG:
                if (Foreground.get().isForeground()) {// 程序在前台 才展示
                    showLoadingDialog();
                }
                break;
            case BConstants.HIDE_LOADING_DIALOG:
                if (Foreground.get().isForeground()) {// 程序在前台 才隐藏
                    hideLoadingDialog();
                }

                break;
        }
    }


    /**
     * 得到自定义的progressDialog
     */

    protected Dialog loadingDialog;

    public void showLoadingDialog() {
        if (loadingDialog == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
            LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
            // main.xml中的ImageView
            TextView progressText = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
            progressText.setText("正在请求服务器...");
            // 加载动画

            loadingDialog = new Dialog(getContext(), R.style.loading_dialog);// 创建自定义样式dialog
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        }
        if (((Activity) getContext()) != null && !((Activity) getContext()).isFinishing()) {
            if (!loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        }
    }

    protected void hideLoadingDialog() {
        HiManager.getBean(GlobalHandler.class).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (((Activity) getContext()) != null && !((Activity) getContext()).isFinishing()) {
                    if (loadingDialog != null && loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    }
                }

            }
        }, 1000);

    }

    /**
     * 创建顶部视图
     */
    protected View createTopView(Context context) {
        return null;
    }

    /**
     * 创建底部视图
     */
    protected View createBottomView(Context context) {
        return null;
    }

    /**
     * 如果有调用，需要在initBaseView之前调用
     */
    public Bundle getBundle() {
        return mBundle;
    }

    public void setBundle(Bundle bundle) {
        mBundle = bundle;
    }

    @Override
    public void flushViewData() {
        super.flushViewData();
        if (topView instanceof HiBasePage) {
            ((HiBasePage) topView).flushViewData();
        }

        if (bottomView instanceof HiBasePage) {
            ((HiBasePage) bottomView).flushViewData();
        }
    }

    /**
     * 判断是否需要进行任务加载
     */
    protected abstract boolean isNeedLoadTask();

    /**
     * 任务回来后，判断是否是空视图
     */
    protected abstract boolean isShowEmptyView();

    /**
     * 创建视图,这个是在任务发起之前创建
     */
    protected abstract View createContentView();


    /**
     * 加载任务,包括下拉刷新
     */
    protected abstract void loadingTask();

    /**
     * 加载更多item的时候使用,上拉加载
     */
    protected abstract void loadingMoreItemTask();

    @Override
    public void onRefresh() {
        loadingTask();
    }

    public void handlerError(Throwable e) {
        // 处理错误，一般是隐藏loadingview，并提示错误
        // 处理错误，一般是隐藏loadingview，并提示错误
        String error = e.getMessage();
        if (e instanceof SocketTimeoutException || e instanceof ConnectException || e instanceof UnknownHostException) {
            error = "加载失败，请检查网络！";
        } else if (e instanceof JsonSyntaxException) {
            error = "暂无数据";
        }
        Toast.makeText(HiApplication.instance.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
//        addEmptyView(error);
        hideLoadingView();
        hideLoadingDialog();
    }

}
