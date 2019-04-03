package com.histudio.app.ui;


import com.histudio.app.fragment.MainTainFragment;
import com.histudio.app.fragment.OtherFragment;

public enum MainTab {

    RECORD(0, com.histudio.app.R.string.title_frame_main, com.histudio.app.R.drawable.radio_activity_selector, MainTainFragment.class),
    CLUB(1, com.histudio.app.R.string.title_frame_teach, com.histudio.app.R.drawable.radio_club_selector, MainTainFragment.class),
    FIND(2, com.histudio.app.R.string.title_frame_class, com.histudio.app.R.drawable.radio_find_selector, OtherFragment.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
