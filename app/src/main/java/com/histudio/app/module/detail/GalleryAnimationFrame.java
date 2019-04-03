package com.histudio.app.module.detail;


import android.os.Bundle;

import com.histudio.ui.base.HiBaseFrame;


/**
 * 图片走廊详情
 */

public class GalleryAnimationFrame extends HiBaseFrame {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GalleryAnimationPage page = new GalleryAnimationPage(GalleryAnimationFrame.this);
        page.setBundle(getIntent().getExtras());
        page.initBaseView();
        setContentView(page);
    }

}
