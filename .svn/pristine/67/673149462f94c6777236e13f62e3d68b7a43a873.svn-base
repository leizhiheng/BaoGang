package com.baosteel.qcsh.model;

import org.json.JSONObject;

import com.baosteel.qcsh.constants.ConstantsAPI;

/**
 * 猜你喜欢-商品
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-2
 */
public class ProductMaybeLike {

	private String id;// 商品id,
	private String price;// 商品价格,
	private String name;// 商品名称,
	private String img;// 图片",
	private String goods_genre;// 商品类型  1实物商品，2服务商品(后面可能还会新加类型)
	private String sale_count;//销量

	public ProductMaybeLike(){
		
	}
	
	public ProductMaybeLike(JSONObject obj) {
		id = obj.optString("id");
		price = obj.optString("price");
		name = obj.optString("name");
		img = obj.optString("img");
		goods_genre = obj.optString("goods_genre");
		sale_count = obj.optString("sale_count");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	/**
	 * 获取商品类型
	 * @return
	 */
	public int getGoods_genre() {
		try{
			return Integer.parseInt(goods_genre);
		}catch(Exception e){
			e.printStackTrace();
			return ConstantsAPI.Goods_Genre_Normal;
		}
	}

	public void setGoods_genre(String goods_genre) {
		this.goods_genre = goods_genre;
	}

	public String getSaleCount() {
		return sale_count;
	}

	public void setSaleCount(String saleCount) {
		this.sale_count = saleCount;
	}
	
	
}
