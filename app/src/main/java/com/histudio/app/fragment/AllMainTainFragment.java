package com.histudio.app.fragment;


import com.histudio.ui.base.HiBaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 社团页面
 */
public class AllMainTainFragment extends HiBaseFragment {
    private AllMainTainPage layout;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layout == null) {
            layout = new AllMainTainPage(getActivity());
            layout.initBaseView();
        }
        ViewGroup parent = (ViewGroup) layout.getParent();
        if (parent != null) {
            parent.removeView(layout);
        }
        return layout;
    }
}

