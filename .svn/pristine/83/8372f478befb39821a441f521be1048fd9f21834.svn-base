package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HousingEstateNotice;
import com.baosteel.qcsh.ui.adapter.SimpleTextAdapter.ViewHolder;
import com.baosteel.qcsh.utils.ImageManager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @description 小区通知列表的Adapter
 * @author blue
 * @Time 2015-9-2
 *
 */
public class HousingEstateNoticesAdapter extends BaseAdapter{

	private Context mContext;
	private LayoutInflater mInflater;
	private List<HousingEstateNotice> mData;
	
	public HousingEstateNoticesAdapter(Context context, List<HousingEstateNotice> data) {
		this.mContext = context;
		this.mData = data;
		this.mInflater = LayoutInflater.from(context);
		Log.d("zhiheng", "===> HousingEstateNoticesAdapter");
	}
	
	@Override
	public int getCount() {
		Log.d("zhiheng", "===> getCount,count = "+(mData == null ? 0 : mData.size()));
		return mData == null ? 0 : mData.size();
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
		Log.d("zhiheng", "notice getView");
		HousingEstateNotice notice = mData.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_housing_estate_notice, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_housing_estate_name);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_notice_title);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_release_time);
			holder.ivLogo = (ImageView) convertView.findViewById(R.id.iv_housing_estate_logo);
			convertView.setTag(holder);
		
		}
		
		holder = (ViewHolder) convertView.getTag();
		holder.tvTitle.setText(notice.getTitle());
		holder.tvName.setText(notice.getHousingEstateName());
		holder.tvTime.setText(notice.getTime());
		if (notice.getHousingEstateIconUrl() != null) {
			ImageManager.Load(notice.getHousingEstateIconUrl(), holder.ivLogo);
		}
		
		return convertView;
	}
	
	class ViewHolder{
		TextView tvTitle;
		TextView tvName;
		TextView tvTime;
		ImageView ivLogo;
	}

}
