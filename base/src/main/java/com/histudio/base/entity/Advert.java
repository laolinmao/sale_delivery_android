package com.histudio.base.entity;

import com.histudio.base.IItem;

/**
 * Created by apple on 16/7/12.
 */
public class Advert implements IItem {

    private String uuid;
    private long createTime;
    private String imageUrl = "";
    private String introduce;
    private String link = "";
    private int status;
    private String title = "";
    private String type;
    private int moduleType;
    private String moduleParam;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getModuleType() {
        return moduleType;
    }

    public void setModuleType(int moduleType) {
        this.moduleType = moduleType;
    }

    public String getModuleParam() {
        return moduleParam;
    }

    public void setModuleParam(String moduleParam) {
        this.moduleParam = moduleParam;
    }
}
