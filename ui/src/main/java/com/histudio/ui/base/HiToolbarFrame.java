package com.histudio.ui.base;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.histudio.ui.R;

/**
 * 带有toolbar的Activity
 *
 * @author laolin
 */
public class HiToolbarFrame extends HiBaseFrame {
    private TextView toolbarText;
    public View rootView;
    private Toolbar toolbar;

    @Override
    public final void setContentView(int layoutResId) {
        initBaseView();
        FrameLayout container = (FrameLayout) rootView.findViewById(R.id.common_container_content);
        container.removeAllViews();
        View contentView = LayoutInflater.from(this).inflate(layoutResId, null);
        container.addView(contentView);

        super.setContentView(rootView);

        mImmersionBar
                .titleBar(toolbar)
                .init();
    }

    @Override
    public final void setContentView(View view, LayoutParams params) {
        initBaseView();
        FrameLayout container = (FrameLayout) rootView.findViewById(R.id.common_container_content);
        container.removeAllViews();
        container.addView(view, params);

        super.setContentView(rootView);

        mImmersionBar
                .titleBar(toolbar)
                .init();
    }

    @Override
    public final void setContentView(View view) {
        initBaseView();
        FrameLayout container = (FrameLayout) rootView.findViewById(R.id.common_container_content);
        container.removeAllViews();
        container.addView(view);
        super.setContentView(rootView);

        mImmersionBar
                .titleBar(toolbar)
                .init();
    }

    protected void initBaseView() {
        rootView = LayoutInflater.from(this).inflate(R.layout.common_container_with_toolbar, null);
        toolbar = (Toolbar) rootView.findViewById(R.id.common_toolbar);
        toolbarText = (TextView) rootView.findViewById(R.id.common_toolbar_text);
        toolbarText.setText(getActionBarCenterTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getActionBarTitle());
    }

    /**
     * 设置每个界面各自ActionBar的
     *
     * @return 返回标题
     */
    protected String getActionBarTitle() {
        return "";
    }

    protected String getActionBarCenterTitle() {
        return "";
    }

    public TextView getToolbarText() {
        return toolbarText;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
