package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.RoomerMsgData;

public class RoomerMsgAdapter extends BaseAdapter{

	private Context mContext;
	private List<RoomerMsgData> mDatas;
	private LayoutInflater mInflater;
	
	public RoomerMsgAdapter(Context context, List<RoomerMsgData> datas) {
		this.mContext = context;
		this.mDatas = datas;
		this.mInflater = LayoutInflater.from(context);
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
		RoomerMsgData msg = mDatas.get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_house_msg, null);
		}
		
		TextView title = (TextView) convertView.findViewById(R.id.tv_title);
		TextView content = (TextView) convertView.findViewById(R.id.tv_content);
		View fix = convertView.findViewById(R.id.layout_fix);
		
		title.setText(msg.getTitle());
		content.setText(msg.getContent());
		if (msg.isCanSelect()) {
			fix.setVisibility(View.VISIBLE);
		} else {
			fix.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}
	
}
