package com.histudio.base.entity;

import java.io.Serializable;

/**
 * Created by apple on 16/7/12.
 */
public class ResIdData implements Serializable{

    private String resId;
    private String url;
    private int  index;// 图片顺序
    private String localUrl;

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
