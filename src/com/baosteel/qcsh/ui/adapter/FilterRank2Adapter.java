package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.FilterGridItem;

public class FilterRank2Adapter extends BaseAdapter{

	private Context mContext;
	private List<FilterGridItem> mDatas;
	private LayoutInflater mInflater;
	
	public FilterRank2Adapter(Context context, List<FilterGridItem> datas) {
		this.mContext = context;
		this.mDatas = datas;
		this.mInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	public void setData(List<FilterGridItem> datas) {
		if (mDatas == null) {
			mDatas = new ArrayList<FilterGridItem>();
		}
		mDatas.addAll(datas);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		FilterGridItem item = mDatas.get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_filter_rank_2, null);
		}
		
		TextView title = (TextView) convertView.findViewById(R.id.tv_title);
		ImageView ivSelected = (ImageView) convertView.findViewById(R.id.iv_selected);
		
		title.setText(item.getValue());
		if (item.isSelected()) {
			ivSelected.setVisibility(View.VISIBLE);
		} else {
			ivSelected.setVisibility(View.INVISIBLE);
		}
		
		return convertView;
	}
	
}

