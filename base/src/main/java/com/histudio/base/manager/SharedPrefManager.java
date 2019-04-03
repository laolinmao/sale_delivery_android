package com.histudio.base.manager;

import com.histudio.base.HiFao;
import com.histudio.base.HiManager;
import com.histudio.base.constant.BConstants;
import com.histudio.base.entity.ISingleable;

import android.text.TextUtils;

/**
 * 基本的配置持久化文件
 * 
 */
public class SharedPrefManager extends HiFao implements ISingleable {

	public static final String HI_SHARED_PREF = "hi_shared_pref";

	public SharedPrefManager() {
		super(HI_SHARED_PREF);
	}

	/**
	 * 判断引导版本
	 * 
	 * @return
	 */
	public boolean isGuidVersion() {
		String isInstall = HiManager.getBean(SharedPrefManager.class).getStringByKey(
				BConstants.IS_GUID_VERSION, "");
		return TextUtils.isEmpty(isInstall);
	}

	/**
	 * 保存引导版本 
	 */
	public void saveGuidVersion() {
		HiManager.getBean(SharedPrefManager.class).saveStringValue(BConstants.IS_GUID_VERSION,
				BConstants.IS_GUID_VERSION);
	}
	
}
