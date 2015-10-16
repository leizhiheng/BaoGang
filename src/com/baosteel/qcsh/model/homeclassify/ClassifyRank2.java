package com.baosteel.qcsh.model.homeclassify;

import java.util.List;

import com.baosteel.qcsh.model.ProductCategory;

/**
 * 
 * @description 首页分类-二级分类数据
 * @author blue
 * @Time 2015-9-25
 * 
 */
public class ClassifyRank2 extends ProductCategory {

	public ClassifyRank2() {
		this(null);
	}

	public ClassifyRank2(String name) {
		super(name);
	}

	private boolean hasRank3 = true;// 二级分类下是否有三级分类，默认有
	private List<ProductCategory> list;

	public List<ProductCategory> getmRank3Items() {
		return list;
	}

	public void setmRank3Items(List<ProductCategory> mRank3Items) {
		this.list = mRank3Items;
	}

	public boolean isHasRank3() {
		return hasRank3;
	}

	public void setHasRank3(boolean hasRank3) {
		this.hasRank3 = hasRank3;
	}
}
