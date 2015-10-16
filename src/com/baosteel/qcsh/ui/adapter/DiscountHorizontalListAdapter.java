package com.baosteel.qcsh.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;

public class DiscountHorizontalListAdapter extends BaseAdapter {

	private Context context;

	public DiscountHorizontalListAdapter(Context context) {
		this.context=context;
	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public Object getItem(int arg0) {

		return null;
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_item_discount,null);
			holder.tv_content= (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String [] data={"免运费","七彩生活","促销","健康币支付","支付宝"};
		holder.tv_content.setText(data[position]);
		if(position==0){
			holder.tv_content.setBackgroundResource(R.drawable.shape_radius_transparent_strok_brickred);
			holder.tv_content.setTextColor(context.getResources().getColor(R.color.brick_red));
		}else{
			holder.tv_content.setBackgroundResource(R.drawable.common_radius_transparent_strok_gray);
			holder.tv_content.setTextColor(context.getResources().getColor(R.color.store_gray));
		}
		return convertView;
	}

	private class ViewHolder {
		TextView tv_content;//劵类型
	}
}
