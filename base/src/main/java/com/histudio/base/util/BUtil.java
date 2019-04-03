package com.histudio.base.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BUtil {

	public static String converIntsToString(int[] ints) {
		StringBuffer sb = new StringBuffer();
		for (int cha : ints) {
			sb.append((char) cha);
		}
		return sb.toString();
	}

	// 获取manifest中的meta数据
	public static String getMetaValue(Context context, String metaKey) {
		Bundle metaData = null;
		String metaValue = null;
		if (context == null || metaKey == null) {
			return null;
		}
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
					PackageManager.GET_META_DATA);
			if (null != ai) {
				metaData = ai.metaData;
			}
			if (null != metaData) {
				metaValue = metaData.getString(metaKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return metaValue;
	}

	/**
	 * <pre>
	 * 字节单位转化转换
	 * 数值在1~1024，单位根据大小变化而变化,最大单位为GB
	 * </pre>
	 * 
	 * @param size
	 *            传进来的必须是字节
	 * 
	 */
	public static String formatByteDes(long size) {
		size = (size == 0 ? 1 : size);
		if (size > 1024 * 1024 * 1024) {
			return apkSizeFormat(size / (1024.0 * 1024 * 1024), "GB");

		} else if (size > 1024 * 1024) {
			return apkSizeFormat(size / (1024.0 * 1024), "MB");

		} else if (size > 1024) {
			return apkSizeFormat(size / 1024.0, "KB");

		} else {
			return apkSizeFormat(size, "B");
		}

	}

	private static final NumberFormat apkSizeFormat = new DecimalFormat("0.##");

	/**
	 * apk 大小
	 * 
	 * @param size
	 * @param unit
	 * @return
	 */
	public static String apkSizeFormat(double size, String unit) {
		return apkSizeFormat.format(size) + unit;
	}

	/**
	 * 是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
        return str != null && (str.matches("\\d+") || str.matches("-\\d+"));
    }



	/**
	 * 判断当前是否是处于开发模式
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isDebug(Context context) {
		try {
			PackageManager mgr = context.getPackageManager();
			ApplicationInfo info = mgr.getApplicationInfo(context.getPackageName(), 0);
			return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) == ApplicationInfo.FLAG_DEBUGGABLE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
