package com.histudio.app.ui;

import com.histudio.app.R;
import com.histudio.ui.base.HiBaseFragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class ViewPagerFragment extends HiBaseFragment {

    protected ViewPager mViewPager;
    protected ViewPageFragmentAdapter mPagerAdapter;
    protected List<String> mTabName;
    protected ArrayList<Fragment> mFragments;
    protected TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.rank_tab_layout);

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mFragments = new ArrayList<>();
        FragmentManager fragmentManager = getChildFragmentManager();
        mPagerAdapter = new ViewPageFragmentAdapter(fragmentManager);
        mViewPager.setAdapter(mPagerAdapter);
        initTabName();
        for (String mString : mTabName) {
            mFragments.add(initFragment(mString));
        }

        mPagerAdapter.appendList(mFragments);

        if (tabLayout != null) {
            tabLayout.setupWithViewPager(mViewPager);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            for (int index = 0; index < mTabName.size(); index++) {
                tabLayout.getTabAt(index).setText(mTabName.get(index));
            }
        }


        return view;
    }

    protected void initTabName() {

    }

    protected Fragment initFragment(String channelName) {
        return null;
    }

}
