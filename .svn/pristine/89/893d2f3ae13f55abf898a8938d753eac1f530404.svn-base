package com.common.net;

import org.json.JSONObject;

import android.content.Context;
import android.widget.Toast;

import com.baosteel.qcsh.api.RequestCallback;
import com.common.volley.AuthFailureError;
import com.common.volley.NetworkError;
import com.common.volley.NoConnectionError;
import com.common.volley.ParseError;
import com.common.volley.Request;
import com.common.volley.RequestQueue;
import com.common.volley.ServerError;
import com.common.volley.TimeoutError;
import com.common.volley.VolleyError;
import com.common.volley.VolleyLog;
import com.common.volley.toolbox.JsonObjectRequest;
import com.common.volley.toolbox.MySingleton;
import com.common.volley.toolbox.Volley;

/**
 * 网络请求工具
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-27
 */
public class HttpUtils {
	
	 /**
     * 添加Request请求到队列中，因为我们使用OkHttp作为Volley的传输层，所以增加一个HttpStack参数
     * @param request
     */
    public static void addRequest(Context context, Request<?> request) {
        if (request != null) {
        	request.setShouldCache(false);
        	
            //MySingleton.getInstance(context).addToRequestQueue(request);
        	RequestQueue requestQueue = Volley.newRequestQueue(context);
			requestQueue.add(request);
        }
    }
    
    
    /**
     * 添加请求到队列
     * @param context
     * @param params
     * @param showLoadingDialog
     * @param listener
     */
    public static void addRequest(final Context context, RequestParams params, final RequestCallback<JSONObject> listener){
		if (null != params) {
			JsonObjectRequest req = new JsonObjectRequest(params.url, params.getParms(), listener, listener);
			req.setShouldCache(false);
			
			//用这种方式，没有参数的接口，请求没有回调，郁闷
			MySingleton.getInstance(context).addToRequestQueue(req);
			//RequestQueue requestQueue = Volley.newRequestQueue(context);
			//requestQueue.add(req);
		}
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
    
    
    /**
     * 取消所有Request
     */
    public static void cancelAllRequest(Context context) {
        RequestQueue localRequestQueue = MySingleton.getInstance(context).getRequestQueue();
        if (localRequestQueue != null) {
            localRequestQueue.cancelAll(new RequestQueue.RequestFilter() {

                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
            localRequestQueue.stop();
        }
    }
    
    /**
     * 根据标签取消指定Request
     * @param tag
     */
    public static void cancelRequestByTag(Context context, Object tag) {
        if (null != tag) {
        	MySingleton.getInstance(context).cancelAll(tag);
        }
    }
    
}
