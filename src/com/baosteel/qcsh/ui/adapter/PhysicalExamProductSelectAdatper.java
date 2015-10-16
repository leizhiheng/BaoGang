package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.PysicalExamProductSelectItem;

public class PhysicalExamProductSelectAdatper extends BaseAdapter{

	private static final String TAG = "ProductCategoryItemAdapter";
	private Context mContext;
	private LayoutInflater mInflater;
	private List<PysicalExamProductSelectItem> mDatas;
	
	public PhysicalExamProductSelectAdatper(Context context, List<PysicalExamProductSelectItem> items) {
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_physical_exam_select, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		
		return convertView;
	}
	
	class ViewHolder {
		TextView itemTitle;
		ImageView loadIcon;
		
		public ViewHolder(View v) {
			loadIcon = (ImageView) v.findViewById(R.id.iv_load_icon);
		}
	}

}


