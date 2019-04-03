package com.histudio.base;


import com.histudio.base.entity.ISingleable;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局消息处理器
 * 
 * arg1标记为MESSAGE_HANDLED时候就不往下面发送
 * 
 * @author qiush_000
 * 
 */
public class GlobalHandler extends Handler implements ISingleable {

	public static final int MESSAGE_HANDLED = -1;

	private HiApplication mContext;
	private static List<Handler> handlerList = new ArrayList<Handler>();

	public GlobalHandler() {
		super(Looper.getMainLooper());
		mContext = HiApplication.instance;
	}

	public synchronized void registeHandler(Handler handler) {
		if (!handlerList.contains(handler)) {
			handlerList.add(handler);
		}
	}

	public synchronized void unregistHandler(Handler handler) {
		if (handlerList.contains(handler)) {
			handlerList.remove(handler);
		}
	}

	@Override
	public void handleMessage(Message msg) {
		try {
			synchronized (this) {
				// 先于子处理器执行
				try {
					mContext.preSubHandler(msg);
				} catch (Exception e1) {
				}

				// 子处理器执行,最后注册的先接收
				for (int index = handlerList.size() - 1; index >= 0; index--) {
					if (msg.arg1 != MESSAGE_HANDLED) {
						try {
							handlerList.get(index).handleMessage(msg);
						} catch (Exception e) {
							continue;
						}
					}
				}

				// 子处理器之后执行
				if (msg.arg1 != MESSAGE_HANDLED) {
					try {
						mContext.afterSubHandler(msg);
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 异步任务，子线程处理
	 * 
	 * @param runnable
	 */
	public static void executeAsyncTask(Runnable runnable) {
		AsyncTask.execute(runnable);
	}
}