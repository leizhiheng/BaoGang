package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ServiceData;
import com.baosteel.qcsh.model.WifiLease;
import com.baosteel.qcsh.ui.activity.home.happyliving.CommitReserveMsgActivity;
import com.baosteel.qcsh.ui.activity.home.happyliving.ServiceCommentActivity;

import java.util.List;

public class WifiLeaseAdapter extends BaseAdapter {

	private Context mContext;
	private List<WifiLease> mDatas;
	private LayoutInflater mInflater;

	public WifiLeaseAdapter(Context context, List<WifiLease> datas) {
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
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_wifi_lease, null);
			holder.mTv_wifi_name = (TextView) convertView.findViewById(R.id.tv_wifi_name);
			holder.mTv_price = (TextView) convertView.findViewById(R.id.tv_price);
			holder.mTv_count = (TextView) convertView.findViewById(R.id.tv_count);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mTv_wifi_name.setText(mDatas.get(position).getName());
		holder.mTv_price.setText(mDatas.get(position).getPrice());
		holder.mTv_count.setText("月销量"+mDatas.get(position).getCount()+"份");
		return convertView;
	}

	class ViewHolder{
		public TextView mTv_wifi_name;
		public TextView mTv_price;
		public TextView mTv_count;
	}
	
}

