package com.baosteel.qcsh.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;

public class JuanHorizontalListAdapter extends BaseAdapter {

	private Context context;

	public JuanHorizontalListAdapter(Context context) {
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
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_item_juan,null);
			holder.tv_juan_type= (TextView) convertView.findViewById(R.id.tv_juan_type);
			holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
			holder.layout_main= (LinearLayout) convertView.findViewById(R.id.layout_main);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(position%2==0){
			holder.tv_juan_type.setText("现金劵");
			holder.tv_name.setText("￥20");
			holder.tv_juan_type.setTextColor(context.getResources().getColor(R.color.gray_juan));
			holder.tv_name.setTextColor(context.getResources().getColor(R.color.gray_juan));
			holder.layout_main.setBackgroundResource(R.drawable.icon_jiangquan_weilingqu);
		}else{
			holder.tv_juan_type.setText("优惠劵（已领取）");
			holder.tv_name.setText("满200减100");
			holder.tv_juan_type.setTextColor(context.getResources().getColor(R.color.gray_juan_recieve));
			holder.tv_name.setTextColor(context.getResources().getColor(R.color.gray_juan_recieve));
			holder.layout_main.setBackgroundResource(R.drawable.icon_gouwuche_yilingqu);
		}
		return convertView;
	}

	private class ViewHolder {
		TextView tv_juan_type;//劵类型
		TextView tv_name;//劵名称
		LinearLayout layout_main;//主布局
	}
}
