package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CancelOrderReason;

public class CancelOrderReasonAdapter extends BaseAdapter{

	private Context mContext;
	private List<CancelOrderReason> mDatas;
	private LayoutInflater mInflater;
	
	private int mSelectIndex;
	
	public CancelOrderReasonAdapter(Context context, List<CancelOrderReason> datas) {
		this.mContext = context;
		this.mDatas = datas;
		this.mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
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
		final CancelOrderReason reason = mDatas.get(position);
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_cancel_order_reason, null);
		}
		
		ImageView ivSelected = (ImageView) convertView.findViewById(R.id.iv_selected);
		TextView tvReasonTextView = (TextView) convertView.findViewById(R.id.tv_reason);
		
		
		if (reason.isSelected()) {
			ivSelected.setImageResource(R.drawable.ic_checkbox_bg_sel);
		} else {
			ivSelected.setImageResource(R.drawable.ic_checkbox_bg_nlr);
		}
		tvReasonTextView.setText(reason.getName());
		
		
		return convertView;
	}

	public int getSelectedIndex() {
		return mSelectIndex;
	}
}
