package com.common.net;

import java.util.HashMap;
import java.util.Map;

import android.text.TextUtils;

import com.baosteel.qcsh.api.URLs;
import com.common.volley.Request.Method;

/**
 * 请求参数
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-27
 */
public class RequestParams {
	
	public String url;
	public int method;
	private Map<String, String> params;
	
	public RequestParams(){
		params = new HashMap<String, String>();
		method = Method.POST;
	}
	
	public void addParam(String key, String value){
		if(TextUtils.isEmpty(key)){
			return;
		}
		
		if(null == value){
			value = "";
		}
		
		this.params.put(key, value);
	}

	public Map<String, String> getParms() {
		return params;
	}
	
	/**
	 * 获取接口方法名
	 * @return
	 */
	public String getMethod(){
		return url.replace(URLs.ServerUrl, "");
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(method == Method.POST ? "post:" : "get:");
		sb.append(url);
		
		boolean flag = false;
		for(String key : params.keySet()){
			if(!flag){
				sb.append("?");
				sb.append(key+"="+params.get(key));
				flag = true;
			}else{
				sb.append("&"+key+"="+params.get(key));
			}
		}
		
		return sb.toString();
	}
}
