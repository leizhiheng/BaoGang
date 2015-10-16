package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ServiceCommentData;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceCommentAdapter extends BaseAdapter{

	private static final String TAG = "ServiceCommentAdapter";
	private Context mContext;
	private List<ServiceCommentData> mDatas;
	private LayoutInflater mInflater;
	
	public ServiceCommentAdapter(Context context, List<ServiceCommentData> datas) {
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
		ServiceCommentData data = mDatas.get(position);
		Log.d(TAG, "===> getView");
		if (data.getmAmendComments() != null) {
			Log.d(TAG, "===> amend comment size = "+data.getmAmendComments().size());
		}
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_service_comment, null);
			holder.commentUser = (TextView) convertView.findViewById(R.id.tv_comment_user_name);
			holder.commentTime = (TextView) convertView.findViewById(R.id.tv_comment_time);
			holder.commentContent = (TextView) convertView.findViewById(R.id.tv_comment_conent);
			holder.commentPics = (GridView) convertView.findViewById(R.id.gridview_comment_pic);
			holder.ammendComments = (ListView) convertView.findViewById(R.id.listview_amend_comment);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		holder.commentUser.setText(data.getmUserName());
		holder.commentContent.setText(data.getmComment());
		//填充评论中的图片
		holder.commentPics.setAdapter(new SimplePicturesAdapter(mContext, data.getmImgUrls()));
		//显示追加评论
		holder.ammendComments.setAdapter(new ServiceAmendContentAdapter(mContext, data.getmAmendComments()));
		holder = (ViewHolder) convertView.getTag();
		return convertView;
	}

	class ViewHolder{
		TextView commentUser;
		TextView commentTime;
		TextView commentContent;
		GridView commentPics;
		ListView ammendComments;
	}
}
