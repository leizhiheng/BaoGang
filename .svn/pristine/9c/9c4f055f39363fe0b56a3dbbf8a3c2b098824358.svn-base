package com.baosteel.qcsh.ui.adapter;

import com.baosteel.qcsh.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CombinationProductAdapter extends BaseAdapter {

	private Context context;

	public CombinationProductAdapter(Context context) {
		this.context=context;
	}

	@Override
	public int getCount() {

		return 10;
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
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_item_combination_products,null);
			holder.iv_pro= (ImageView) convertView.findViewById(R.id.iv_pro);
			holder.iv_add= (TextView) convertView.findViewById(R.id.iv_add);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(position==0){
			holder.iv_add.setVisibility(View.GONE);
		}else{
			holder.iv_add.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	private class ViewHolder {
		TextView iv_add;
		ImageView iv_pro;
	}
}
