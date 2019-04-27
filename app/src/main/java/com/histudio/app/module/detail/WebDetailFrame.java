package com.histudio.app.module.detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

import com.histudio.app.R;
import com.histudio.base.entity.Advert;
import com.histudio.ui.base.HiToolbarFrame;

/**
 * 网页详情页
 */
public class WebDetailFrame extends HiToolbarFrame {
    private BaseWebPage detailPage;
    private Advert advert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        advert = (Advert) getIntent().getExtras().getSerializable(Constants.ADVERT_DETAIL);
        detailPage = new BaseWebPage(this);
        detailPage.setBundle(getIntent().getExtras());
        detailPage.initBaseView();
        setContentView(detailPage);
    }

    @Override
    protected String getActionBarCenterTitle() {
        String title = advert.getTitle();
        if (TextUtils.isEmpty(title)) {
            return "网页";
        } else {
            return title;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_share, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem shareItem = menu.findItem(R.id.action_share);
        shareItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                detailPage.share();
                return false;
            }
        });

        return true;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        /** 使用SSO授权必须添加如下代码 */
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        detailPage.goToBack();
    }
}