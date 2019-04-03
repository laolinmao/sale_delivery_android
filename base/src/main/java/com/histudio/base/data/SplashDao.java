package com.histudio.base.data;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.histudio.base.HiFao;
import com.histudio.base.entity.Advert;
import com.histudio.base.util.JsonUtil;

import java.lang.reflect.Type;

/**
 * Created by apple on 16/7/8.
 */
public class SplashDao extends HiFao {

    private static final String HI_CHANNEL_PREF = "hi_channel_pref";
    private static final String AD_INFO = "ad_info";

    public SplashDao() {
        super(HI_CHANNEL_PREF);
    }

    // 广告项

    public void saveSplashInfo(Advert advert){
        if(advert ==null){
            return;
        }
        String json = JsonUtil.toStringFromEntity(advert);
        saveStringValue(AD_INFO,json);
    }

    public Advert getSplashInfo(){
        String json =getStringByKey(AD_INFO,null);
        if(json!=null){
            Type type = new TypeToken<Advert>(){
            }.getType();
            return new GsonBuilder().create().fromJson(json, type);
        }
        return null;
        }
}
