package com.baosteel.qcsh.ui.view;

import java.util.List;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.baosteel.qcsh.model.Brand;
import com.baosteel.qcsh.ui.adapter.HomeBrandAdapter;
import com.baosteel.qcsh.ui.adapter.HomeBrandPagerAdapter;

/**
 * 品牌推荐
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-2
 */
public class BrandRecommentView {
	
	/**viewpager控件**/
	private ViewPager viewpager;
	
	/**viewpager适配器**/
	private HomeBrandPagerAdapter pageAdapter;
	
	/**上下文**/
	private Context context;
	
	public BrandRecommentView(Context context, ViewPager viewpager){
		this.viewpager = viewpager;
		this.context = context;
	}
	
	/**
	 * 刷新数据
	 * @param brandList
	 */
	public void refreshData(List<Brand> brandList, int pageCount){
		pageAdapter = new HomeBrandPagerAdapter(context, brandList, pageCount);
		pageAdapter.setOntemClickListener(backItemClickListener);
		viewpager.setAdapter(pageAdapter);
	}
	
	HomeBrandAdapter.BrandItemClickListener backItemClickListener;
	public void setOntemClickListener(HomeBrandAdapter.BrandItemClickListener backItemClickListener) {
		this.backItemClickListener = backItemClickListener;
	}
}
