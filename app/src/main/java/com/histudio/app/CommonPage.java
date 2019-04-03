package com.histudio.app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.histudio.ui.base.HiLoadablePage;

import butterknife.ButterKnife;

/**
 * 作者：apple on 2018/10/28 17:46
 */
public class CommonPage extends HiLoadablePage {

    public CommonPage(Activity context) {
        super(context);
    }

    @Override
    protected boolean isNeedLoadTask() {
        return false;
    }

    @Override
    protected boolean isShowEmptyView() {
        return false;
    }

    @Override
    protected View createContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    protected void loadingTask() {

    }

    @Override
    protected void loadingMoreItemTask() {

    }
}
