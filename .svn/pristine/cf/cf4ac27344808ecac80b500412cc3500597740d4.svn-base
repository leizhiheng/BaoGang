package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HealthyContactPerson;
import com.baosteel.qcsh.model.HealthyLiveInPerson;

public class HealthyLiveInPersonsAdapter extends BaseAdapter {

	private Context mContext;
	private List<HealthyContactPerson> mDatas;
	private LayoutInflater mInflater;
	
	public HealthyLiveInPersonsAdapter(Context context, List<HealthyContactPerson> datas) {
		mContext = context;
		mDatas =datas;
		mInflater = LayoutInflater.from(context);
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
		HealthyContactPerson person = mDatas.get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_healthy_live_in_persons, null);
		}
		
		TextView tvName = (TextView) convertView.findViewById(R.id.tv_live_in_name);
		TextView tvSex = (TextView) convertView.findViewById(R.id.tv_live_in_sex);
		TextView tvIdentifyCode = (TextView) convertView.findViewById(R.id.tv_live_in_identify_code);
		
		tvIdentifyCode.setText(person.getIdentiryCode());
		tvName.setText(person.getName());
		tvSex.setText(person.getSex());
		return convertView;
	}

}

