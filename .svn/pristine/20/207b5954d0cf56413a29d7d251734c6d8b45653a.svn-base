package com.common.net;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

import android.util.Log;

import com.common.utils.LogUtil;
import com.common.volley.AuthFailureError;
import com.common.volley.NetworkResponse;
import com.common.volley.ParseError;
import com.common.volley.Request;
import com.common.volley.Response;
import com.common.volley.Response.ErrorListener;
import com.common.volley.Response.Listener;
import com.common.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

/**
 * Volley自定义Request
 * 
 * @author liujing
 * @param <T>
 */
public class RequestJSONBean<T> extends Request<T> {

	public static final String TAG = "Request";
	
	public static final int GET = Method.GET;
	public static final int POST = Method.POST;
	private static Gson gson = new Gson();
	private Type mType;
	private final ResponeListener<T> mlistener;
	private Map<String, String> mParams;

	// 自定义回调，方便使用
	public interface ResponeListener<T> extends ErrorListener, Listener<T> {
	}

	/**
	 * 默认get方式提交http请求
	 * 
	 * @param url
	 *            请求地址
	 * @param type
	 *            JSONBean对应的class
	 * @param params
	 *            请求参数
	 * @param listener
	 *            监听回调
	 */
	public RequestJSONBean(String url, Type type, Map<String, String> params, ResponeListener<T> listener) {
		super(GET, getUrl(url, params), listener);
		LogUtil.i(TAG, "get:"+getUrl(url, params));
		
		this.mlistener = listener;
		this.mType = type;
		setShouldCache(false);
	}

	/**
	 * 增加method参数选择get或post方式提交http请求
	 * 
	 * @param method
	 * @param url
	 * @param type
	 * @param params
	 * @param listener
	 */
	public RequestJSONBean(int method, String url, Type type, Map<String, String> params, ResponeListener<T> listener) {
		super(method, method == POST ? url : getUrl(url, params), listener);
		
		String methodType = method == POST ? "post" : "get";
		LogUtil.i(TAG, methodType + ":" + getUrl(url, params));
		this.mlistener = listener;
		this.mType = type;
		if (method == POST) {
			mParams = params;
		}
		setShouldCache(false);
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		if (mParams != null) {
			return mParams;
		} else {
			return super.getParams();
		}
	}

	// 构造url，get方式请求也可直接传参数
	private static String getUrl(String url, Map<String, String> params) {
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			StringBuffer sb = null;
			while (it.hasNext()) {
				String key = it.next();
				String value = params.get(key);
				if (sb == null) {
					sb = new StringBuffer();
					sb.append("?");
				} else {
					sb.append("&");
				}
				sb.append(key);
				sb.append("=");
				sb.append(value);
			}
			url += sb.toString();
		}
		return url;
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			T result = null;
			String jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			LogUtil.i(TAG, "result:"+jsonStr);
			
			result = gson.fromJson(jsonStr, mType);
			return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		mlistener.onResponse(response);
	}
}