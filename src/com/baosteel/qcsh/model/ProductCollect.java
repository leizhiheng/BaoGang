package com.baosteel.qcsh.model;

import org.json.JSONObject;

/**
 * 收藏的商品
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-08
 */
public class ProductCollect {
	
	private String imgUrl;//商品图片
	private String goodsId;//商品id
	private String goodsPrice;//商品价格
	private String goodsName;//产品标题
	private String sellerStatus;//商家状态码

	
	public ProductCollect(JSONObject obj){
		imgUrl = obj.optString("imgUrl");
		goodsId = obj.optString("goodsId");
		goodsPrice = obj.optString("goodsPrice");
		sellerStatus = obj.optString("sellerStatus");
		goodsName = obj.optString("goodsName");
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public String getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}


	public String getGoodsPrice() {
		return goodsPrice;
	}


	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}


	public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSellerStatus() {
		return sellerStatus;
	}

	public void setSellerStatus(String sellerStatus) {
		this.sellerStatus = sellerStatus;
	}
}
