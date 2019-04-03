package com.histudio.base;

import com.histudio.base.entity.ISingleable;
import com.histudio.base.util.JsonUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.List;

/**
 * 基于sp的文件缓存操作类
 * 
 * @author qsj
 */
public class HiFao implements ISingleable {

	private SharedPreferences basePref;

	public HiFao(String spFile) {
		basePref = HiApplication.instance.getSharedPreferences(spFile, Context.MODE_PRIVATE);
	}

	/**
	 * 保存boolean类型的数据
	 * 
	 * @param key
	 * @param value
	 */
	public void saveBooleanValue(String key, boolean value) {
		Editor editor = basePref.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 保存整形数据
	 * 
	 * @param key
	 * @param value
	 */
	public void saveIntValue(String key, int value) {
		Editor editor = basePref.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 保存字符串数据
	 * 
	 * @param key
	 * @param value
	 */
	public void saveStringValue(String key, String value) {
		Editor editor = basePref.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 获取字符串数据
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getStringByKey(String key, String defaultValue) {
		return basePref.getString(key, defaultValue);
	}

	/**
	 * 获取整形数据
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public int getIntByKey(String key, int defaultValue) {
		return basePref.getInt(key, defaultValue);
	}

	/**
	 * 获取布尔型数据
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public boolean getBooleanByKey(String key, boolean defaultValue) {
		return basePref.getBoolean(key, defaultValue);
	}

	/**
	 * 保存字符串list数据
	 * 
	 * @param key
	 * @param value
	 */
	public void saveListStrValue(String key, List<String> mListStr) {
		Editor editor = basePref.edit();
		String json = JsonUtil.toStringFromList(mListStr);
		editor.putString(key, json);
		editor.commit();
	}

	/**
	 * 获取字符串list数据
	 */
	public List<String> getListStrValue(String key, List<String> defaultValue) {
		String json = basePref.getString(key, null);
		if(json == null){
			if(defaultValue == null){
				return null;
			} else {
				json = JsonUtil.toStringFromList(defaultValue);
			}
		}
		List<String> mListStr = JsonUtil.getEntityListByString(json, String.class);
		return mListStr;
	}
}
