package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ServiceData;
import com.baosteel.qcsh.model.ShouldKnowData;
import com.baosteel.qcsh.ui.activity.StartActivity;
import com.baosteel.qcsh.ui.activity.home.happyliving.CommitReserveMsgActivity;
import com.baosteel.qcsh.ui.activity.home.happyliving.ServiceCommentActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ReserveServiceAdapter extends BaseAdapter {

	private Context mContext;
	private List<ServiceData> mDatas;
	private LayoutInflater mInflater;
	
	public ReserveServiceAdapter(Context context, List<ServiceData> datas) {
		this.mContext = context;
		this.mDatas = datas;
		this.mInflater = LayoutInflater.from(mContext);
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
		ServiceData data = mDatas.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_reserve_service, null);
			holder.tvComment = (TextView) convertView.findViewById(R.id.tv_service_comment);
			holder.tvCall = (TextView) convertView.findViewById(R.id.btn_call);
			holder.tvReserve = (TextView) convertView.findViewById(R.id.btn_reserve);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		
		holder.tvComment.setOnClickListener(myClickListener);
		holder.tvReserve.setOnClickListener(myClickListener);
		return convertView;
	}

	class ViewHolder{
		TextView tvComment;
		TextView tvReserve;
		TextView tvCall;
	}
	
	private OnClickListener myClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_service_comment:
				mContext.startActivity(new Intent(mContext, ServiceCommentActivity.class));
				break;
			case R.id.btn_reserve:
				mContext.startActivity(new Intent(mContext, CommitReserveMsgActivity.class));
				break;
			default:
				break;
			}
		}
	};
}

