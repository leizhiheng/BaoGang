package com.baosteel.qcsh.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baosteel.qcsh.R;

/**
 * 红包列表适配器
 */
public class RedPackageListAdapter extends BaseAdapter {

	private Context context;
	private int type;//1.未领取 2.已领取

	public RedPackageListAdapter(Context context,int type) {
		this.context=context;
		this.type=type;
	}

	@Override
	public int getCount() {

		return 2;
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
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_item_redpackage,null);
			holder.tv_juan_type= (TextView) convertView.findViewById(R.id.tv_juan_type);
			holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
			holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
			holder.facePriceTv= (TextView) convertView.findViewById(R.id.facePriceTv);
			holder.specificationTv= (TextView) convertView.findViewById(R.id.specificationTv);
			holder.layout_main= (LinearLayout) convertView.findViewById(R.id.layout_main);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(type==1){
			holder.tv_juan_type.setText("现金劵");
			holder.tv_name.setText("￥20");
			holder.tv_juan_type.setTextColor(context.getResources().getColor(R.color.gray_juan));
			holder.tv_name.setTextColor(context.getResources().getColor(R.color.gray_juan));
			holder.layout_main.setBackgroundResource(R.drawable.icon_jiangquan_weilingqu);
			holder.tv_title.setTextColor(Color.BLACK);
			holder.facePriceTv.setTextColor(Color.BLACK);
		}else{
			holder.tv_juan_type.setText("优惠劵（已领取）");
			holder.tv_name.setText("满200减100");
			holder.tv_juan_type.setTextColor(context.getResources().getColor(R.color.gray_juan_recieve));
			holder.tv_name.setTextColor(context.getResources().getColor(R.color.gray_juan_recieve));
			holder.layout_main.setBackgroundResource(R.drawable.icon_gouwuche_yilingqu);
			holder.tv_title.setTextColor(context.getResources().getColor(R.color.gray_juan_recieve));
			holder.facePriceTv.setTextColor(context.getResources().getColor(R.color.gray_juan_recieve));
		}
		return convertView;
	}

	private class ViewHolder {
		TextView tv_juan_type;//劵类型
		TextView tv_name;//劵名称
		TextView tv_title;//主题
		TextView facePriceTv;//描述
		TextView specificationTv;//时间
		LinearLayout layout_main;//主布局
	}
}
