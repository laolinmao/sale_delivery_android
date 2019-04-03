package com.histudio.base.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class ApkUtil {

	public static String getVersionName(Context mContext){
		PackageManager pm = mContext.getPackageManager();
		String versionName = "";
		try {
			PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
			versionName = pi.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return versionName;
	}
	
	public static int getVersionCode(Context mContext){
		PackageManager pm = mContext.getPackageManager();
		int versionCode = 0;
		try {
			PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
			versionCode = pi.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return versionCode;
	}
	
}
