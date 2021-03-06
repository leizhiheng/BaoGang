package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.Brand;
import com.baosteel.qcsh.utils.ImageManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * 品牌推荐适配器 
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-2
 */
public class HomeBrandAdapter extends BaseAdapter {

	/**品牌数据**/
	private List<Brand> dataList;
	
	/**View加载器**/
	public LayoutInflater layoutInflater;

	public HomeBrandAdapter(Context context, List<Brand> dataList) {
		layoutInflater = LayoutInflater.from(context);
		this.dataList = dataList;
	}

	public HomeBrandAdapter(Context context) {
		layoutInflater = LayoutInflater.from(context);
		dataList = new ArrayList<Brand>();
	}

	@Override
	public int getCount() {
		if (null == dataList) {
			return 0;
		}
		return dataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return dataList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	private DisplayImageOptions options;
	private DisplayImageOptions getOption(){
		if(null == options){
			options = new DisplayImageOptions.Builder().showStubImage(R.drawable.image_default_square)
					.showImageForEmptyUri(R.drawable.image_default_square)
					.showImageOnFail(R.drawable.image_default_square)
					.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
					.cacheInMemory(true).cacheOnDisc(true)
					.bitmapConfig(Bitmap.Config.RGB_565).build();
		}
		return options;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		holder = new ViewHolder();
		convertView = layoutInflater.inflate(R.layout.adapter_brand_item, null);
		holder.image = (ImageView) convertView.findViewById(R.id.image);

		Brand bean = null;
		bean = dataList.get(position);
		Log.d("zhiheng", "image url = "+bean.getBrandLogo());
		
		//加载图片
		ImageManager.Load(bean.getBrandLogo(), holder.image, getOption());
		convertView.setOnClickListener(new MyClickListener(bean));

		return convertView;
	}

	/**
	 * class ViewHolder
	 */
	private class ViewHolder {
		
		ImageView image;
	}

	public interface BrandItemClickListener {
		void onBrandItemClick(Brand bean);
	}

	BrandItemClickListener backItemClickListener;

	public void setBackItemClickListener(BrandItemClickListener backItemClickListener) {
		this.backItemClickListener = backItemClickListener;
	}

	class MyClickListener implements View.OnClickListener {
		Brand bean;

		public MyClickListener(Brand bean) {
			this.bean = bean;
		}

		@Override
		public void onClick(View v) {
			backItemClickListener.onBrandItemClick(bean);
		}

	}

}
