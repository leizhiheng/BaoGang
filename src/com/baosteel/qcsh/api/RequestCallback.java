package com.baosteel.qcsh.api;

import android.content.Context;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.utils.Tip;
import com.common.volley.Response.ErrorListener;
import com.common.volley.Response.Listener;
import com.common.volley.VolleyError;

/**
 * 请求回调
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-23
 * @param <T>
 */
public abstract class RequestCallback<T> implements Listener<T>, ErrorListener{
	
	/**是否显示加载对话框**/
	private boolean showLoadDialog;
	
	/**是否为一次成功的结果**/
	public boolean isSuccessResult;
	
	public RequestCallback(boolean showLoadDialog){
		this.showLoadDialog = showLoadDialog;
	}
	
	public RequestCallback(){
		showLoadDialog = true;
	}
	
	@Override
	public void onErrorResponse(VolleyError error) {
	}
	
	 /**
     * 显示对话框
     * @param context
     * @param showLoadingDialog
     */
    public static void showLoadDialog(Context context, boolean showLoadingDialog){
    	try {
			if (showLoadingDialog) {
				Tip.showLoadDialog(context, context.getString(R.string.loading));
			}
		} catch (Exception e) {
		}
    }
    
	
	public void onStart(Context context){
		try {
			if (showLoadDialog) {
				Tip.showLoadDialog(context, context.getString(R.string.loading));
			}
		} catch (Exception e) {
		}
	}
	
	public void onFinish(){
		try {
			if (showLoadDialog) {
				Tip.colesLoadDialog();
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 
	 * @Description 隐藏loadDialog,onFinish方法也实现了该功能，但是onFinish方法会在onResponse方法中
	 * 				调用，不能控制loadDialog的隐藏时间
	 * @Author blue
	 * @Time 2015-9-28
	 */
	public void closeLoadDialog() {
		try {
			if (showLoadDialog) {
				Tip.colesLoadDialog();
			}
		} catch (Exception e) {
		}
	}
	
}
