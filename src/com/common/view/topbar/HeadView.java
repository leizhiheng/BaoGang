package com.common.view.topbar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.dialog.TopbarMenuPopwindow;
import com.baosteel.qcsh.ui.activity.MainActivity;
import com.common.base.BaseActivity;
import com.common.utils.DeviceUtils;
import com.common.view.topbar.MenuPopupWindow.OnMenuItemClickListener;

/**
 * tabBar
 * 
 * @author kuangyong
 * 
 */
public class HeadView extends RelativeLayout {

	private RelativeLayout root_view;//主体布局

	/**返回**/
	private View layoutBack;
	
	/**标题**/
	private TextView titile;
	
//	/**返回文本**/
//	private TextView gobackText;
	
	/**右边图标**/
	private ImageButton imgBtn_right;

	/**返回图标**/
//	private ImageView goback;
	
	/**点击右边弹出的popwindow**/
	private MenuPopupWindow popupWindow;
	
	/**右边布局**/
	private FrameLayout mFrameRight;
	
	/**右边文本**/
	private TextView mRightText;
	
	/****/
	private List<String> items = new ArrayList<String>();
	
	/****/
	private Context mContext;

	private View line;//分割线

	private View mainView;
	
	/****/
	private OnMenuItemClickListener listener;

	public HeadView(Context context) {
		super(context);
		this.mContext=context;
	}

	public HeadView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext=context;
	}

	public HeadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext=context;
		initview(context);
	}

	private void initview(final Context context) {
		mainView=LayoutInflater.from(context).inflate(R.layout.common_headview, this, true);
		mContext = context;
		findView();
		layoutBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 监听返回键
				if (context instanceof Activity) {
					((Activity) context).finish();
				}
			}
		});

		initItemList();
	}
	
	private void findView() {
		
		layoutBack = findViewById(R.id.layout_back);
		line = findViewById(R.id.line);
		root_view = (RelativeLayout)findViewById(R.id.root_view);
		titile = (TextView) findViewById(R.id.tv_headview_title);
//		goback = (ImageView) findViewById(R.id.iv_headview_goback);
//		gobackText = (TextView) findViewById(R.id.tv_headview_goback_text);
		imgBtn_right = (ImageButton) findViewById(R.id.imgbtn_right);
		mFrameRight = (FrameLayout) findViewById(R.id.rightBtn_text);
		mRightText = (TextView) findViewById(R.id.right_text);
	}

	/**
	 * 设置标题
	 * 
	 * @param text
	 */
	public void setTitle(CharSequence text) {
		titile.setText(text);
	}

	public void setHeadViewBackGroundColor(int color){
		root_view.setBackgroundColor(color);
	}

	/**
	 * 显示返回键 设置监听
	 * 
	 * @param mClickListener
	 */
	public void setBackOnclickListener(OnClickListener mClickListener) {
//		goback.setVisibility(View.VISIBLE);
//		gobackText.setVisibility(View.VISIBLE);
		layoutBack.setOnClickListener(mClickListener);
	}

	/**
	 * 设置返回按钮是否显示
	 * 
	 * @param visible
	 */
	public void showGoback(int visible) {
		layoutBack.setVisibility(visible);
	}

	/**
	 * 设置右侧图片按钮 显示/隐藏
	 * 
	 * @param visible
	 */
	public void showRightImageBtn(int visible, OnClickListener listener) {
		imgBtn_right.setVisibility(visible);
		imgBtn_right.setOnClickListener(listener);
	}



	/**
	 * 设置右侧图片菜单按钮 显示/隐藏
	 * 
	 * @param visible
	 */
	public void showRightImageMenuBtn(int visible) {
		imgBtn_right.setVisibility(visible);
		initItemList();
		imgBtn_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				TopbarMenuPopwindow popwindow = new TopbarMenuPopwindow(getContext());
				popwindow.showAsDropDown(mainView, DeviceUtils.getWidthMaxPx((Activity) mContext) - popwindow.getWidth(), 0);
			}
		});
	}

	/**
	 * 设置右侧文字按钮的背景
	 * 
	 * @param src
	 */
	public void setRightTextBtnBackGround(int src) {
		mRightText.setBackgroundResource(src);
	}

	/**
	 * 设置右侧文字按钮的文字颜色
	 * 
	 * @param color
	 */
	public void setRightTextBtnTextColor(int color) {
		mRightText.setTextColor(color);
	}

	/**
	 * 显示/隐藏右侧文字按钮
	 * 
	 * @param visible
	 * @param str
	 * @param mClickListener
	 */
	public void showRightTextBtn(int visible, String str, OnClickListener mClickListener) {
		mRightText.setText(str);
		mFrameRight.setVisibility(visible);
		mFrameRight.setOnClickListener(mClickListener);
	}

	/**
	 * 设置右侧图片按钮背景图片
	 * 
	 * @param src
	 */
	public void setRightImageBtnBackGround(int src) {
		imgBtn_right.setImageResource(src);
	}

	public void initItemList() {
		 items=new ArrayList<String>();
		 items.add("首页");
		 items.add("搜索");
		 items.add("购物车");
		 items.add("个人中心");
	}

	/**
	 * 显示/隐藏分割线
	 * @param visible
	 */
	public void setSeparateLineVisible(int visible){
		line.setVisibility(visible);
	}
}
