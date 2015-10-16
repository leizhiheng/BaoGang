package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺2级列表适配器
 */
public class Store2CategoryListAdapter extends BaseAdapter{

	private Context mContext;
	private LayoutInflater mInflater;
	private List<ProductCategory> data;


	public Store2CategoryListAdapter(Context context,List<ProductCategory> data) {
		mContext = context;
		this.data = data;
		mInflater = LayoutInflater.from(mContext);
	}

	/**
	 * 刷新数据
	 * @param data
	 */
	public void refreshDate(List<ProductCategory> data){
		this.data=data;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return null==data?0:data.size();
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_store_2category_item, null);
			holder = new ViewHolder();
			holder.tv_item_title = (TextView) convertView.findViewById(R.id.tv_item_title);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		holder.tv_item_title.setText(data.get(position).getName());
		return convertView;
	}

	class ViewHolder{
		TextView tv_item_title;
	}
}
