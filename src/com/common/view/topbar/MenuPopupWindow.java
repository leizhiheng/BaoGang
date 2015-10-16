package com.common.view.topbar;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.baosteel.qcsh.R;

public class MenuPopupWindow extends PopupWindow implements OnItemClickListener{

	private List<String> mItems;
	private MenuPopupWindow mWindow;

	public MenuPopupWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MenuPopupWindow(Context context, int width, List<String> items, OnMenuItemClickListener listener) {
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View contentView = inflater.inflate(R.layout.pop_menu, null);
		// 设置PopupWindow的View
		this.setContentView(contentView);
		// 设置PopupWindow弹出窗体的宽
		this.setWidth(width);
		// 设置PopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
//	    this.setAnimationStyle(R.style.AnimBottom);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		this.setBackgroundDrawable(dw);
       
		this.mItems = items;
		ListView listView = (ListView) contentView.findViewById(R.id.lv_list);
		mWindow = this;
		MyListAdapter adapter = new MyListAdapter( context, mItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
		this.listener=listener;
	}

	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		super.showAtLocation(parent, gravity, x, y);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
		if(null!=mWindow){
			mWindow.dismiss();
		}
		if(null!=listener){
			listener.onMenuItemSelect(index);
		}
	}
	
	public interface OnMenuItemClickListener{
		void onMenuItemSelect(int index);
	}
	
	private OnMenuItemClickListener listener;
	
}
