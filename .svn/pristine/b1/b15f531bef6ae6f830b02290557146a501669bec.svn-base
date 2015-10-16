package com.baosteel.qcsh.ui.activity.home.healthy;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.model.ProductCategoryItem;
import com.baosteel.qcsh.model.homeclassify.ClassifyRank2;
import com.baosteel.qcsh.ui.fragment.commen.ProductCategoryFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 
 * @description 页面：健康宝-》健康商城
 * @author blue
 * @Time 2015-9-14
 *
 */
public class HealthyMallActivity extends BaseActivity{
	
	private EditText mEtSearch;
	/**
     * 分类信息
     */
    private ArrayList<ClassifyRank2> mCatogeries;
    private ProductCategoryFragment mProductCategoryFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongue_type);
        
        initData();
        initView();
        
        loadData();
    }
    
    private void initData() {
    	mCatogeries = new ArrayList<ClassifyRank2>();
    }

    private void initView() {
    	setTitle("健康商城");
    	mTitleBar.setBackgroud(R.color.theme_color_healthy);

    	mProductCategoryFragment = (ProductCategoryFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_product_category);
        mProductCategoryFragment.setmColorSelected(getResources().getColor(R.color.theme_color_healthy));
    
        mEtSearch = (EditText) findViewById(R.id.et_search_input);
        mEtSearch.setHint("寻找您喜欢的东西");
    }
    
    private void loadData() {
    	
    	mCatogeries.add(new ClassifyRank2("女性保健"));
    	mCatogeries.add(new ClassifyRank2("男性保健"));
    	mCatogeries.add(new ClassifyRank2("老年保健"));
    	mCatogeries.add(new ClassifyRank2("儿童营养"));
    	mCatogeries.add(new ClassifyRank2("基础保健"));
    	mCatogeries.add(new ClassifyRank2("健康食品"));
    	mCatogeries.add(new ClassifyRank2("传统保健"));
    	
    	ArrayList<ProductCategory> items = new ArrayList<ProductCategory>();
    	items.add(new ProductCategory("提升精力"));
    	items.add(new ProductCategory("缓解疲劳"));
    	items.add(new ProductCategory("解救养肝"));
    	items.add(new ProductCategory("滋补美颜"));
    	items.add(new ProductCategory("清肺润喉"));
    	
    	mCatogeries.get(0).setSelected(true);
    	
    	for (int i = 0; i < mCatogeries.size(); i++) {
			mCatogeries.get(i).setmCatogeryItems(items);
		}
    	
    	mProductCategoryFragment.setData(mCatogeries, 2);
    }
}
