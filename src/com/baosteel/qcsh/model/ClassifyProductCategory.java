package com.baosteel.qcsh.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 商品分类的类型数据对象
 * Author blue
 * Time 2015/8/31
 */
public class ClassifyProductCategory extends ProductCategory{
    /**该分类下的商品*/
    private List<ClassifyProductCategoryItem> mClassifyCatogeryItems;
    
    public ClassifyProductCategory(String title) {
    	super(title);
    }

	public List<ClassifyProductCategoryItem> getmClassifyCatogeryItems() {
		return mClassifyCatogeryItems;
	}

	public void setmClassifyCatogeryItems(List<ClassifyProductCategoryItem> mClassifyCatogeryItems) {
		this.mClassifyCatogeryItems = mClassifyCatogeryItems;
	}
    
}
