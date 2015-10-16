package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.dialog.SimpleMsgDialog;
import com.baosteel.qcsh.model.MyReserveServiceData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @description 乐居宝-》物业服务-》我的物业-》我的预约服务 页面中ListView的数据适配器
 * @author blue
 * @Time 2015-9-10
 *
 */
public class MyReserveServiceAdapter extends BaseAdapter implements OnClickListener{

	private Context mContext;
	private List<MyReserveServiceData> mDatas;
	private LayoutInflater mInflater;
	
	public MyReserveServiceAdapter(Context context, List<MyReserveServiceData> datas) {
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
			convertView = mInflater.inflate(R.layout.adapter_my_reserve_service, null);
		}
		
		TextView tvCancelReserve = (TextView) convertView.findViewById(R.id.btn_cancel_reserve);
		tvCancelReserve.setOnClickListener(this);
		return convertView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cancel_reserve:
			final SimpleMsgDialog dialog = new SimpleMsgDialog(mContext);
			dialog.setMsg("您确定要取消本次预约吗？");
			dialog.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (v.getId() == R.id.btn_ok) {
						Toast.makeText(mContext, "Button OK has been clicked", Toast.LENGTH_SHORT).show();
					} else if (v.getId() == R.id.btn_cancel) {
						Toast.makeText(mContext, "Button Cancel has been clicked", Toast.LENGTH_SHORT).show();
					}
					dialog.dismiss();
				}
			});
			dialog.show();
			break;

		default:
			break;
		}
	}

}
