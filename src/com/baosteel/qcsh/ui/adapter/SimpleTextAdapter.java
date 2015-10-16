package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.BaseItemData;
import com.baosteel.qcsh.utils.ImageManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SimpleTextAdapter extends BaseAdapter{

	private Context mContext;
	private List<? extends BaseItemData> mData;
	private LayoutInflater mInflater;
	
	/**
	 * 选中的Item（或者ListView中被点击的Item）
	 */
	private int mSelectedItem = 0;
	
	public SimpleTextAdapter(){}
	
	public SimpleTextAdapter(Context context, List<? extends BaseItemData> data) {
		mContext = context;
		mData = data;
		mInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return mData == null ? 0 : mData.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	public void setSelectedItem(int index) {
		mSelectedItem = index;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseItemData itemData = mData.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_simple_text_listview, null);
			holder = new ViewHolder();
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_item_icon);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_item_title);
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();

		if (itemData.getmIconUrl() != null) {
			ImageManager.Load(itemData.getmIconUrl(), holder.ivIcon);
		} else {
			holder.ivIcon.setImageResource(itemData.getmIconId());
		}
		
		holder.tvName.setText(itemData.getmName());
		
		return null;
	}

	class ViewHolder{
		ImageView ivIcon;
		TextView tvName;
	}
}
