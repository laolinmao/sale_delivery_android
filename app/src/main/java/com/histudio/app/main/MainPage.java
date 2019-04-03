package com.histudio.app.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.histudio.app.R;
import com.histudio.ui.base.HiLoadablePage;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：apple on 2018/10/28 17:46
 */
public class MainPage extends HiLoadablePage {

    public MainPage(Activity context) {
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frame_main, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void loadingTask() {

    }

    @Override
    protected void loadingMoreItemTask() {

    }

    @OnClick({R.id.main_sale, R.id.main_anjian, R.id.main_yuangong, R.id.main_bottle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_sale:
                break;
            case R.id.main_anjian:
                break;
            case R.id.main_yuangong:
                break;
            case R.id.main_bottle:
                break;
        }
    }
}
