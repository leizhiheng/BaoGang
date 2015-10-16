package com.baosteel.qcsh.model.store;

import org.json.JSONObject;

import com.baosteel.qcsh.database.DatabaseManager.ProductColumns;

import android.database.Cursor;

/**
 * 商品表数据模型
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-28
 */
public class StoreCollect {
	
	private String id;							//用户编号
	private String merchant_name;				//商家名称,
	private String background_img_app;			//商家背景图,
	private String merchant_id;					//商家编号
	private String status;						//商家状态
	private String status_name;					//商家状态提示

	public StoreCollect(JSONObject obj){
		id = obj.optString("id");//用户编号
		merchant_name = obj.optString("merchant_name");//商家名称,
		background_img_app = obj.optString("background_img_app");//商家背景图,
		merchant_id = obj.optString("merchant_id");//商家编号
		status = obj.optString("status");//商家状态
		status_name = obj.optString("status_name");//商家状态提示
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getBackground_img_app() {
		return background_img_app;
	}

	public void setBackground_img_app(String background_img_app) {
		this.background_img_app = background_img_app;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
}
