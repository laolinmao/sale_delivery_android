package com.histudio.ui.custom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.histudio.ui.R;
import com.histudio.ui.base.HiLoadablePage;

/**
 * Created by Administrator on 2018/8/9.
 */

public class EmptyPage extends HiLoadablePage {
    public EmptyPage(Activity context) {
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.stu_empty_view, null);
        return view;
    }

    @Override
    protected void loadingTask() {

    }

    @Override
    protected void loadingMoreItemTask() {

    }
}
