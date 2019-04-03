package com.histudio.base.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基于Gson的Json工具类, (entity,string,jsonobject三者之间的转化),部分方法需要优化
 */
public class JsonUtil {

    private static Gson gson = new GsonBuilder().create();

    /**
     * 获得gson单例对象
     */
    public static Gson getGson() {
        return gson;
    }

    /**
     * json对象转json字符串
     */
    public static String getJsonStringByJsonObject(JSONObject jsonObject) {
        return jsonObject.toString();
    }

    /**
     * json字符串转json对象
     */
    public static JSONObject getJsonObjectByString(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 实体转成json对象
     */
    public static JSONObject getJsonObjectByEntity(Object entity) {
        return getJsonObjectByString(toStringFromEntity(entity));
    }

    /**
     * jsonobject转实体
     */
    public static <T> T getEntityByJsonObject(JSONObject jsonObject, Class<T> clz) {
        return getEntityByString(getJsonStringByJsonObject(jsonObject), clz);
    }

    /**
     * 对象转Json字符串
     */
    public static String toStringFromEntity(Object object) {
        return gson.toJson(object);
    }

    /**
     * Json字符串解析成一个对象
     */
    public static <T> T getEntityByString(String response, Class<T> clz) {
        T t = gson.fromJson(response, clz);
        return t;
    }

    /**
     * Json字符串解析成一个对象列表
     * 会报错
     * com.google.gson.internal.LinkedTreeMap cannot be cast to XX
     */
    public static <T> List<T> getEntityListByString(String response, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(response).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;

    }

    public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }

    /**
     * List转Json字符串
     */
    public static <T> String toStringFromList(List<T> list) {
        return gson.toJson(list);
    }

    /**
     * List转Json字符串
     */
    public static <T> String toJsonFromMap(Map map) {
        return gson.toJson(map);
    }

    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
