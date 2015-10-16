package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ShouldKnowData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ShouldKnowAdapter extends BaseAdapter{

	private Context mContext;
	private List<ShouldKnowData> mDatas;
	private LayoutInflater mInflater;
	
	public ShouldKnowAdapter(Context context, List<ShouldKnowData> datas) {
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
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_should_know, null);
		}
			
		TextView title = (TextView) convertView.findViewById(R.id.tv_title);
		if (mDatas.get(position).getTitle() == null) {
			if (position % 2 == 0) {
				title.setText("小区消防安全条例须知");
			} else {
				title.setText("和谐小区消防安全演练进行中");
			}
		} else {
			title.setText(mDatas.get(position).getTitle());
		}
		
		return convertView;
	}

	
}
