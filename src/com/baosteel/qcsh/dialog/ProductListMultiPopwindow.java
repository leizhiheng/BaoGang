package com.baosteel.qcsh.dialog;

import android.R.integer;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;

import com.baosteel.qcsh.R;

/**
 * 
 * @description 商品列表页面中，点击综合按钮弹出该popwindow
 * @author blue
 * @Time 2015-9-23
 *
 */
public class ProductListMultiPopwindow extends PopupWindow{

	private Context mContext;
	private View mPopView;
	private ListView mMenuList;
	private String [] mMultiItems = new String[]{"综合", "新品"};
	private BaseAdapter mAdapter;
	
	public interface OnItemSelectedListener{
		void onItemSelected(int position, String title);
	}
	private OnItemSelectedListener mListener;
	
	public void setOnItemSelectedListener(OnItemSelectedListener listener) {
		this.mListener = listener;
	}
	
	public ProductListMultiPopwindow(Context context){
		init(context);
		
		setWindow();
		
	}
	
	private void init(Context context){
		mContext = context;
		mAdapter = new ArrayAdapter<String>(mContext, R.layout.simple_list_item_1, mMultiItems);
		mPopView = LayoutInflater.from(context).inflate(R.layout.popwindow_topbar_menu, null);
		mMenuList = (ListView) mPopView.findViewById(R.id.listview);
		mMenuList.setAdapter(mAdapter);
		
		mMenuList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mListener != null) {
					mListener.onItemSelected(position, mMultiItems[position]);
				}
			}
		});
	}

	private void setWindow() {
		// 设置SelectPicPopupWindow的View
		setContentView(mPopView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		setHeight(LayoutParams.WRAP_CONTENT);
		// 设置Dialog之外区域的透明度
		//backgroundAlpha(0.7f);
		// 设置外部点击隐藏popwindow
		setOutsideTouchable(true);
		// 设置SelectPicPopupWindow弹出窗体可点击
		setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
//		setAnimationStyle(R.style.TopMenuAnimation);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
//		 设置SelectPicPopupWindow弹出窗体的背景
		setBackgroundDrawable(dw);
		setOutsideTouchable(true);
		update();
	}
}
