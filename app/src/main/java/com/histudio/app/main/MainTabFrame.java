package com.histudio.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.histudio.app.R;
import com.histudio.app.ui.MainTab;
import com.histudio.app.ui.MyFragmentTabHost;
import com.histudio.app.util.Constants;
import com.histudio.app.util.Util;
import com.histudio.ui.base.HiBaseFrame;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainTabFrame extends HiBaseFrame {

    @Bind(R.id.tab_container)
    MyFragmentTabHost mTabHost;
    private ImageView mImgNotice;
    private long firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_main_tab);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.white).init();
    }


    private void initView() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setShowDividers(0);

        initTabs();
        // 如果是无账号，则跳转2
    }


    /**
     * 处理传进来的intent
     *
     * @return void
     */
    private void handleIntent(Intent intent) {
        if (intent == null)
            return;
        int index = intent.getIntExtra(Constants.MAIN_TAB_INDEX, 0);
        // 否则跳转主界面
        mTabHost.setCurrentTab(index);
    }

    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            ImageView img = (ImageView) indicator.findViewById(R.id.tab_img);
            img.setImageResource(mainTab.getResIcon());
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
//            tab.setContent(new TabContentFactory() {
//
//                @Override
//                public View createTabContent(String tag) {
//                    return new View(MainTabFrame.this);
//                }
//            });
            mTabHost.addTab(tab, mainTab.getClz(), null);
        }
    }


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
