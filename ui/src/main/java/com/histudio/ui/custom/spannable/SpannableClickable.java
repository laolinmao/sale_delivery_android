package com.histudio.ui.custom.spannable;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.histudio.base.HiApplication;
import com.histudio.ui.R;

/**
 */
public abstract class SpannableClickable extends ClickableSpan implements View.OnClickListener {

    private int DEFAULT_COLOR_ID = R.color.color_2a2a2a;
    private int textColorId = DEFAULT_COLOR_ID;

    public SpannableClickable() {

    }

    public SpannableClickable(int textColorId){
        this.textColorId = textColorId;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);

        int colorValue = HiApplication.instance.getResources().getColor(
                textColorId);
        ds.setColor(colorValue);
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}
