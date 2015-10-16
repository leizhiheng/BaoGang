package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.AmendComment;

public class ServiceAmendContentAdapter extends BaseAdapter{

	private Context mContext;
	private List<AmendComment> mDatas;
	private LayoutInflater mInflater;
	
	public ServiceAmendContentAdapter(Context context, List<AmendComment> datas) {
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
		Log.d("zhiheng", "===> getView position = "+position);
		AmendComment data = mDatas.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_service_amend_comment, null);
			holder.commentContent = (TextView) convertView.findViewById(R.id.tv_comment_conent);
			holder.commentPics = (GridView) convertView.findViewById(R.id.gridview_comment_pic);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		holder.commentContent.setText(data.getmComment());
		holder.commentPics.setAdapter(new SimplePicturesAdapter(mContext, data.getmImgUrls()));
		
		holder = (ViewHolder) convertView.getTag();
		return convertView;
	}

	class ViewHolder{
		TextView commentContent;
		GridView commentPics;
	}
}