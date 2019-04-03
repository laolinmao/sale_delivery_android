package com.histudio.ui.base;

import com.histudio.ui.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 加载界面
 * 
 * @author ljh
 * @data 2014-9-14
 */
public class LoadingView extends FrameLayout {

	private TextView progressBarText;

	public LoadingView(Context context) {
		super(context);
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER));
		addView(LayoutInflater.from(getContext()).inflate(R.layout.loading_view, null));
		progressBarText = (TextView) findViewById(R.id.progressBarText);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return true;
	}

	public TextView getProgressBarText() {
		return progressBarText;
	}

	public void setProgressBarText(String s) {
		this.progressBarText.setText(s);
	}

}
