package com.common.view.pulltorefresh;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class Utils {

	static final String LOG_TAG = "PullToRefresh";

	public static void warnDeprecation(String depreacted, String replacement) {
		Log.w(LOG_TAG, "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurrTiem() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
		return dateFormat.format(now);
	}
}
