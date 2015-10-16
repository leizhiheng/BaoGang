package com.baosteel.qcsh.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.fragment.product.filter.FilterProductFragment;

public class ProductListFilterPopwindow extends PopupWindow implements OnClickListener{

	private Context mContext;
	private View mPopView;
	private View mBlankView;
	
	private FragmentManager fragmentManager;
	private FilterProductFragment filterProductFragment;
	
	public interface OnItemSelectedListener{
		void onItemSelected(int position, String title);
	}
	private OnItemSelectedListener mListener;
	
	public void setOnItemSelectedListener(OnItemSelectedListener listener) {
		this.mListener = listener;
	}
	
	public ProductListFilterPopwindow(Context context, FragmentManager fm){
		init(context, fm);
		
		setWindow();
		
		loadData();
	}
	
	private void init(Context context, FragmentManager fm){
		mContext = context;
		fragmentManager = fm;
		
		mPopView = LayoutInflater.from(context).inflate(R.layout.popwindow_product_list_filter, null);
		mBlankView = mPopView.findViewById(R.id.layout_blank);
		FrameLayout frameLayout = (FrameLayout) mPopView.findViewById(R.id.container_filter);
		mBlankView.setOnClickListener(this);
		
		filterProductFragment = new FilterProductFragment();
		
	}
	
	private void loadData() {
		fragmentManager.beginTransaction().replace(R.id.container_filter, filterProductFragment).commit();
	}

	private void setWindow() {
		// 设置SelectPicPopupWindow的View
		setContentView(mPopView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		setHeight(LayoutParams.MATCH_PARENT);
		// 设置Dialog之外区域的透明度
		//backgroundAlpha(0.7f);
		// 设置外部点击隐藏popwindow
		setOutsideTouchable(true);
		// 设置SelectPicPopupWindow弹出窗体可点击
		setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
//		setAnimationStyle(R.style.PopAnimation);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
//		 设置SelectPicPopupWindow弹出窗体的背景
		setBackgroundDrawable(dw);
		setOutsideTouchable(true);
		update();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_blank:
			dismiss();
			break;

		default:
			break;
		}
	}
}
