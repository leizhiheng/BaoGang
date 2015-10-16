package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.Balance;

/**
 * 现金券
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-11
 */
public class BalanceAdapter extends BaseAdapter {

	private List<Balance> dataList;
	private Context context;
	
	public BalanceAdapter(Context context, List<Balance> data) {
		this.dataList = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		return dataList == null ? 0 : dataList.size();
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
			convertView = LayoutInflater.from(context).inflate(R.layout.adapter_blance_item, null);
		}
//		TextView title = (TextView) convertView.findViewById(R.id.tv_search_title);
//		title.setText(dataList.get(position).getmTitle());
		return convertView;
	}

}
