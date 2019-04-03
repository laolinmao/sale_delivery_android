package com.histudio.base;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.histudio.base.constant.BConstants;
import com.histudio.base.entity.ISingleable;
import com.histudio.base.util.BUtil;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象管理器
 * 
 * @author ljh
 * @data 2014-9-16
 */
public class HiManager implements ISingleable {

	public static HiApplication mContext = HiApplication.instance;
	private static ContextConfig contextConfig = new ContextConfig();

	/** 存放单例对象 */
	private static final ConcurrentHashMap<Class, Object> singletonInstanceMap = new ConcurrentHashMap<Class, Object>(
			200, 0.75f);

	/**
	 * 简单获取单例对象
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getBean(clazz, null, null);
	}

	/**
	 * 获取单例对象
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T getBean(Class<T> clazz, Class<T>[] argClazz, Object[] args) {
		if (!ISingleable.class.isAssignableFrom(clazz)) {
			return null;
		}
		Object instance = singletonInstanceMap.get(clazz);
		if (instance == null) {
			synchronized (clazz) {
				instance = singletonInstanceMap.get(clazz);
				if (instance == null) {
					try {
						if (argClazz == null) {
							instance = clazz.newInstance();
						} else {
							Constructor<T> constructor = clazz.getDeclaredConstructor(argClazz);
							constructor.setAccessible(true);
							instance = constructor.newInstance(args);
						}
						singletonInstanceMap.put(clazz, instance);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return (T) instance;
	}

	public static void initContextConfig() {
		try {
			// 客户端类型
			contextConfig.setClientType(BConstants.CLIENT_TYPE_ANDROID_PHO);

			// 获取渠道号
			String channelId = BUtil.getMetaValue(mContext, BConstants.CHANNEL_KEY);
			contextConfig.setChannelId(channelId);

			// sim卡id
			TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
			String imsi = tm.getSubscriberId();
			contextConfig.setSimId(imsi);

			// imei
			String imei = tm.getDeviceId();
			contextConfig.setImei(imei);

			// 手机型号名字
			String model = Build.MODEL;
			contextConfig.setModel(model);

			// 是否是debug模式
			contextConfig.setDebug(BUtil.isDebug(mContext));

			// 版本
			PackageManager manager = mContext.getPackageManager();
			PackageInfo info;
			try {
				info = manager.getPackageInfo(mContext.getPackageName(), 0);
				if (info != null) {
					contextConfig.setVersion(info.versionName);
					contextConfig.setVersionCode(info.versionCode);
				} else {
					contextConfig.setVersion("1.0.0");
					contextConfig.setVersionCode(0);
				}
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}

			// 固件版本
			String firmware = Build.VERSION.RELEASE;
			contextConfig.setFirmware(firmware);

			// sdkversion
			String sdkVersion = Build.VERSION.SDK;
			contextConfig.setSdkVersion(sdkVersion);

			// 硬件架构
			String abi = Build.CPU_ABI;
			abi = (abi == null || abi.trim().length() == 0) ? "" : abi;
			contextConfig.setAbi(abi);

			WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
			DisplayMetrics displayMetrics = new DisplayMetrics();
			Display display = windowManager.getDefaultDisplay();
			display.getMetrics(displayMetrics);
			contextConfig.setDensity(displayMetrics.density + "");
			contextConfig.setScreenWidth(displayMetrics.widthPixels);
			contextConfig.setScreenHeight(displayMetrics.heightPixels);
			contextConfig.setResolution(contextConfig.getScreenWidth() + "x" + contextConfig.getScreenHeight());

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注册定时器处理
	 * 
	 * @return
	 */
	public static void initAlarm() {
		// 注册定时更新
		AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

		// 1分钟
		Intent intent = new Intent();
		intent.setPackage(mContext.getPackageName());
		intent.setAction(BConstants.ALARM_REGISTER_1MIN_CLOCK);
		PendingIntent contentIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()
				+ BConstants.TIME_DELAY_REL_MIN, BConstants.TIME_DELAY_REL_MIN, contentIntent);

		// 5分钟
		intent.setPackage(mContext.getPackageName());
		intent.setAction(BConstants.ALARM_REGISTER_5MIN_CLOCK);
		contentIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()
				+ BConstants.TIME_DELAY_REL_SHORT, BConstants.TIME_DELAY_REL_SHORT, contentIntent);

		// 15分钟
		intent.setPackage(mContext.getPackageName());
		intent.setAction(BConstants.ALARM_REGISTER_15MIN_CLOCK);
		contentIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()
				+ BConstants.TIME_DELAY_REL_NORMAL, BConstants.TIME_DELAY_REL_NORMAL, contentIntent);

		// 60分钟
		intent.setPackage(mContext.getPackageName());
		intent.setAction(BConstants.ALARM_REGISTER_60MIN_CLOCK);
		contentIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()
				+ BConstants.TIME_DELAY_REL_LONG, BConstants.TIME_DELAY_REL_LONG, contentIntent);

		// 注册消息
		HiBroadcastReceiver hiBroadcastReceiver = new HiBroadcastReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(BConstants.ALARM_REGISTER_1MIN_CLOCK);
		intentFilter.addAction(BConstants.ALARM_REGISTER_5MIN_CLOCK);
		intentFilter.addAction(BConstants.ALARM_REGISTER_15MIN_CLOCK);
		intentFilter.addAction(BConstants.ALARM_REGISTER_60MIN_CLOCK);
		mContext.registerReceiver(hiBroadcastReceiver, intentFilter);

	}

	public static synchronized ContextConfig getContextConfig() {
		return contextConfig;
	}
}
