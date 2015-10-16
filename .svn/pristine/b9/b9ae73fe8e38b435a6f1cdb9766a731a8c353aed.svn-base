package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.model.homeclassify.ClassifyRank2;
import com.baosteel.qcsh.ui.activity.prodcut.ProductListActivity;

public class ClassifyProductCategoryAdapter extends BaseAdapter{

	private static final String TAG = "ProductCategoryItemAdapter";
	private Context mContext;
	private LayoutInflater mInflater;
	private List<ClassifyRank2> mCategoryItems;
	
	public ClassifyProductCategoryAdapter(Context context, List<ClassifyRank2> items) {
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
	public void setData(List<ClassifyRank2> items) {
		
		if (items == null) {
			return;
		}
		
		
		
		if (mCategoryItems == null) {
			mCategoryItems = new ArrayList<ClassifyRank2>();
		}
		mCategoryItems.clear();
		mCategoryItems.addAll(items);
		
		/*
		 * 判断ClassifyRank2对象是否存在下一级分类
		 */
		for (int i = 0; i < items.size(); i++) {
			ClassifyRank2 item = mCategoryItems.get(i);
			if (item.getmRank3Items() == null || item.getmRank3Items().size() == 0) {
				item.setHasRank3(false);
			}
		}
		
		notifyDataSetChanged();
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
		final ClassifyRank2 item = mCategoryItems.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_classify_product_category, null);
			holder.itemTitle = (TextView) convertView.findViewById(R.id.tv_title);
			holder.gridView = (GridView) convertView.findViewById(R.id.gridview);
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();
		holder.itemTitle.setText(item.getName());
		Log.d("zhiheng", "rank2 title = "+item.getName()+", rank3Items size = "+item.getmRank3Items().size());
		
		if (!item.isHasRank3()) {//判断是否有三级分类
			
			/**
			 * 如果没有三级分类，则隐藏二级分类标题，并且在三级分类数据个数为0时（保证只添加一次），将二级分类当成三级分类显示
			 */
			if (item.getmRank3Items().size() == 0) {//之后三级分类列表中数据个数为0时
				ProductCategory category = item;
				item.getmRank3Items().add(category);//将二级分类放到三级分类中显示
			}
			holder.itemTitle.setVisibility(View.GONE);//隐藏二级分类标题
			holder.gridView.setAdapter(new ProductCategoryItemAdapter(mContext, item.getmRank3Items()));
		
		} else {
			holder.itemTitle.setVisibility(View.VISIBLE);
			holder.gridView.setAdapter(new ProductCategoryItemAdapter(mContext, item.getmRank3Items()));
		}
		
		holder.gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(mContext, ProductListActivity.class);
				intent.putExtra(ProductListActivity.EXTRA_INTENT_FROM, ProductListActivity.INTENT_FROM_CLASSIFY);
				intent.putExtra(ProductListActivity.EXTRA_SEARCH_KEYWORD, item.getmRank3Items().get(position).getName());
				intent.putExtra(ProductListActivity.EXTRA_PRODUCT_TYPE_ID, item.getmRank3Items().get(position).getId());
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}
	
	class ViewHolder {
		TextView itemTitle;
		GridView gridView;
	}

}

