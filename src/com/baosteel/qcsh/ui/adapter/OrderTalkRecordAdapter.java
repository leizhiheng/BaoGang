package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.OrderItem;

/**
 * 订单-协商记录
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-21
 */
public class OrderTalkRecordAdapter extends BaseAdapter {

	/** 上下文 **/
	private Context mContext;

	/** layout加载器 **/
	private LayoutInflater mInflater;

	public OrderTalkRecordAdapter(Context context) {
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// return mDatas == null ? 0 : mDatas.size();
		return 10;
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_order_talk_record, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();

		return convertView;
	}

	class ViewHolder {

		public ImageView shopIconIv;
		public TextView shopNameTv;
		public TextView dateIv;
		public TextView stateNameTv;
		public TextView contentTv;

		public ViewHolder(View view) {

			shopIconIv = (ImageView) view.findViewById(R.id.shopIconIv);
			shopNameTv = (TextView) view.findViewById(R.id.shopNameTv);
			dateIv = (TextView) view.findViewById(R.id.dateIv);
			stateNameTv = (TextView) view.findViewById(R.id.stateNameTv);
			contentTv = (TextView) view.findViewById(R.id.contentTv);
		}

		/**
		 * 显示数据
		 * 
		 * @param order
		 */
		public void showData() {

		}
	}

}
