package com.baosteel.qcsh.model;

import com.baosteel.qcsh.constants.ConstantsAPI;

public class BaseProductData {

	// id：商品id，
	// logistics_style:运费方式：1表示固定运费，使用字段logistics_cost，2表示运费模版，使用express_postage
	// logistics_cost：固定运费，
	// express_postage：默认模版运费，
	// goods_genre：商品类型；1表示实物，2表示服务
	// city_name:商家所在市，
	// area_name：商家所在区
	// "price": 10,
	// "name": "test品牌003",
	// "img":
	// "http://d1-img.java.shovesoft.com/flie_upload/image/20150924/20150924164457_545.jpg",
	// "sale_count": 0,
	// "seller_id": 33,

	protected String id;
	protected String logistics_style;
	protected String logistics_cost;
	protected String express_postage;
	protected String goods_genre;
	protected String city_name;
	protected String area_name;
	protected String price;
	protected String name;
	protected String img;
	protected String sale_count;
	protected String seller_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLogistics_style() {
		try{
			return Integer.parseInt(logistics_style);
		}catch(Exception e){
			return 1;
		}
	}

	public void setLogistics_style(String logistics_style) {
		this.logistics_style = logistics_style;
	}

	public String getLogistics_cost() {
		return logistics_cost;
	}

	public void setLogistics_cost(String logistics_cost) {
		this.logistics_cost = logistics_cost;
	}

	public String getExpress_postage() {
		return express_postage;
	}

	public void setExpress_postage(String express_postage) {
		this.express_postage = express_postage;
	}

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

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
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

	public String getSale_count() {
		return sale_count;
	}

	public void setSale_count(String sale_count) {
		this.sale_count = sale_count;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

}
