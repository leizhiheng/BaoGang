package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.utils.ImageManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SimplePicturesAdapter extends BaseAdapter{
	private Context mContext;
	private List<String> mDatas;
	private LayoutInflater mInflater;
	
	public SimplePicturesAdapter(Context context, List<String> datas) {
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
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_simple_pictures, null);
		}
		ImageView imageView = (ImageView) convertView;
		ImageManager.Load(mDatas.get(position), imageView);
		return convertView;
	}
}
