package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
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
 * 搜索历史适配器
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-26
 */
public class SearchHistoryAdapter extends CustomAdapter {

	/**上下文**/
	private Context context;
	
	/**搜索历史**/
	private List<SearchItem> dataList;
	
	public SearchHistoryAdapter(Context context) {
		this.context = context;
	}

	
	/**
	 * 刷新数据
	 */
	public void refreshData(List<SearchItem> dataList){
		this.dataList = dataList;
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return null != dataList ? dataList.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	/**
	 * 根据索引获取数据
	 * @param position
	 * @return
	 */
	public SearchItem getData(int position){
		try{
			return dataList.get(position);
		}catch(Exception e){
			return null;
		}
	}
	
	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View contentView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (null == contentView) {
			viewHolder = new ViewHolder();
			contentView = LayoutInflater.from(context).inflate(R.layout.item_spec, null);
			viewHolder.name = (TextView) contentView.findViewById(R.id.tv_spec);
			contentView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) contentView.getTag();
		}
		
		//显示文本
		SearchItem bean = dataList.get(position);
		viewHolder.name.setText(bean.getmTitle());

		//设置选中
		boolean selected = (selectPosition == position);
		viewHolder.name.setSelected(selected);
		
		return contentView;
	}

	//设置选中项
	private int selectPosition = 0;
	public void selectIndex(int index){
		this.selectPosition = index;
		this.notifyDataSetChanged();
	}
	
	
	private class ViewHolder {
		TextView name;
	}
}
