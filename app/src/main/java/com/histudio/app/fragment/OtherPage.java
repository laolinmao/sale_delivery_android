package com.histudio.app.fragment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.histudio.app.R;
import com.histudio.ui.base.HiLoadablePage;

import butterknife.ButterKnife;

/**
 * 作者：apple on 2018/10/25 20:36
 */
public class OtherPage extends HiLoadablePage {



    public OtherPage(Activity context) {
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_other, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
    }

    @Override
    protected void loadingTask() {

    }

    @Override
    protected void loadingMoreItemTask() {

    }



}
