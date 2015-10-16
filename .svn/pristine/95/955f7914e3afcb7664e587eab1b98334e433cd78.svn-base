package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.SearchItem;

/**
 * 旅行宝-热门城市
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-11
 */
public class HotAddressAdapter extends BaseAdapter {

	private List<SearchItem> mData;
	private Context context;
	
	public HotAddressAdapter(Context context, List<SearchItem> data) {
		this.mData = data;
		this.context = context;
	}

	@Override
	public int getCount() {
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
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_search_gridview, null);
		}
		TextView title = (TextView) convertView.findViewById(R.id.tv_search_title);
		title.setText(mData.get(position).getmTitle());
		return convertView;
	}

}
