package com.baosteel.qcsh.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description 商品分类的类型数据对象 Author blue Time 2015/8/31
 */
public class ProductCategory implements Serializable{
	private String id;
	/** 分类名称 */
	private String name;
	/** 分类图标 */
	private String img_url;
	/** 分类等级 */
	private String level;
	/** 该分类下的商品 */
	private ArrayList<ProductCategory> mCatogeryItems;
	/** 该分类下的子分类 */
	private List<ProductCategory> mChildCategories;

	private boolean isSelected;

	public ProductCategory(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public ArrayList<ProductCategory> getmCatogeryItems() {
		return mCatogeryItems;
	}

	public void setmCatogeryItems(ArrayList<ProductCategory> mCatogeryItems) {
		this.mCatogeryItems = mCatogeryItems;
	}

	public List<ProductCategory> getmChildCategories() {
		return mChildCategories;
	}

	public void setmChildCategories(List<ProductCategory> mChildCategories) {
		this.mChildCategories = mChildCategories;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
