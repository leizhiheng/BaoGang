package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.dialog.SimpleMsgDialog;
import com.baosteel.qcsh.model.MyReportPrepairData;
import com.baosteel.qcsh.model.MyReserveServiceData;

/**
 * 
 * @description 乐居宝-》物业服务-》我的物业-》我的预约服务 页面中ListView的数据适配器
 * @author blue
 * @Time 2015-9-10
 *
 */
public class MyReportRepairAdapter extends BaseAdapter{

	private Context mContext;
	private List<MyReportPrepairData> mDatas;
	private LayoutInflater mInflater;
	
	public MyReportRepairAdapter(Context context, List<MyReportPrepairData> datas) {
		mContext = context;
		mDatas =datas;
		mInflater = LayoutInflater.from(context);
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
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_my_report_repair, null);
		}
		
		TextView cancelReserve = (TextView) convertView.findViewById(R.id.btn_cancel_reserve);
		cancelReserve.setOnClickListener(myOncliClickListener);
		return convertView;
	}
	
	private OnClickListener myOncliClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btn_cancel_reserve) {
				final SimpleMsgDialog dialog = new SimpleMsgDialog(mContext);
				dialog.setMsg("您确定要取消本次预约吗？");
				dialog.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (v.getId() == R.id.btn_ok) {
							Toast.makeText(mContext, "Button OK has been Clicked", Toast.LENGTH_SHORT).show();
						} else if (v.getId() == R.id.btn_cancel) {
							Toast.makeText(mContext, "Button Cancel has been Clicked", Toast.LENGTH_SHORT).show();
						}
						dialog.dismiss();
					}
				});
				dialog.show();
			}
		}
	};

}
