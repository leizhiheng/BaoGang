package com.baosteel.qcsh.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.baosteel.qcsh.R;

public class FilterTabBar extends LinearLayout implements OnClickListener{

	private LinearLayout mRootView;
	private LayoutInflater mInflater;
	private String [] mTabTitles;
	private ViewPager mPager;
	
	private int color;
	
	private int mCurItemIndex = -1;
	public FilterTabBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context) {
		mInflater = LayoutInflater.from(context);
		mRootView = (LinearLayout) mInflater.inflate(R.layout.layout_radio_group_bar, null);
		mRootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		this.addView(mRootView);
	}
	
	/**
	 * 
	 * @Description 设置Tab的标题，并根据标题的个数添加TabView
	 * @Author blue
	 * @Time 2015-9-10
	 */
	public void setTabTitles(String[] titles) {
		mTabTitles = titles;
		addTabView();
	}
	
	/**
	 * 
	 * @Description 设置选中文字颜色
	 * @Author blue
	 * @Time 2015-9-10
	 */
	public void setColor(int color) {
		this.color = color;
		for (int i = 0; i < mTabTitles.length; i++) {
			View view = mRootView.getChildAt(i);
			View line = view.findViewById(R.id.line);
			line.setBackgroundColor(color);
		}
	}
	
	/**
	 * 
	 * @Description 设置tab icon可见
	 * @Author blue
	 * @Time 2015-9-15
	 */
	public void setTabIcon(int index) {
		if (index < 0 || index >= mRootView.getChildCount()) {
			return;
		}
		ImageView icon = (ImageView) mRootView.getChildAt(index).findViewById(R.id.iv_tab_icon);
		icon.setVisibility(View.VISIBLE);
	}
	
	/**
	 * 
	 * @Description 设置Tab可见，并改变其图标
	 * @Author blue
	 * @Time 2015-9-15
	 */
	public void setTabIcon(int index, int iconId) {
		if (index < 0 || index >= mRootView.getChildCount()) {
			return;
		}
		ImageView icon = (ImageView) mRootView.getChildAt(index).findViewById(R.id.iv_tab_icon);
		icon.setVisibility(View.VISIBLE);
		icon.setImageResource(iconId);
		icon.setImageResource(iconId);
	}
	
	public void setViewPager(ViewPager pager) {
		this.mPager = pager;
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				setCurrentItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}
	
	public void setCurrentItem(int index) {
		if (mCurItemIndex == index) {
			return;
		}
		if (mCurItemIndex == -1) {
			mCurItemIndex = index;
		}
		
		setItemSelected(index);		
	}
	
	/**
	 * 
	 * @Description 选中一个Tab
	 * @Author blue
	 * @Time 2015-9-10
	 */
	private void setItemSelected(int index) {
		View view = mRootView.getChildAt(mCurItemIndex);
		View line = view.findViewById(R.id.line);
		line.setVisibility(View.INVISIBLE);
		TextView title = (TextView) view.findViewById(R.id.tv_tab_title);
		title.setTextColor(getResources().getColor(R.color.black));
		
		
		view = mRootView.getChildAt(index);
		line = view.findViewById(R.id.line);
		line.setVisibility(View.VISIBLE);
		title = (TextView) view.findViewById(R.id.tv_tab_title);
		title.setTextColor(color);
		
		mCurItemIndex = index;
		
		/*
		 * 切换ViewPager页面
		 */
		if (mPager !=null) {
			mPager.setCurrentItem(index);
		}
	}
	
	/**
	 * 
	 * @Description 根据Title的个数添加相应个数的Tab View
	 * @Author blue
	 * @Time 2015-9-10
	 */
	private void addTabView() {
		for (int i = 0; i < mTabTitles.length; i++) {
			View view = mInflater.inflate(R.layout.view_tab_indicator, null);
			view.setTag(i);
			mRootView.addView(view);
			
			LayoutParams params = (LayoutParams) view.getLayoutParams();
			params.weight = 1;
			view.setLayoutParams(params);
			TextView title = (TextView) view.findViewById(R.id.tv_tab_title);
			title.setText(mTabTitles[i]);
			
			view.setOnClickListener(this);
			
		}
	}

	@Override
	public void onClick(View v) {
		int tag = Integer.parseInt(v.getTag().toString().trim());

		setCurrentItem(tag);
	}

}
