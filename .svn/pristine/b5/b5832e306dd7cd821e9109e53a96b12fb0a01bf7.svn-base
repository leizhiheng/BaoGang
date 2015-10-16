package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.utils.ImageManager;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.utils.LogUtil;
import com.common.view.photoview.PhotoViewActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class GoodsDetailGalleryAdapter extends PagerAdapter {

	@SuppressLint("UseSparseArrays")
	private Map<Integer, View> views = new HashMap<Integer, View>();
	private ArrayList<String> listDatas;
	private Activity activity;
	private boolean isQuota = false;
	private DisplayImageOptions options;

	public GoodsDetailGalleryAdapter(List<String> listDatas, Activity activity) {
		this.listDatas = (ArrayList<String>) listDatas;
		this.activity = activity;
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true)
				.showStubImage(R.drawable.image_default_rectangle)
				.showImageForEmptyUri(R.drawable.image_default_rectangle)
				.showImageOnFail(R.drawable.image_default_rectangle)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
		views.remove(position);
	}

	@Override
	public int getCount() {
		if (listDatas != null) {
			return listDatas.size();
		}
		return 0;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v = LayoutInflater.from(activity).inflate(
				R.layout.fragment_goods_detail_baseinfo_top_image, null);
		ImageView mImageView = (ImageView) v.findViewById(R.id.main_image);
//		ImageView xiangou = (ImageView) v.findViewById(R.id.xiangou);
//		xiangou.setVisibility(isQuota?View.VISIBLE:View.INVISIBLE);
//		xiangou.setVisibility(View.INVISIBLE);//功能暂时去掉，隐藏
		String imageUrl = listDatas.get(position);

        //统一图片获取方式用于省流量设置
        ImageManager.LoadWithServer(imageUrl , mImageView, options);

        mImageView.setTag(position);
        mImageView.setOnClickListener(new MyClick());
//
		views.put(position, v);
		container.addView(v);
		return v;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	public void setQuota(boolean isQuota) {
		this.isQuota = isQuota;
	}

	
	private int pageCurrentIndex = 0;
	public void setCurrentIndex(int index){
		this.pageCurrentIndex = index;
	}
	
	class MyClick implements OnClickListener{

		
		@Override
		public void onClick(View v) {
			
			Intent intent = new Intent(activity, PhotoViewActivity.class);
			
			int index = Integer.parseInt(v.getTag().toString().trim());
			
			//大图用720x720的图加载
			ArrayList<String> newUrls = StringUtils.getImageUrls(listDatas, "");//TODO 暂时去掉尺寸后缀 720X720
			intent.putStringArrayListExtra(PhotoViewActivity.INTENT_KEY_PHOTO, newUrls);
			//intent.putStringArrayListExtra(PhotoViewActivity.INTENT_KEY_PHOTO, listDatas);
			intent.putExtra(PhotoViewActivity.INTENT_KEY_POSITION, pageCurrentIndex);
			LogUtil.i("PhotoView", "in position:" + pageCurrentIndex);
			activity.startActivity(intent);
			
		}
		
	}
}
