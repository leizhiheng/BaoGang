package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.KeyValue;

/**
 * 水平listview适配器
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-9
 */
public class HscViewAdapter extends BaseAdapter {

	/** 上下文 **/
	private Activity mContext;

	/** 选中的索引 **/
	private int selectIndex = 0;

	private List<KeyValue<String, String>> dataList;

	public HscViewAdapter(Activity context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.dataList = new ArrayList<KeyValue<String, String>>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return null == dataList ? 0 : dataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return dataList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setCurrent(int positon) {
		this.selectIndex = positon;
		this.notifyDataSetChanged();
	}

	public void addItems(List<KeyValue<String, String>> tempList) {
		this.dataList = tempList;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_horizon_listview_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.h_list_item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText(dataList.get(position).getValue());
		holder.title.setSelected(position == selectIndex ? true : false);

		return convertView;
	}

	private class ViewHolder {
		TextView title;
	}

	/**
	 * 刷新数据
	 * 
	 * @param dataList
	 */
	public void refreshData(List<KeyValue<String, String>> dataList) {
		this.dataList = dataList;
		this.notifyDataSetChanged();
	}

}
