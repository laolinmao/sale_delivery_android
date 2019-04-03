package com.histudio.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.histudio.base.constant.BConstants;
import com.socks.library.KLog;

/**
 * 消息接收器
 * 
 * @author ljh
 * 
 * @data 2014-9-25
 */
public class HiBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent == null) {
			return;
		}

		String action = intent.getAction();
		if (BConstants.ALARM_REGISTER_1MIN_CLOCK.equals(action)) {
			KLog.i("ALARM_REGISTER_1MIN_CLOCK");
			HiManager.getBean(GlobalHandler.class).sendEmptyMessage(BConstants.ALERT_CLOCK_1_MIN);
		}
		else if (BConstants.ALARM_REGISTER_5MIN_CLOCK.equals(action)) {
			KLog.i("ALARM_REGISTER_5MIN_CLOCK");
			HiManager.getBean(GlobalHandler.class).sendEmptyMessage(BConstants.ALERT_CLOCK_5_MIN);

		} else if (BConstants.ALARM_REGISTER_15MIN_CLOCK.equals(action)) {
			KLog.i("ALARM_REGISTER_15MIN_CLOCK");
			HiManager.getBean(GlobalHandler.class).sendEmptyMessage(BConstants.ALERT_CLOCK_15_MIN);

		} else if (BConstants.ALARM_REGISTER_60MIN_CLOCK.equals(action)) {
			KLog.i("ALARM_REGISTER_60MIN_CLOCK");
			HiManager.getBean(GlobalHandler.class).sendEmptyMessage(BConstants.ALERT_CLOCK_60_MIN);

		}
	}
}
