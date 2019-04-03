package com.histudio.app.fragment;


import android.support.v4.app.Fragment;

import com.histudio.app.R;
import com.histudio.app.ui.ViewPagerFragment;

import java.util.ArrayList;

/**
 * 维修界面
 */
public class MainTainFragment extends ViewPagerFragment {

    @Override
    protected void initTabName(){
        mTabName = new ArrayList<>();
        mTabName.add(getResources().getString(R.string.main_tab_unreach));
        mTabName.add(getResources().getString(R.string.main_tab_untake));
        mTabName.add(getResources().getString(R.string.main_tab_maintain));
    }

    public Fragment initFragment(String channelName) {
        Fragment newfragment = new Fragment();
        if (channelName.equals(getResources().getString(R.string.main_tab_unreach))) {
            newfragment = new AllMainTainFragment();
        } else if (channelName.equals(getResources().getString(R.string.main_tab_untake))) {
            newfragment = new AllMainTainFragment();
        }else if (channelName.equals(getResources().getString(R.string.main_tab_maintain))) {
            newfragment = new AllMainTainFragment();
        }

        return newfragment;
    }
}
