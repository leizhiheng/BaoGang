package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.FilterGridItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FilterGridConditionAdapter extends BaseAdapter{

	private Context mContext;
	private List<FilterGridItem> mDatas;
	private LayoutInflater mInflater;
	
	public FilterGridConditionAdapter(Context context, List<FilterGridItem> datas) {
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		FilterGridItem item = mDatas.get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_filter_grid_condition, null);
		}
		
		TextView rightIcon = (TextView) convertView.findViewById(R.id.tv_right_icon);
		TextView conditionTitle = (TextView) convertView.findViewById(R.id.tv_condition_title);
		
		if (item.isSelected()) {
			convertView.setBackgroundResource(R.drawable.shape_radius_red_full_white);
			rightIcon.setVisibility(View.VISIBLE);
			conditionTitle.setTextColor(mContext.getResources().getColor(R.color.index_red));
		} else {
			convertView.setBackgroundResource(R.drawable.shape_radius_black_full_white);
			rightIcon.setVisibility(View.GONE);
			conditionTitle.setTextColor(mContext.getResources().getColor(R.color.black));
		}
		
		conditionTitle.setText(item.getName());
		
		return convertView;
	}

	
}
