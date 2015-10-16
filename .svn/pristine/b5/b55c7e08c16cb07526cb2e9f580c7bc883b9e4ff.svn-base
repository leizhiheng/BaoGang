package com.baosteel.qcsh.ui.activity.home.healthy;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.IndicatorPagerAdapter;
import com.baosteel.qcsh.ui.fragment.home.healthy.HospitalExpertIntraoduceFragment;
import com.baosteel.qcsh.ui.fragment.home.healthy.HospitalIntroduceFragment;
import com.baosteel.qcsh.ui.fragment.home.healthy.PhysicalExamGuardFragment;
import com.baosteel.qcsh.ui.view.TabViewPagerIndicator;
import com.common.base.BaseActivity;
import com.common.base.BaseFragment;

public class HospitalIntroduceActivity extends BaseActivity{

	private static String[] mTitles = new String[]{"医院简介","专家介绍","体检指导"};
	
	private TabViewPagerIndicator mIndicator;
	private ViewPager mPager;
	private List<BaseFragment> mFragments;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hospital_introduce);

		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		
		initFragments();
	}
	
	private void initView() {
		
		setTitle("医院介绍");
		mTitleBar.setBackgroud(R.color.theme_color_healthy);
		
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(new IndicatorPagerAdapter(getSupportFragmentManager(), mFragments));
		
		/**
		 * 设置Indicator相关属性
		 */
		mIndicator = (TabViewPagerIndicator) findViewById(R.id.indicator);
		//设置Tab标题，并且根据标题个数创建一样个数的Tab
		mIndicator.setTabTitles(mTitles);
		//设置ViewPager
		mIndicator.setViewPager(mPager);
		mIndicator.showRightDivideLine(0);
		mIndicator.showRightDivideLine(1);
		//设置选中颜色
		mIndicator.setColor(getResources().getColor(R.color.theme_color_healthy));
		//设置选中项
		mIndicator.setCurrentItem(0);
	}
	
	private void loadData() {
		
	}
	
	private void initFragments() {
		mFragments = new ArrayList<BaseFragment>();
		mFragments.add(new HospitalIntroduceFragment());
		mFragments.add(new HospitalExpertIntraoduceFragment());
		mFragments.add(new PhysicalExamGuardFragment());
	}
}
