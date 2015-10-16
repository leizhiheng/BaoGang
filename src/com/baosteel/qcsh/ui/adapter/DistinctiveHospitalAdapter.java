package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.DistinctiveHospitalData;
import com.baosteel.qcsh.ui.adapter.ChooseContactPersonAdapter.ViewHolder;

public class DistinctiveHospitalAdapter extends BaseAdapter{

	private Context mContext;
	private List<DistinctiveHospitalData> mDatas;
	private LayoutInflater mInflater;
	
	public DistinctiveHospitalAdapter(Context context, List<DistinctiveHospitalData> datas) {
		this.mContext = context;
		this.mDatas = datas;
		mInflater = LayoutInflater.from(mContext);
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
		
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_distinctive_hospital, null);
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();
		holder.ivIcon.setImageResource(R.drawable.ic_healthy_hospital);
		
		return convertView;
	}

	class ViewHolder{
		ImageView ivIcon;
		
		public ViewHolder(View v) {
			ivIcon = (ImageView) v.findViewById(R.id.iv_icon);
		}
	}
}
