package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.Attribute;
import com.baosteel.qcsh.model.SearchItem;
import com.custom.vg.list.CustomAdapter;

import java.util.List;

/**
 * 商品属性适配器
 * @author kuangyong
 *
 * @todo TODO
 *
 * @date 2015-9-28
 */
public class Attribute2ListAdapter extends CustomAdapter {

	/**上下文**/
	private Context context;

	private List<Attribute> data;
	
	public Attribute2ListAdapter(Context context) {
		this.context = context;
	}

	
	/**
	 * 刷新数据
	 */
	public void refreshData(List<Attribute> data){
		this.data = data;
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return null != data ? data.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
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
			contentView = LayoutInflater.from(context).inflate(R.layout.item_attribute, null);
			viewHolder.tv_attr = (TextView) contentView.findViewById(R.id.tv_attr);
			contentView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) contentView.getTag();
		}
		if(data.get(position).isSelected()){
			viewHolder.tv_attr.setBackgroundResource(R.drawable.selector_radiu_brickred);
		}else{
			viewHolder.tv_attr.setBackgroundResource(R.drawable.selector_radiu_gray);
		}

		return contentView;
	}

	private class ViewHolder {
		TextView tv_attr;
	}
}
