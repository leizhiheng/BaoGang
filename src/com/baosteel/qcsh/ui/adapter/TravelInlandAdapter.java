package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.KeyValue;

/**
 * 旅行宝-国内游-适配
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-9
 */
public class TravelInlandAdapter extends BaseAdapter {

	/** 上下文 **/
	private Activity mContext;

	public TravelInlandAdapter(Activity context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// return null == dataList ? 0 : dataList.size();
		return 10;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		// return dataList.get(arg0);
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_travel_inland, null);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.title = (TextView) convertView.findViewById(R.id.name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}

	private class ViewHolder {
		ImageView img;
		TextView title;
	}

	/**
	 * 刷新数据
	 * 
	 * @param dataList
	 */
	public void refreshData(List<KeyValue<String, String>> dataList) {
		//this.dataList = dataList;
		//this.notifyDataSetChanged();
	}

}
