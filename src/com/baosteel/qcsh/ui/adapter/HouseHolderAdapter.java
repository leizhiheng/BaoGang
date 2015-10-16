package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HouseData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HouseHolderAdapter extends BaseAdapter{

	private Context mContext;
	private List<HouseData> mDatas;
	private LayoutInflater mInflater;
	
	public HouseHolderAdapter(Context mContext, List<HouseData> datas) {
		this.mContext = mContext;
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
			convertView = mInflater.inflate(R.layout.item_house_holder, null);
		}
		return convertView;
	}

}
