package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HousemakingData;

public class HousemakingAdapter extends BaseAdapter{

	private Context mContext;
	private List<HousemakingData> mDatas;
	private LayoutInflater mInflater;
	
	public HousemakingAdapter(Context context, List<HousemakingData> datas) {
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_housemaking, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();
		holder.iconView.setBackgroundResource(mDatas.get(position).getBgDrawableId());
		holder.title.setText(mDatas.get(position).getTitle());
		holder.icon.setImageResource(mDatas.get(position).getIconId());
		return convertView;
	}
	
	class ViewHolder{
		ImageView icon;
		View iconView;
		TextView title;
		
		public ViewHolder(View v) {
			icon = (ImageView) v.findViewById(R.id.iv_housemaking_icon);
			title = (TextView) v.findViewById(R.id.tv_housemaking_name);
			iconView = v.findViewById(R.id.layout_icon);
		}
	}

}
