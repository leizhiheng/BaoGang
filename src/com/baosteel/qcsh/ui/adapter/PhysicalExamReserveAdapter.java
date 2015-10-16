package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.PhysicalExamPackageData;
import com.baosteel.qcsh.ui.view.TitleBar;

public class PhysicalExamReserveAdapter extends BaseAdapter{

	private Context mContext;
	private List<PhysicalExamPackageData> mDatas;
	private LayoutInflater mInflater;
	
	public PhysicalExamReserveAdapter(Context context, List<PhysicalExamPackageData> datas) {
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
			convertView = mInflater.inflate(R.layout.adapter_physical_exam_reserve, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();
		
		holder.title.setText(mDatas.get(position).getTitle());
		holder.icon.setImageResource(mDatas.get(position).getIconId());
		
		return convertView;
	}
	
	class ViewHolder {
		ImageView icon;
		TextView title;
		
		public ViewHolder(View view) {
			icon = (ImageView) view.findViewById(R.id.iv_icon);
			title = (TextView) view.findViewById(R.id.tv_title);
		}
	}

}
