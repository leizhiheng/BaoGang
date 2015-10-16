package com.baosteel.qcsh.ui.activity.common;

import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.IndicatorPagerAdapter;
import com.baosteel.qcsh.ui.view.TabViewPagerIndicator;
import com.common.base.BaseActivity;
import com.common.base.BaseFragment;

public class TabPagerActivity extends BaseActivity {
	
	protected TabViewPagerIndicator mIndicator;
	
	protected String[] mTitles = new String[] { "全部", "申请中",
			"已接受", "已安排", "已完成" };
	
	protected List<BaseFragment> mFragments;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_pager);
		initView();

	}
	
	private void initView() {
		setTitle("我的预约服务");
		
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new IndicatorPagerAdapter(getSupportFragmentManager(), mFragments));
		
		/**
		 * 设置Indicator相关属性
		 */
		mIndicator = (TabViewPagerIndicator) findViewById(R.id.indicator);
		//设置Tab标题，并且根据标题个数创建一样个数的Tab
		mIndicator.setTabTitles(mTitles);
		//设置ViewPager
		mIndicator.setViewPager(pager);
		//设置选中颜色
		mIndicator.setColor(getResources().getColor(R.color.title_bar_bg_voilet));
		//设置选中项
		mIndicator.setCurrentItem(0);
	}

}