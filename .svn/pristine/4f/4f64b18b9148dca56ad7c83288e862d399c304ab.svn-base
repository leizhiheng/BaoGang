package com.baosteel.qcsh.ui.view;

import java.util.Set;
import java.util.TreeSet;

import android.R.integer;
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

import com.baosteel.qcsh.R;


public class TabViewPagerIndicator extends LinearLayout implements OnClickListener{

	private LinearLayout mRootView;
	private LayoutInflater mInflater;
	private String [] mTabTitles;
	private ViewPager mPager;
	
	private Set<Integer> mShowIconIconIndex;
	
	private int color;
	
	private int mCurItemIndex = -1;
	public TabViewPagerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	/**
	 * 
	 * @description 这是一个回调接口，外部注册该回调用于监听Tab的点击事件
	 * @author blue
	 * @Time 2015-9-18
	 *
	 */
	public interface OnTabSelectedListerner {
		/**
		 * 
		 * @Param tag :选择的Tab下标
		 * @Author blue
		 * @Time 2015-9-18
		 */
		void onTabSelected(int tag);
	}
	private OnTabSelectedListerner mListerner;
	public void setOnTabSelectedListerner(OnTabSelectedListerner listerner) {
		this.mListerner = listerner;
	}
	
	private void init(Context context) {
		mShowIconIconIndex = new TreeSet<Integer>();
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
		
		if (mRootView.getChildCount() == 0) {
			return;
		}
		
		this.color = color;
		for (int i = 0; i < mTabTitles.length; i++) {
			View view = mRootView.getChildAt(i);
			View line = view.findViewById(R.id.line);
			line.setBackgroundColor(color);
		}
	}
	
	public void showRightDivideLine(int index) {
		if (index < 0 || index >= mRootView.getChildCount()) {
			return;
		}
		View line = mRootView.getChildAt(index).findViewById(R.id.line_divide_right);
		line.setVisibility(View.VISIBLE);
	}
	
	/**
	 * 
	 * @Description 设置tab icon可见,并显示默认图片
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
	public void setTabIcon(int index, int iconId, int iconIdSel) {
		if (index < 0 || index >= mRootView.getChildCount()) {
			return;
		}
		//将该index放入Set中，表示该Tab的图标会显示，并且不同状态会显示不同图标
		mShowIconIconIndex.add(index);
		View tabView = mRootView.getChildAt(index);
		ImageView icon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
		/*
		 * 设置icon的图标资源
		 */
		icon.setVisibility(View.VISIBLE);
		icon.setTag(R.id.tab_icon_nor, iconId);
		icon.setTag(R.id.tab_icon_sel, iconIdSel);
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
		//设置底部线条
		View line = view.findViewById(R.id.line);
		line.setVisibility(View.INVISIBLE);
		//设置文字颜色
		TextView title = (TextView) view.findViewById(R.id.tv_tab_title);
		title.setTextColor(getResources().getColor(R.color.black));
		//判断该Tab是否显示图标，并且会有图标切换
		if (mShowIconIconIndex.contains(mCurItemIndex)) {
			ImageView icon = (ImageView) view.findViewById(R.id.iv_tab_icon);
			if (icon.getTag(R.id.tab_icon_nor) != null) {
				icon.setImageResource(Integer.parseInt(icon.getTag(R.id.tab_icon_nor).toString()));
			}
		}
		
		view = mRootView.getChildAt(index);
		//设置底部线条
		line = view.findViewById(R.id.line);
		line.setVisibility(View.VISIBLE);
		//设置文字颜色
		title = (TextView) view.findViewById(R.id.tv_tab_title);
		title.setTextColor(color);
		//判断该Tab是否显示图标，并且会有图标切换
		if (mShowIconIconIndex.contains(index)) {
			ImageView icon = (ImageView) view.findViewById(R.id.iv_tab_icon);
			if (icon.getTag(R.id.tab_icon_nor) != null) {
				
				icon.setImageResource(Integer.parseInt(icon.getTag(R.id.tab_icon_sel).toString()));
			}
		}
		
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
		int tag = Integer.parseInt(v.getTag().toString());

		/*
		 * 设置选中tag
		 */
		setCurrentItem(tag);
		/*
		 * 执行外部注册的监听事件
		 */
		if (mListerner != null) {
			mListerner.onTabSelected(tag);
		}
	}

}
