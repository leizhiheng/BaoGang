package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.SearchItem;
import com.custom.vg.list.CustomAdapter;

/**
 * 搜索适配器
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-23
 */
public class SearchItemAdapter extends CustomAdapter{

	/**上下文**/
	private Context mContext;

	/**搜索记录**/
	private List<SearchItem> mData;
	
	public SearchItemAdapter(Context context, List<SearchItem> data) {
		this.mContext = context;
		this.mData = data;
	}
	
	/**
	 * 刷新数据
	 * @param list
	 */
	public void refreshData(List<SearchItem> list) {
		this.mData = list;
		this.notifyDataSetChanged();
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_gridview, null);
		}
		
		TextView title= (TextView) convertView.findViewById(R.id.tv_search_title);
		title.setText(mData.get(position).getmTitle());
		return convertView;
	}
	
}