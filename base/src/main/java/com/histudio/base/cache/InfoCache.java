package com.histudio.base.cache;

import com.histudio.base.HiManager;
import com.histudio.base.constant.BConstants;
import com.histudio.base.constant.Configuration;
import com.histudio.base.entity.ISingleable;
import com.histudio.base.entity.User;
import com.histudio.base.manager.SharedPrefManager;
import com.histudio.base.util.BUtil;
import com.histudio.base.util.Des3;
import com.histudio.base.util.JsonUtil;

import java.io.Serializable;

/**
 * 所有对外方法都简单同步一下就好
 */
public class InfoCache implements ISingleable, Serializable {
    // 用户信息缓存
    private User userInfo;

    public synchronized void clearUserData() {
        setUserInfo(null);
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized User getUserInfo() {
        return userInfo;
    }

    public synchronized void setUserInfo(User userInfo) {
        try {
            String desKey = BUtil.converIntsToString(Configuration.APP_SECRET);
            HiManager.getBean(SharedPrefManager.class).saveStringValue(BConstants.USER_INFO,
                    Des3.encode(desKey, JsonUtil.toStringFromEntity(userInfo)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.userInfo = userInfo;
    }


}