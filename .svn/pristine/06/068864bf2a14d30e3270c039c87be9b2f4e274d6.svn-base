package com.common.view.banner;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.utils.ImageManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Banner适配器
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-31
 */
public class BannerAdapter extends BaseAdapter {

	/**图片加载参数**/
	private DisplayImageOptions options;
	
	/**上下文**/
	private Context mContext;

	/**Banner数据**/
	private List<BannerData> listDatas;

	/**图片点击回调**/
	private IOnBannerItenClick onItemClick;
	
	public BannerAdapter(Context context, List<BannerData> listDatas) {
		mContext = context;
		options = new DisplayImageOptions.Builder()
		.cacheInMemory(false)
		.cacheOnDisc(true)
		.showStubImage(R.drawable.image_default_square)
		.showImageForEmptyUri(R.drawable.image_default_square)
		.showImageOnFail(R.drawable.image_default_square)
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
		.bitmapConfig(Bitmap.Config.RGB_565)
		//.displayer(new RoundedBitmapDisplayer(0))
		.build();

		this.listDatas = listDatas;
	}

	@Override
	public int getCount() {
		if(null == listDatas || listDatas.size() == 0){
			return 0;
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			ImageView imageView = new ImageView(mContext);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			convertView = imageView;
			viewHolder.imageView = (ImageView) convertView;
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		BannerData bean = listDatas.get(position % listDatas.size());
		// 统一图片加载方式用于省流量处理
		ImageManager.LoadWithServer(bean.getImgUrl(), viewHolder.imageView, options);

		viewHolder.imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				BannerData bean = listDatas.get(position % listDatas.size());
				if(null != onItemClick){
					onItemClick.onBannerItemClick(bean, (position % listDatas.size()));
				}
			}
		});

		return convertView;
	}

	/**
	 * 设置点击回调
	 * @param onItemClick
	 */
	public void setOnItemClickListener(IOnBannerItenClick onItemClick){
		this.onItemClick = onItemClick;
	}
	
	private class ViewHolder {
		ImageView imageView;
	}

}