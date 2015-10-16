package com.common.view.fastlink;

import android.R.integer;

import com.baosteel.qcsh.R;


/**
 * 导航数据
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-31
 */
public class FastLinkData {
	
	/**数据ID**/
	public int id;
	
	/**名称**/
	private String name;
	
	/**图片路径**/
	public String imgUrl;
	public int imgId;
	
	/**数据类型(可以根据这个来做不同界面的跳转)**/
	public String type;

	/**根据类型不一跳转不同页面**/
	public int flag;

	/**类别id**/
	public int goods_type_id;

	public FastLinkData(int id, String name){
		this.id = id;
		this.imgId = R.drawable.icon_home_acb;
		this.type = "1";
		this.name = name;
	}
	
	public FastLinkData(int id, String name,int imgId){
		this.id = id;
		this.imgId = imgId;
		this.type = "1";
		this.name = name;
	}
	
	public FastLinkData(int id, String name, String imgUrl){
		this.id = id;
		this.imgUrl = imgUrl;
		this.type = "1";
		this.name = name;
	}

	public FastLinkData(int id, String name, String imgUrl,int flag,int goods_type_id){
		this.id = id;
		this.imgUrl = imgUrl;
		this.type = "1";
		this.name = name;
		this.flag = flag;
		this.goods_type_id = goods_type_id;
	}
	

	public int getId() {return id;}
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {this.flag = flag;}
	public void setChannelId(int id) {
		this.id = id;
	}
	
	public int getChannelId() {
		return this.id;
	}

	public void setImageUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getImageUrl() {
		return imgUrl;
	}

	public int getImgId() {
		return imgId;
	}

	public int getGoods_type_id() {return goods_type_id;}

	public void setGoods_type_id(int goods_type_id) {this.goods_type_id = goods_type_id;}
}
