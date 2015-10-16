package com.baosteel.qcsh.api;

import com.common.net.RequestParams;
import com.common.volley.Request;

/**
 * 网络请求参数统一管理
 *
 * @author jws
 */
public class HttpParamsUtil {
    /**
     * 登录
     * @param userName
     * @param password
     * */
    public static RequestParams loginParams(String userName,String password){
        RequestParams params = new RequestParams();
        params.url = URLs.user_queryAppLogin;
        params.method = Request.Method.POST;
        params.addParam("userName", userName);
        params.addParam("password", password);
        return params;
    }
}
