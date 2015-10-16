package com.common.view.fastlink;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.utils.ImageManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * */
public class LnkToolsGridViewAdapter extends BaseAdapter {

	private List<FastLinkData> listDatas;
	public LayoutInflater layoutInflater;



	public LnkToolsGridViewAdapter(Context context, List<FastLinkData> listDatas1, int page) {
		layoutInflater = LayoutInflater.from(context);
		listDatas = listDatas1;
	}

	public LnkToolsGridViewAdapter(Context context) {
		layoutInflater = LayoutInflater.from(context);
		listDatas = new ArrayList<FastLinkData>();
	}

	@Override
	public int getCount() {
		if (null == listDatas) {
			return 0;
		}
		return listDatas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listDatas.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		holder = new ViewHolder();
		convertView = layoutInflater.inflate(R.layout.adapter_lnktools_item, null);
		holder.name = (TextView) convertView.findViewById(R.id.name);
		holder.image = (ImageView) convertView.findViewById(R.id.image);

		FastLinkData bean = null;
		bean = listDatas.get(position);
		holder.name.setText(bean.getName());
		
		if(!TextUtils.isEmpty(bean.getImageUrl())){
			ImageManager.Load(bean.getImageUrl(), holder.image);
		}else{
			holder.image.setImageResource(bean.getImgId());
		}
		convertView.setOnClickListener(new MyClickListener(bean));

		return convertView;
	}

	/**
	 * class ViewHolder
	 */
	private class ViewHolder {
		TextView name;
		ImageView image;
	}

	public interface BackItemClickListener {
		void onFastLinkItemClick(FastLinkData bean);
	}

	BackItemClickListener backItemClickListener;

	public void setBackItemClickListener(BackItemClickListener backItemClickListener) {
		this.backItemClickListener = backItemClickListener;
	}

	class MyClickListener implements View.OnClickListener {
		FastLinkData bean;

		public MyClickListener(FastLinkData bean) {
			this.bean = bean;
		}

		@Override
		public void onClick(View v) {
			backItemClickListener.onFastLinkItemClick(bean);
		}

	}

}
