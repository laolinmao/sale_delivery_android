package com.histudio.app.fragment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.histudio.app.R;
import com.histudio.ui.base.HiLoadablePage;

import butterknife.ButterKnife;

/**
 * 活动
 * Created by apple on 16/6/3.
 */
public class AllMainTainPage extends HiLoadablePage {

    public AllMainTainPage(Activity context) {
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void loadingTask() {
    }

    @Override
    protected void loadingMoreItemTask() {
    }

}