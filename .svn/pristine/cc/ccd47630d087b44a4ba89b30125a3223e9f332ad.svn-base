package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.URLs;
import com.baosteel.qcsh.model.ProductMaybeLike;
import com.baosteel.qcsh.utils.ImageManager;
import com.common.utils.MathUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class GoodsDetailLikeGridViewAdapter extends BaseAdapter {

	private Context mContext;

	private List<ProductMaybeLike> listDatas;
	public LayoutInflater layoutInflater;

	private DisplayImageOptions options;
	private ImageLoader imageLoader;

	public GoodsDetailLikeGridViewAdapter(Context context,
			List<ProductMaybeLike> listDatas) {
		layoutInflater = LayoutInflater.from(context);
		mContext = context;

		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true)
				.showStubImage(R.drawable.image_default_square)
				.showImageForEmptyUri(R.drawable.image_default_square)
				.showImageOnFail(R.drawable.image_default_square)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		imageLoader = ImageLoader.getInstance();

		this.listDatas = listDatas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return null != listDatas ? listDatas.size() : 0;
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
        ProductMaybeLike bean = listDatas.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(
					R.layout.fragment_goods_detail_like_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.price = (TextView) convertView.findViewById(R.id.price);
			holder.buy_count = (TextView) convertView.findViewById(R.id.buy_count);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(bean.getName());
		holder.buy_count.setText(bean.getSaleCount()+"人购买");
		holder.price.setText(MathUtil.priceForAppWithSign(bean.getPrice()));
        //统一图片获取方式用于省流量设置
        ImageManager.LoadWithServer(bean.getImg(), "", holder.image, options);
		return convertView;
	}

	private class ViewHolder {
		ImageView image;
		TextView name;
		TextView price;
		TextView buy_count;
	}

}