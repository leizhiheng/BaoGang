package com.baosteel.qcsh.model;

import org.json.JSONObject;

import com.baosteel.qcsh.ui.activity.my.order.ReturnAddActivity;

/**
 * 我的足迹商品
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-08
 */
public class ProductRecord {
	
	private String id;				//足迹id,
	private String look_timestamp;	//浏览的时间戳,
	private String name;			//商品名称
	private String img;				//商品图片
	private String price;			//商品价格，
	private String goods_id;		//商品id,
	private String look_time;		//浏览的时间字符串
	private String sellerStatus;	//商家状态
	
	
	public ProductRecord(JSONObject obj){
		id = obj.optString("id");
		look_timestamp = obj.optString("look_timestamp");
		name = obj.optString("name");			
		img = obj.optString("img");	
		price = obj.optString("price");
		goods_id = obj.optString("goods_id");
		look_time = obj.optString("look_time");
		sellerStatus = obj.optString("sellerStatus");
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLook_timestamp() {
		return look_timestamp;
	}


	public void setLook_timestamp(String look_timestamp) {
		this.look_timestamp = look_timestamp;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getGoods_id() {
		return goods_id;
	}


	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getSellerStatus() {
		return sellerStatus;
	}

	public void setSellerStatus(String sellerStatus) {
		this.sellerStatus = sellerStatus;
	}

	//只精确到 yyyy-MM-dd
	private String dayTime;
	public String getLook_time() {
		try{
			if(null == dayTime){
				dayTime = look_time.split(" ")[0];
			}
			return dayTime;
		}catch(Exception e){
			return look_time;
		}
	}


	public void setLook_time(String look_time) {
		this.look_time = look_time;
	}
}
