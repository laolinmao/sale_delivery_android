package com.histudio.app.main;

import android.os.Bundle;

import com.histudio.app.R;
import com.histudio.app.util.Util;
import com.histudio.ui.base.HiToolbarFrame;

public class MainFrame extends HiToolbarFrame {

    MainPage page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = new MainPage(this);
        page.setBundle(getIntent().getExtras());
        page.initBaseView();
        setContentView(page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected String getActionBarCenterTitle() {
        return "基础系统";
    }

    private long firstTime;

    /**
     * 连续按两次返回键就退出
     */
    @Override
    public void onBackPressed() {
        if (firstTime + 2000 > System.currentTimeMillis()) {
            onExit();
            //            goToHome();
        } else {
            Util.showToastTip(getResources().getString(R.string.exit_to_home));
            firstTime = System.currentTimeMillis();
        }
    }

    // 禁止右划退出
    @Override
    protected boolean supportSlideBack() {
        return false;
    }

}
