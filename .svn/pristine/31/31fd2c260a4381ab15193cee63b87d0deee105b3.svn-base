package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.FilterGridItem;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FilterListConditionAdapter extends BaseAdapter{

	private Context mContext;
	private List<FilterGridItem> mDatas;
	private LayoutInflater mInflater;
	
	public FilterListConditionAdapter(Context context, List<FilterGridItem> datas) {
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
			convertView = mInflater.inflate(R.layout.adapter_filter_list_condition, null);
		}
		
		TextView title = (TextView) convertView.findViewById(R.id.tv_title);
		TextView conditionTitle = (TextView) convertView.findViewById(R.id.tv_condition_title);
		
		title.setText(item.getName());
		Log.d("zhiheng", "position = "+position+", condition name = "+item.getName()+", condition value = "+item.getConditionValue());
		if (TextUtils.isEmpty(item.getConditionValue())) {
			conditionTitle.setTextColor(mContext.getResources().getColor(R.color.font_gray));
			conditionTitle.setText("全部");
		} else {
			conditionTitle.setText(item.getConditionValue());
			conditionTitle.setTextColor(mContext.getResources().getColor(R.color.index_red));
		}
		
		return convertView;
	}

	public void setData(List<FilterGridItem> datas) {
		this.mDatas = datas;
		notifyDataSetChanged();
	}

	
}
