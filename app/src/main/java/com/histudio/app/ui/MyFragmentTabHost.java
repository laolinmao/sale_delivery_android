package com.histudio.app.ui;

import android.content.Context;
import android.util.AttributeSet;

/**
 * tabhost
 */

public class MyFragmentTabHost extends FragmentNewTabHost {

	private String mCurrentTag;

	private String mNoTabChangedTag;

	public MyFragmentTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onTabChanged(String tag) {

		if (tag.equals(mNoTabChangedTag)) {
			setCurrentTabByTag(mCurrentTag);
		} else {
			super.onTabChanged(tag);
			mCurrentTag = tag;
		}
	}

	public void setNoTabChangedTag(String tag) {
		this.mNoTabChangedTag = tag;
	}
}
