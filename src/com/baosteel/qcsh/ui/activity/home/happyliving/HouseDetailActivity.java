package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.PictureData;
import com.baosteel.qcsh.ui.fragment.commen.PhotoViewPagerFragment;
import com.baosteel.qcsh.ui.view.RadioGroupBar;
import com.baosteel.qcsh.ui.view.RadioGroupBar.OnTabSelectedListener;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

public class HouseDetailActivity extends BaseActivity{


	private TitleBar mTitleBar;
	private PhotoViewPagerFragment mPhotoViewPagerFragment;
	
	/**
	 * 要显示的图片数据，外部只需要将这个数据集合传递进来即可
	 */
	private List<PictureData> mPictureDatas;
	
	private RadioGroupBar mRadioGroupBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house_detail);

		initData();
		initView();

		loadData();

	}

	private void initData() {
		mPictureDatas = new ArrayList<PictureData>();
	}

	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle("详情");
		mTitleBar.setFocusable(true);
		mTitleBar.setFocusableInTouchMode(true);

		mPhotoViewPagerFragment = (PhotoViewPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewpager);
		
		/*
		 * 底部选项卡
		 */
		mRadioGroupBar = (RadioGroupBar) findViewById(R.id.radio_group_bar);
		initRadioGroupBar();
	}
	
	private void initRadioGroupBar() {
		mRadioGroupBar.setTabNum(4);//设置选项卡数量
		mRadioGroupBar.setTabBgColor(getResources().getColor(R.color.black), getResources().getColor(R.color.black_light));//设置选项卡背景色
		mRadioGroupBar.setTabTitle(0, "张三（房东）");//设置选项卡0的title
		mRadioGroupBar.setTabContent(0, "151****");//设置选项卡的context
		mRadioGroupBar.setTabContent(1, "电话");//设置选项卡的context
		mRadioGroupBar.setTabContent(2, "短信");//设置选项卡的context
		mRadioGroupBar.setTabContent(3, "语音");//设置选项卡的context
		
		mRadioGroupBar.setTabIcon(1, R.drawable.icon_home_on, R.drawable.icon_home_in);
		mRadioGroupBar.setTabIcon(2, R.drawable.icon_home_on, R.drawable.icon_home_in);
		mRadioGroupBar.setTabIcon(3, R.drawable.icon_home_on, R.drawable.icon_home_in);
		
		mRadioGroupBar.setOnTabSelectedListener(new OnTabSelectedListener() {
			
			@Override
			public void onTabSeleted(int tabIndex) {
				showToast("Tab "+ tabIndex+" has been clicked");
			}
		});
		
		mRadioGroupBar.setTabSelected(0);
	}

	private void loadData() {
		String imgUrl = "http://lehumall.b0.upaiyun.com//upload/image/admin/2015/20150824/201508242224519073.jpg";

		for (int i = 0; i < 5; i++) {
			mPictureDatas.add(new PictureData(imgUrl, 0));
		}
		mPhotoViewPagerFragment.setDatas(mPictureDatas);
	}
}
