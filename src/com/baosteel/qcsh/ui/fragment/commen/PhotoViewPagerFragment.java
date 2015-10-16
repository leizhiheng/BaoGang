package com.baosteel.qcsh.ui.fragment.commen;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.PictureData;
import com.baosteel.qcsh.ui.adapter.PhotoPagerAdapter;
import com.baosteel.qcsh.utils.ImageManager;
import com.common.base.BaseFragment;

/**
 * 
 * @description 这个Fragmnent封装了一个ViewPager，用于展示商品的图片详情
 * @author blue
 * @Time 2015-9-7
 *
 */
public class PhotoViewPagerFragment extends BaseFragment{

	private ViewPager mViewPager;
	private TextView mTvIndex;
	private PhotoPagerAdapter mPagerAdapter;
	/**
	 * 要显示的图片数据，外部只需要将这个数据集合传递进来即可
	 */
	private List<PictureData> mPictureDatas;
	
	/**
	 * ViewPager显示的View集合
	 */
	private List<View> mViews;
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_photo_viewpager, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initData();
		findView();
		
		loadData();
	}
	
	private void initData() {
		mViews = new ArrayList<View>();
		mPagerAdapter = new PhotoPagerAdapter(mViews);
	}
	
	private void findView(){
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mTvIndex = (TextView) findViewById(R.id.index);
		mViewPager.setAdapter(mPagerAdapter);
		mPagerAdapter.notifyDataSetChanged();
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				mTvIndex.setText((arg0 + 1) + "/" + mPictureDatas.size());
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}
	
	/**
	 * 
	 * @Description 下载图片
	 * @Author blue
	 * @Time 2015-9-7
	 */
	private void  loadData() {
		
		for (int i = 0; i < mPictureDatas.size(); i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			imageView.setScaleType(ScaleType.CENTER_CROP);
			if (mPictureDatas.get(i).getUrl() != null) {
				ImageManager.Load(mPictureDatas.get(i).getUrl(), imageView);
			} else {
				imageView.setImageResource(mPictureDatas.get(i).getResId());
			}
			mViews.add(imageView);
		}
		mPagerAdapter.notifyDataSetChanged();
	}
	
	/**
	 * 
	 * @Description 显示的图片对象集合 由外部设置
	 * @Author blue
	 * @Time 2015-9-7
	 */
	public void setDatas(List<PictureData> datas) {
		this.mPictureDatas = datas;
		
	}
	
	/**
	 * 
	 * @Description 刷新图片列表
	 * @Author blue
	 * @Time 2015-9-7
	 */
	public void notifyDataSetChanged() {
		if (mPagerAdapter != null) {
			mPagerAdapter.notifyDataSetChanged();
		}
	}
}
