package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.MyReleaseRecordData;

public class MyReleaseRecordAdapter extends BaseAdapter{

	private Context mContext;
	private List<MyReleaseRecordData> mDatas;
	private LayoutInflater mInflater;
	
	public MyReleaseRecordAdapter(Context context, List<MyReleaseRecordData> datas) {
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
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_my_release_record, null);
		}
		return convertView;
	}

}

