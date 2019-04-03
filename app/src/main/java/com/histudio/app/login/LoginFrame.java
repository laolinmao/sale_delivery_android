package com.histudio.app.login;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.histudio.app.R;
import com.histudio.app.main.MainFrame;
import com.histudio.app.util.ActivityUtil;
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
    protected void onPause() {
        page.onPause();
        super.onPause();

    }

    @Override
    protected String getActionBarCenterTitle() {
        return "液化气管理系统";
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.color_green).init();
    }

    //    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pop_unarrive, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pop_search:
                ActivityUtil.launchActivity(LoginFrame.this, MainFrame.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
