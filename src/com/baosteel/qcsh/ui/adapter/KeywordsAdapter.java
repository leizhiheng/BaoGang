package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.R.integer;
import android.content.ClipData.Item;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
public class KeywordsAdapter extends BaseAdapter{

	private static final String TAG = "ProductCatogeryTitleAdapter";
	private Context mContext;
	private LayoutInflater mInflater;
	private List<ProductCategory> mCatogeries;
	/**
	 * 当Item被选中时，字体的颜色
	 */
	private int mColorItemSelected;
	
	private boolean showVerticalLine = true;
	
	private boolean showRightIcon = false;
	
	private int mRank;
	
	/**
	 * 
	 * @description 该回调接口用于把Item选中事件通知到外部
	 * @author blue
	 * @Time 2015-9-18
	 *
	 */
	public interface OnItemSelctedListener{
		void onItemSelected(int position);
	}
	private OnItemSelctedListener mListener;
	public void setOnItemSelctedListener(OnItemSelctedListener listener) {
		mListener = listener;
	}
	
	/**
	 * 
	 * @ Description Constructor of ProductCatogeryTitleAdapter.java
	 * @ Author blue
	 * @ Time 2015-8-31
	 */
	public KeywordsAdapter(Context context, List<ProductCategory> catogeries, int colorTitleSelected, boolean showVerticalLine) {
		this.mContext = context;
		this.mCatogeries = catogeries;
		this.mColorItemSelected = colorTitleSelected;
		this.mInflater = LayoutInflater.from(context);
		
		this.showVerticalLine = showVerticalLine;
		this.mCatogeries.get(0).setSelected(true);
	}
	
	/**
	 * 
	 * @ Description Constructor of ProductCatogeryTitleAdapter.java
	 * @ Author blue
	 * @ Time 2015-8-31
	 */
	public KeywordsAdapter(Context context, List<ProductCategory> catogeries, int colorTitleSelected, boolean showVerticalLine, boolean showRightIcon) {
		this.mContext = context;
		this.mCatogeries = catogeries;
		this.mColorItemSelected = colorTitleSelected;
		this.mInflater = LayoutInflater.from(context);
		
		this.showVerticalLine = showVerticalLine;
		this.showRightIcon = showRightIcon;
		this.mCatogeries.get(0).setSelected(true);
	}
	
	@Override
	public int getCount() {
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
			convertView = mInflater.inflate(R.layout.layout_product_filter_category_item, null);
			holder = new ViewHolder();
			holder.lineRight = convertView.findViewById(R.id.line_right);
			holder.lineTagLeft = convertView.findViewById(R.id.line_tag_left);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_catogery_title);
			holder.ivSeletedIcon = (ImageView) convertView.findViewById(R.id.iv_selected_icon);
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
			
			//是否显示左边的竖线
			int vis = showVerticalLine ? View.VISIBLE : View.INVISIBLE;
			holder.lineTagLeft.setVisibility(vis);
			
			/*
			 * 选中时显示右侧图标
			 */
			if (showRightIcon) {
				holder.ivSeletedIcon.setVisibility(View.VISIBLE);
			}
			
		} else {
			holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.black));
			holder.lineRight.setVisibility(View.VISIBLE);
			holder.lineTagLeft.setVisibility(View.INVISIBLE);
			
			if (showRightIcon) {
				holder.ivSeletedIcon.setVisibility(View.GONE);
			}
		}
		
		
		
		holder.tvTitle.setText(catogery.getName());
		return convertView;
	}
	
	class ViewHolder {
		View lineRight;
		View lineTagLeft;
		TextView tvTitle;
		ImageView ivSeletedIcon;
	}
	
	public void setRank(int rank) {
		mRank = rank;
	}
	
	public void setSeletedItemColor(int color) {
		mColorItemSelected = color;
	}
	
	public void setData(List<ProductCategory> categories) {
		mCatogeries.clear();
		mCatogeries.addAll(categories);
		notifyDataSetChanged();
	}
	
	/*
	 * 选中一个Item，并记录该Item的position
	 */
	private int selectItem = 0;
	public void selectItem(int position) {
		/*
		 * 这个判断不能加，如果要加的话则selectItem的初始值应为-1，并且每次重新加载数据之前都需要
		 * 将selectItem重新置为-1，否则会直接return，造成后面必须要执行的代码执行不了。
		 */
//		if (selectItem == position) {
//			return;
//		}
		
		this.mCatogeries.get(selectItem).setSelected(false);
		this.mCatogeries.get(position).setSelected(true);
		this.selectItem = position;
		
		if (mListener != null) {
			mListener.onItemSelected(selectItem);
		}
		
		Log.d("zhiheng", mRank + " 级分类，第 "+position +" 被选中");
		
		this.notifyDataSetChanged();
	}
	
	public int getSelectedItemIndex() {
		return selectItem;
	}
	
	public void setSelectedItemIndex(int selectedItem) {
		this.selectItem = selectedItem;
	}
}
