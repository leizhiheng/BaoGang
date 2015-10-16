package com.baosteel.qcsh.dialog;

import java.util.ArrayList;
import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.TopbarMenuItem;
import com.baosteel.qcsh.ui.activity.StartActivity;
import com.baosteel.qcsh.ui.activity.my.CollectProductActivity;
import com.baosteel.qcsh.ui.activity.my.order.AllOrderActivity;
import com.baosteel.qcsh.ui.activity.my.order.OrderListActivity;
import com.baosteel.qcsh.ui.activity.my.setting.MyMessageActivity;
import com.baosteel.qcsh.ui.adapter.TopbarMenuAdapter;
import com.common.utils.DensityUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class TopbarMenuPopwindow extends PopupWindow{

	private static final int MENU_TAG_COLLECTION = 0x1;
	private static final int MENU_TAG_ORDER = 0x2;
	private static final int MENU_TAG_MSG = 0x3;
	
	private Context mContext;
	private View mPopView;
	private ListView mMenuList;
	private List<TopbarMenuItem> mMenuItems;
	private TopbarMenuAdapter mAdapter;
	
	public TopbarMenuPopwindow(Context context){
		init(context);
		
		setWindow();
		
		initMenuDatas();
	}
	
	private void init(Context context){
		mContext = context;
		mMenuItems = new ArrayList<TopbarMenuItem>();
		mAdapter = new TopbarMenuAdapter(mContext, mMenuItems);
		mPopView = LayoutInflater.from(context).inflate(R.layout.popwindow_topbar_menu, null);
		mMenuList = (ListView) mPopView.findViewById(R.id.listview);
		mMenuList.setAdapter(mAdapter);
		
		mMenuList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				dismiss();
				
				if (!BaoGangData.getInstance().isLogin()) {
					Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
					return;
				}
				
				switch (mMenuItems.get(position).getTag()) {
				case MENU_TAG_COLLECTION:
					mContext.startActivity(new Intent(mContext, CollectProductActivity.class));
					break;
					
				case MENU_TAG_ORDER:
					mContext.startActivity(new Intent(mContext, AllOrderActivity.class));
					break;
					
				case MENU_TAG_MSG:
					mContext.startActivity(new Intent(mContext, MyMessageActivity.class));
					break;

				default:
					break;
				}
			}
		});
	}
	
	private void initMenuDatas() {
		mMenuItems.add(new TopbarMenuItem(MENU_TAG_COLLECTION,"我的收藏", R.drawable.icon_my_selection));
		mMenuItems.add(new TopbarMenuItem(MENU_TAG_ORDER,"我的订单", R.drawable.icon_my_order));
		mMenuItems.add(new TopbarMenuItem(MENU_TAG_MSG,"消息中心", R.drawable.icon_messgae_center));
		mAdapter.notifyDataSetChanged();
	}
	
	private void setWindow() {
		// 设置SelectPicPopupWindow的View
		setContentView(mPopView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		setWidth(DensityUtils.dp2px(mContext, 150));
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
