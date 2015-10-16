package com.baosteel.qcsh.ui.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.dialog.TopbarMenuPopwindow;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.ui.activity.MainActivity;
import com.baosteel.qcsh.ui.activity.common.SearchActivity;
import com.common.utils.DeviceUtils;

/**
 * 
 * @description 这个类封装了一个重用性较高的TitleBar
 * @author blue
 * @Time time2015-8-31
 *
 */
@SuppressLint("NewApi")
public class TitleBar extends FrameLayout implements OnClickListener{

	private Context mContext;
	/**title bar 布局*/
	private View mTitlebarView;
	private View mRootView;
	
	/**title bar 左侧返回布局*/
	private View mLayoutLeft;
	/**title bar 左侧返回图标*/
	private ImageView mBackIcon;
	/**title bar 左侧返回文本*/
	private TextView mBackText;
	
	/**title bar 中间标题布局*/
	private ViewGroup mLayoutMiddle;
	/**title bar 右侧菜单按钮布局*/
	private TextView mTitleText;
	
	/**右侧按钮布局*/
	private View mLayoutRight;
	/**右侧按钮图标*/
	private ImageView mRightIcon;
	/**右侧文字按钮*/
	private TextView mRightText;
	
	/** 搜索图标的index*/
	public static final int ICON_INDEX_SEARCH = 0;
	/** 个人中心图标的index */
	public static final int ICON_INDEX_USER_CENTER = 1;
	/** 菜单或popupMenu图标的index*/
	public static final int ICON_INDEX_MENU = 2;
	/**购物车删除图标的index*/
	public static final int ICON_INDEX_DEL = 3;

