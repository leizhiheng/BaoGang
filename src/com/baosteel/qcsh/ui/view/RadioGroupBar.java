package com.baosteel.qcsh.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.baosteel.qcsh.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @description 这个自定义View封装了一个类似于底部选项卡的布局，每个选项由上下两部分组成，下部分是文字 上面部分可以是图片也可以是文字
 * @author blue
 * @Time 2015-9-8
 * 
 */
public class RadioGroupBar extends LinearLayout implements OnClickListener {

	
	public interface OnTabSelectedListener {
		void onTabSeleted(int tabIndex);
	}
	
	public OnTabSelectedListener mOnTabSelectedListener;
	
	private LinearLayout mRootView;
	/**
	 * 选项个数
	 */
	private int mTabNum;
	
	/**
	 * 选项布局
	 */
	private List<View> mTabViews;
	
	private LayoutInflater mInflater;
	
	/**
	 * Tab 选中时的背景颜色
	 */
	private int mColorTabSel;
	/**
	 * Tab 未选中时的背景颜色
	 */
	private int mColorTabNor;
	
	private int mIndexSelected = -1;
	
	public RadioGroupBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init(context);
	}
	
	private void init(Context context) {
		mTabViews = new ArrayList<View>();
		mInflater = LayoutInflater.from(context);
		mRootView = (LinearLayout) mInflater.inflate(R.layout.layout_radio_group_bar, null);
		mRootView.setBackgroundColor(getResources().getColor(R.color.orange));
		mRootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		this.addView(mRootView);
	}
	
	/**
	 * 
	 * @Description 设置选项卡数量
	 * @Author blue
	 * @Time 2015-9-8
	 */
	public void setTabNum(int num) {
		for (int i = 0; i < num; i++) {
			View view = mInflater.inflate(R.layout.view_radio_group_item, null);
			mTabViews.add(view);
			mRootView.addView(view);
			LayoutParams params = (LayoutParams) view.getLayoutParams();
			params.weight = 1;
			view.setLayoutParams(params);
			
			view.setTag(i);
		}
	}
	
	/**
	 * 
	 * @Description 设置Tab的背景颜色
	 * @Author blue
	 * @Time 2015-9-8
	 */
	public void setTabBgColor(int colorSel, int colorNor) {
		this.mColorTabSel = colorSel;
		this.mColorTabNor = colorNor;
		
		for (int i = 0; i < mRootView.getChildCount(); i++) {
			mRootView.getChildAt(i).setBackgroundColor(mColorTabNor);
		}
	}
	
	/**
	 * 
	 * @Description 设置Tab Icon
	 * @Author blue
	 * @Time 2015-9-8
	 */
	public void setTabIcon(int index, int iconNorId, int iconSelId) {
		View view = mRootView.getChildAt(index);
		view.setTag(R.id.tab_icon_nor, iconNorId);
		view.setTag(R.id.tab_icon_sel, iconSelId);
		
		ImageView icon = (ImageView) view.findViewById(R.id.iv_tab_icon);
		icon.setBackgroundResource(iconNorId);
	}
	
	/**
	 * 
	 * @Description 设置Tab title
	 * @Author blue
	 * @Time 2015-9-8
	 */
	public void setTabTitle(int index, String title) {
		ImageView icon = (ImageView) mRootView.getChildAt(index).findViewById(R.id.iv_tab_icon);
		icon.setVisibility(View.INVISIBLE);
		
		TextView textView = (TextView) mRootView.getChildAt(index).findViewById(R.id.tv_tab_title);
		textView.setVisibility(View.VISIBLE);
		textView.setText(title);
	}
	
	/**
	 * 
	 * @Description 设置Tab title
	 * @Author blue
	 * @Time 2015-9-8
	 */
	public void setTabContent(int index, String content) {
		TextView textView = (TextView) mRootView.getChildAt(index).findViewById(R.id.tv_tab_content);
		textView.setText(content);
	}
	
	/**
	 * 
	 * @Description 设置TAb 的选中事件
	 * @Author blue
	 * @Time 2015-9-8
	 */
	public void setOnTabSelectedListener(OnTabSelectedListener listener) {
		this.mOnTabSelectedListener = listener;
		
		for (int i = 0; i < mRootView.getChildCount(); i++) {
			mRootView.getChildAt(i).setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		
		int index = Integer.parseInt(v.getTag().toString().trim());
		
		setTabSelected(index);
		
		mIndexSelected = index;
		
		if (mOnTabSelectedListener != null) {
			mOnTabSelectedListener.onTabSeleted(index);
		}
	}
	
	/**
	 * 
	 * @Description 设置选中的Tab
	 * @Author blue
	 * @Time 2015-9-8
	 */
	public void setTabSelected(int index) {
		if (index == mIndexSelected) {
			return;
		}
		
		if (mIndexSelected == -1) {
			mIndexSelected = index;
		}
		
		View view = mRootView.getChildAt(mIndexSelected);
		view.setBackgroundColor(mColorTabNor);
		
		view = mRootView.getChildAt(index);
		view.setBackgroundColor(mColorTabSel);
	}

}