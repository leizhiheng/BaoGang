package com.common.volley.toolbox;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.widget.Toast;

import com.common.volley.AuthFailureError;
import com.common.volley.NetworkError;
import com.common.volley.NoConnectionError;
import com.common.volley.ParseError;
import com.common.volley.ServerError;
import com.common.volley.TimeoutError;
import com.common.volley.VolleyError;
import com.common.volley.VolleyLog;

/**
 * 请求参数类.
 */
public class RequestParams {

	/**
	 * 将参数转换成需要的键值对形式（非登录）
	 * 
	 * @param context
	 *            上下文
	 * @param opt
	 *            接口编号
	 * @param info
	 *            参数格式
	 * @return Map键值对参数
	 */
	public static Map<String, String> convertParams(Context context, String opt, String info) {
//		String key = MD5.md5(AppTools.MD5_key);
//		String time = RspBodyBaseBean.getTime();
//
//		String uid = "-1";
//		if (null != AppTools.user) {
//			uid = AppTools.user.getUid();
//		}
//		String imei = RspBodyBaseBean.getIMEI(context);
//		String crc = RspBodyBaseBean.getCrc(time, imei, key, info, uid);
//		String auth = RspBodyBaseBean.getAuth(crc, time, imei, uid);

		Map<String, String> params = new HashMap<String, String>();
//		params.put("opt", opt);
//		params.put("auth", auth);
//		params.put("info", info);
		return params;
	}

    /**
     * 将参数转换成需要的键值对形式（登录接口）
     *
     * @param context
     *            上下文
     * @param opt
     *            接口编号
     * @param info
     *            参数格式
     * @return Map键值对参数
     */
    public static Map<String, String> convertLoginParams(Context context, String opt, String info) {
//		String key = MD5.md5(AppTools.MD5_key);
//		String time = RspBodyBaseBean.getTime();
//
//		String uid = "-1";
//		if (null != AppTools.user) {
//			uid = AppTools.user.getUid();
//		}
//		String imei = RspBodyBaseBean.getIMEI(context);
//		String crc = RspBodyBaseBean.getCrc(time, imei, key, info, uid);
//		String auth = RspBodyBaseBean.changeLogin_Auth(crc, time, imei);

		Map<String, String> params = new HashMap<String, String>();
//		params.put("opt", opt);
//		params.put("auth", auth);
//		params.put("info", info);
		return params;
	}

	/**
	 * 将错误转换成具体信息
	 * 
	 * @param error
	 *            VolleyError错误
	 * @param shouldToast
	 *            是否弹出Toast
	 * @return 具体错误信息
	 */
	public static String convertError(Context context, VolleyError error, boolean shouldToast) {
		VolleyLog.e("error名称 ： %s  //  error内容 ： %s", error.getClass().getName(), error.getMessage());
		String back = null;
		Class<?> clazz = error.getClass();
		if (clazz.equals(TimeoutError.class)) {
			back = "请求超时！";
		} else if (clazz.equals(ServerError.class)) {
			back = "服务器响应错误！";
		} else if (clazz.equals(NoConnectionError.class)) {
			back = "无法建立连接！";
		} else if (clazz.equals(NetworkError.class)) {
			back = "网络异常！请检查网络";
		} else if (clazz.equals(AuthFailureError.class)) {
			back = "身份验证失败!";
		} else if (clazz.equals(ParseError.class)) {
			back = "服务器响应解析错误!";
		}
		if (shouldToast && back != null){
			//MyToast.getToast(context, back).show();
			Toast.makeText(context, back, Toast.LENGTH_LONG).show();
		}

		return back;
	}
}
