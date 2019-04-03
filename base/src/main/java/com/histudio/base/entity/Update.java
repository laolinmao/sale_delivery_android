package com.histudio.base.entity;

import com.histudio.base.IItem;

/**
 * Created by apple on 16/7/12.
 */
public class Update implements IItem {


    /**
     * id : 1
     * pkg : com.devstudio.delivery.gas
     * vcode : 2
     * vname : 1.0.0
     * content : 更新内容
     1、更新第一条内容
     2、修复了第一个bug
     3、添加了第一个功能
     * url : http://gobrather-test.oss-cn-hangzhou.aliyuncs.com/t-market/apk/young/app-release.apk
     * type : 1
     * is_force : 0
     * threshold : 1
     * status : 2
     */

    private int id;
    private String pkg;
    private int vcode;
    private String vname;
    private String content;
    private String url;
    private int type;
    private int is_force;
    private int threshold;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public int getVcode() {
        return vcode;
    }

    public void setVcode(int vcode) {
        this.vcode = vcode;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIs_force() {
        return is_force;
    }

    public void setIs_force(int is_force) {
        this.is_force = is_force;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
