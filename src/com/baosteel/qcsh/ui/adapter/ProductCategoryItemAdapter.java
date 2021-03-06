package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

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
import com.baosteel.qcsh.model.ProductCategoryItem;
import com.baosteel.qcsh.utils.ImageManager;

/**
 * 
 * @description 这个Adapter对应的是ProductCatogeryFragment中GridView,也就是每个分类中的多种商品
 * @author blue
 * @Time time2015-8-31
 *
 */
public class ProductCategoryItemAdapter extends BaseAdapter{

	private static final String TAG = "ProductCategoryItemAdapter";
	private Context mContext;
	private LayoutInflater mInflater;
	private List<ProductCategory> mCategoryItems;
	
	public ProductCategoryItemAdapter(){}
	
	public ProductCategoryItemAdapter(Context context, List<ProductCategory> items) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mCategoryItems = items;
		
	}
	
	/**
	 * 
	 * @Description 在外部动态改变数据源并刷新
	 * @Author blue
	 * @Time 下午4:46:53
	 */
	public void setData(List<ProductCategory> items) {
		
		if (items == null) {
			return;
		}
		
		if (mCategoryItems == null) {
			mCategoryItems = new ArrayList<ProductCategory>();
		}
		mCategoryItems.clear();
		mCategoryItems.addAll(items);
		notifyDataSetChanged();
	}
	
	
	public List<ProductCategory> getData() {
		return mCategoryItems;
	}
	
	@Override
	public int getCount() {
		return mCategoryItems == null ? 0 : mCategoryItems.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ProductCategory item = mCategoryItems.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.layout_product_category_item, null);
			holder.itemIcon = (ImageView) convertView.findViewById(R.id.iv_item_icon);
			holder.itemTitle = (TextView) convertView.findViewById(R.id.tv_item_title);
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();
		if (item.getImg_url() == null) {
			holder.itemIcon.setImageResource(R.drawable.bg_maybelike);
//			holder.itemIcon.setImageResource(item.getmIconResId());
		} else {
			ImageManager.Load(item.getImg_url(), holder.itemIcon);
		}
		holder.itemTitle.setText(item.getName());
		return convertView;
	}
	
	class ViewHolder {
		ImageView itemIcon;
		TextView itemTitle;
	}

}
