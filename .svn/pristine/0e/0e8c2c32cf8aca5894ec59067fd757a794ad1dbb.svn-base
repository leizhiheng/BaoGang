package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.TopbarMenuItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TopbarMenuAdapter extends BaseAdapter{
	private Context mContext;
	private LayoutInflater mInflater;
	private List<TopbarMenuItem> mDatas;
	
	public TopbarMenuAdapter(Context context, List<TopbarMenuItem> items) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = items;
		
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
		
		convertView = mInflater.inflate(R.layout.adapter_topbar_menu, null);
		ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_menu_icon);
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_menu_title);
		View line = convertView.findViewById(R.id.line);
		
		ivIcon.setImageResource(mDatas.get(position).getIconId());
		tvTitle.setText(mDatas.get(position).getTitle());
		
		if (position == mDatas.size() - 1) {
			line.setVisibility(View.INVISIBLE);
		} else {
			line.setVisibility(View.VISIBLE);
		}
		
		return convertView;
	}

}



