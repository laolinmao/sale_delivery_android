package com.histudio.app.login;

import android.os.Bundle;

import com.histudio.app.R;
import com.histudio.ui.base.HiToolbarFrame;

public class LoginFrame extends HiToolbarFrame {

    LoginPage page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = new LoginPage(this);
        page.initBaseView();
        setContentView(page);
        getToolbar().setBackgroundColor(getResources().getColor(R.color.color_green));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected String getActionBarCenterTitle() {
        return "基础系统";
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.color_green).init();
    }


}
