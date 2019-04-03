package com.histudio.ui.custom.recycler;

import com.histudio.base.IItem;
import com.histudio.base.constant.BConstants;
import com.histudio.ui.R;
import com.histudio.ui.base.HiLoadablePage;
import com.histudio.ui.custom.recycler.base.BaseQuickAdapter;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单的列表view
 * Created by apple on 16/6/25.
 */
public abstract class ARecyclerViewPage<T extends IItem> extends HiLoadablePage implements BaseQuickAdapter.RequestLoadMoreListener {
    protected List<T> mList = new ArrayList<>();
    // 默认第一页 一页10条数据
    protected int ps = BConstants.COMMON_PAGE_SIZE;
    protected int pi = 1;
    protected RecyclerView mRecyclerView;
    protected boolean isLoadMore = false;
    protected TextView emptyText;
    protected ImageView emptyImg;
    protected boolean isShowEmpty = true;// 默认显示empty  但是在加头部后，如果显示empty，会导致头部无法显示
    // 数据适配器
    protected BaseQuickAdapter baseAdapter;

    public ARecyclerViewPage(Activity context) {
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_refresh_recycler_fab, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.common_swipeRefreshLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.common_recycler);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecyclerView.setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
        int padding = getContext().getResources().getDimensionPixelOffset(R.dimen.padding);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).color(getResources().getColor(R.color.color_20_black)).margin(padding, padding).build());

        initLayoutManager();
        View emptyView = LayoutInflater.from(getContext()).inflate(R.layout.stu_empty_view, null);
        emptyText = (TextView) emptyView.findViewById(R.id.empty_text);
        emptyImg = (ImageView) emptyView.findViewById(R.id.empty_img);
        emptyText.setText("暂无数据");
        initAdapter();
        if(isShowEmpty) {
            baseAdapter.setEmptyView(emptyView);
        }
        baseAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(baseAdapter);
        baseAdapter.setEnableLoadMore(isLoadMore);
        if (isLoadMore) {
            baseAdapter.setPreLoadNumber(ps);
            baseAdapter.setOnLoadMoreListener(this);

        }
        return view;
    }

    protected void initLayoutManager() {
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void loadingTask() {
        pi = 1;
        mList.clear();
    }

    @Override
    protected void loadingMoreItemTask() {
        // 增加页数
        pi = pi + 1;
    }


    // 初始化adapter

    public abstract void initAdapter();


    @Override
    public void onLoadMoreRequested() {
        if (isLoadMore) {
            loadingMoreItemTask();
        } else {
            baseAdapter.loadMoreComplete();
        }
    }

//    @Override
//    public void handlerError(String error) {
//        hideLoadingView();
//    }

}