	/** 搜索按钮图标*/
	public static final int DEFAULT_ICON_SEARCH = R.drawable.main_bottom_tab_search_focus;
	/** 个人中心图标*/
	public static final int DEFAULT_ICON_USER_CENTER = R.drawable.icon_user_center;
	/** 菜单图标*/
	public static final int DEFAULT_ICON_MENU = R.drawable.icon_3point;
	/**购物车删除图标*/
	public static final int DEFAULT_ICON_DEL = R.drawable.delete;

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context) {
		mContext = context;
		mTitlebarView = inflate(context, R.layout.common_titlebar_view, this);
		findView();
	}
	
	private void findView() {
		try{
			mRootView = mTitlebarView.findViewById(R.id.root_view);
			mLayoutLeft = mTitlebarView.findViewById(R.id.layout_back);
			mLayoutMiddle = (ViewGroup) mTitlebarView.findViewById(R.id.layout_mid);
			mLayoutRight = mTitlebarView.findViewById(R.id.layout_right);
			mBackIcon = (ImageView) mTitlebarView.findViewById(R.id.iv_headview_goback);
			mBackText = (TextView) mTitlebarView.findViewById(R.id.tv_headview_goback_text);
			mTitleText = (TextView) findViewById(R.id.tv_headview_title);
			mRightIcon = (ImageView) mTitlebarView.findViewById(R.id.imgbtn_right);
			mRightText = (TextView) findViewById(R.id.right_text);
			mLayoutLeft.setOnClickListener(this);
			mBackIcon.setOnClickListener(this);
		}catch(Exception e){
			
		}
		
	}
	
	
	/**
	 * 
	 * @Description 跟换middle标题布局的子布局
	 * @Author blue
	 * @Time 上午9:01:03
	 */
	public void setMidView(View view) {
		mLayoutMiddle.removeAllViews();
		mLayoutMiddle.addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
	}
	
	/**
	 * 
	 * @Description 设置左侧返回图标的可见性
	 * @Author blue
	 * @Time 上午9:38:32
	 */
	public void setLeftIconVisibility(int visibility) {
		mBackIcon.setVisibility(visibility);
	}
	
	/**
	 * 设置右边图标
	 * @param drawable
	 */
	public void setRightViewDrawable(int drawable){
		mRightIcon.setVisibility(View.VISIBLE);
		mRightIcon.setImageResource(drawable);
		mRightIcon.setTag(drawable);
	}
	
	/**
	 * 隐藏右侧图标：View.INVISIBILITY 或者 View.GONE
	 * @Description TODO
	 * @Author blue
	 * @Time 2015-9-12
	 */
	public void hideRightIcon(int visible) {
		mRightIcon.setVisibility(visible);
	}
	
	/**
	 * 
	 * @Description 显示右侧图标，并根据传入的index设置不同的图片和点击事件
	 * @Author blue
	 * @Time 2015-9-12
	 */
	public void showRightIcon(final int index) {
		mRightIcon.setVisibility(View.VISIBLE);
		if (index == ICON_INDEX_SEARCH) {
			mRightIcon.setImageResource(DEFAULT_ICON_SEARCH);
		} else if (index == ICON_INDEX_USER_CENTER) {
			mRightIcon.setImageResource(DEFAULT_ICON_USER_CENTER);
		} else if (index == ICON_INDEX_MENU) {
			mRightIcon.setImageResource(DEFAULT_ICON_MENU);
		}
		
		/*
		 * 设置不同的监听事件
		 */
		mRightIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (index == ICON_INDEX_SEARCH) {
					getContext().startActivity(new Intent(getContext(), SearchActivity.class));
				} else if (index == ICON_INDEX_USER_CENTER) {
					getContext().startActivity(new Intent(getContext(), MainActivity.class));
				} else if (index == ICON_INDEX_MENU) {
					TopbarMenuPopwindow popwindow = new TopbarMenuPopwindow(getContext());
					popwindow.showAsDropDown(mTitlebarView, DeviceUtils.getWidthMaxPx((Activity) mContext) - popwindow.getWidth(), 0);
				}
			}
		});
	}
	
	/**
	 * 
	 * @Description 显示右侧图标，并根据传入的index设置不同的图片,为该Button设置传入的OnclickListener
	 * @Author blue
	 * @Time 2015-9-12
	 */
	public void showRightIcon(int index, OnClickListener listener) {
		mRightIcon.setVisibility(View.VISIBLE);
		if (index == ICON_INDEX_SEARCH) {
			mRightIcon.setImageResource(DEFAULT_ICON_SEARCH);
		} else if (index == ICON_INDEX_USER_CENTER) {
			mRightIcon.setImageResource(DEFAULT_ICON_USER_CENTER);
		} else if (index == ICON_INDEX_MENU) {
			mRightIcon.setImageResource(DEFAULT_ICON_MENU);
		} else if(index==ICON_INDEX_DEL){
			mRightIcon.setImageResource(DEFAULT_ICON_DEL);
		}
		
		mRightIcon.setOnClickListener(listener);
	}
	
	/**
	 * 
	 * @Description 显示右侧图标，显示传入的图片，并且为该Button设置传入的点击事件
	 * @Author blue
	 * @Time 2015-9-12
	 */
	public void showRightIconWithDrawable(int drawableId, OnClickListener listener) {
		mRightIcon.setVisibility(View.VISIBLE);
		mRightIcon.setImageResource(drawableId);
		mRightIcon.setOnClickListener(listener);
	}
	
	/**
	 * 顶部右边按钮点击事件
	 * @param onclick
	 */
	public void setRightClick(View.OnClickListener onclick){
		mRightIcon.setOnClickListener(onclick);
	}
	
	public void showRightText(String text, OnClickListener listener) {
		mRightText.setVisibility(View.VISIBLE);
		mRightText.setText(text);
		mRightText.setOnClickListener(listener);
	}
	/**
	 * 
	 * @Description 设置左侧返回文本
	 * @Author blue
	 * @Time 上午9:39:23
	 */
	public void setLeftText(String text) {
		mBackText.setText(text);
	}
	
	/**
	 * 
	 * @Description 设置标题
	 * @Author blue
	 * @Time 2015-9-1
	 */
	public void setTitle(String title) {
		mTitleText.setText(title);
	}
	
	public void setTitle(int stringId) {
		mTitleText.setText(stringId);
	}
	
	/**
	 * 
	 * @Description 设置右侧图标的可见性
	 * @param viewIndex 0:表示popmeneu, 1：表示个人中心
	 * @param visibility 可见性
	 * @Author blue
	 * @Time 2015-9-1
	 */
	public void setRightIconVisibility(int viewIndex, int visibility) {
		if (viewIndex == 0) {
			mRightIcon.setVisibility(visibility);
		} 
	}
	
	/**
	 * 
	 * @Description 设置背景颜色
	 * @Author blue
	 * @Time 2015-9-6
	 */
	public void setBackgroud(int colorId) {
		mRootView.setBackgroundColor(getResources().getColor(colorId));
	}

	public void setBackgroudValue(int colorId) {
		mRootView.setBackgroundColor(colorId);
	}
	
	public void setBackgroudColor(int color) {
		mRootView.setBackgroundColor(color);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_headview_goback:
			((Activity) mContext).finish();
			break;

		default:
			break;
		}
	}

}
