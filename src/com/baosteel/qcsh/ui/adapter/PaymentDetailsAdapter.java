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
import com.baosteel.qcsh.model.BaseItemData;
import com.baosteel.qcsh.model.PaymentDetailsItem;
import com.baosteel.qcsh.utils.ImageManager;

public class PaymentDetailsAdapter  extends BaseAdapter{

	private Context mContext;
	private List<PaymentDetailsItem> mData;
	private LayoutInflater mInflater;
	
	/**
	 * 选中的Item（或者ListView中被点击的Item）
	 */
	private int mSelectedItem = 0;
	
	public PaymentDetailsAdapter(Context context, List<PaymentDetailsItem> data) {
		mContext = context;
		mData = data;
		mInflater = LayoutInflater.from(mContext);
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
	
	public void setSelectedItem(int index) {
		mSelectedItem = index;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PaymentDetailsItem itemData = mData.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_payment_details, null);
			holder = new ViewHolder();
			holder.tvValue = (TextView) convertView.findViewById(R.id.tv_detail_item_value);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_detail_item_name);
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();

		
		holder.tvName.setText(itemData.getName());
		holder.tvValue.setText(itemData.getValue());
		
		return convertView;
	}

	class ViewHolder{
		TextView tvValue;
		TextView tvName;
	}
}
