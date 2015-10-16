package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ProductCategory;

/**
 * 
 * @description 这个Adapter对应的是ProductCatogeryFragment中ListView
 * @author blue
 * @Time time2015-8-31
 *
 */
public class ProductCatogeryTitleAdapter extends BaseAdapter{

	private static final String TAG = "ProductCatogeryTitleAdapter";
	private Context mContext;
	private LayoutInflater mInflater;
	private List<ProductCategory> mCatogeries;
	/**
	 * 当Item被选中时，字体的颜色
	 */
	private int mColorItemSelected;
	/**
	 * 
	 * @ Description Constructor of ProductCatogeryTitleAdapter.java
	 * @ Author blue
	 * @ Time 2015-8-31
	 */
	public ProductCatogeryTitleAdapter(Context context, List<ProductCategory> catogeries, int colorTitleSelected) {
		this.mContext = context;
		this.mCatogeries = catogeries;
		this.mColorItemSelected = colorTitleSelected;
		this.mInflater = LayoutInflater.from(context);
	}
	
	public void setData(List<ProductCategory> datas) {
		mCatogeries = datas;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		Log.d(TAG, "getCount = "+(mCatogeries == null ? 0 : mCatogeries.size()));
		return mCatogeries == null ? 0 : mCatogeries.size();
	}
	
	@Override
	public Object getItem(int position) {
		return mCatogeries.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ProductCategory catogery = mCatogeries.get(position);
		Log.d(TAG, "== > getView, category title :"+catogery.getName());
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.layout_product_catogery_title_item, null);
			holder = new ViewHolder();
			holder.lineRight = convertView.findViewById(R.id.line_right);
			holder.lineTagLeft = convertView.findViewById(R.id.line_tag_left);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_catogery_title);
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();
		
		/*
		 * 根据Item是否选中，设置Item的布局
		 */
		if (catogery.isSelected()) {
			holder.tvTitle.setTextColor(mColorItemSelected);
			holder.lineTagLeft.setBackgroundColor(mColorItemSelected);
			holder.lineRight.setVisibility(View.INVISIBLE);
			holder.lineTagLeft.setVisibility(View.VISIBLE);
		} else {
			holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.black));
			holder.lineRight.setVisibility(View.VISIBLE);
			holder.lineTagLeft.setVisibility(View.INVISIBLE);
		}
		holder.tvTitle.setText(catogery.getName());
		return convertView;
	}
	
	class ViewHolder {
		View lineRight;
		View lineTagLeft;
		TextView tvTitle;
	}
	
	public void setSeletedItemColor(int color) {
		mColorItemSelected = color;
	}
}
