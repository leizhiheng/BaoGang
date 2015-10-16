package com.common.view.banner;

import java.io.Serializable;

/**
 * 导航数据
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-31
 */
public class BannerData implements Serializable{
	
	/**数据ID(可以是商品ID，也可以是活动ID)**/
	private String id;
	
	/**图片路径**/
	private String imgUrl;
	
	/**数据类型(可以根据这个来做不同界面的跳转)**/
	private String type;

	/**货品id**/
	private String goods_sn;

	/**商品id**/
	private String goods_id;

	public BannerData(){
		this.id = "1";
		this.imgUrl = "http://lehumall.b0.upaiyun.com//upload/image/admin/2015/20150824/201508242224519073.jpg";
		this.type = "1";
	}
	
	public BannerData(String id, String imgUrl, String type) {
		this.id = id;
		this.imgUrl = imgUrl;
		this.type = type;
	}

	public String getGoods_sn() {
		return goods_sn;
	}

	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
