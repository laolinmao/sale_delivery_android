package com.histudio.base.entity;

import com.histudio.base.IItem;

/**
 * Created by apple on 16/7/12.
 */
public class Banner implements IItem {

    private String activity_object;
    private String activity_type;
    private String banner_image_url;

    public String getActivity_object() {
        return activity_object;
    }

    public void setActivity_object(String activity_object) {
        this.activity_object = activity_object;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getBanner_image_url() {
        return banner_image_url;
    }

    public void setBanner_image_url(String banner_image_url) {
        this.banner_image_url = banner_image_url;
    }
}
