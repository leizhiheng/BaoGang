package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.fragment.commen.BannerFragment;
import com.baosteel.qcsh.ui.fragment.commen.ScrollLayoutFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.banner.BannerData;
import com.common.view.fastlink.FastLinkData;
import com.common.view.fastlink.LnkToolsGridViewAdapter.BackItemClickListener;

/**
 * 
 * @description 对应：乐居宝->物业服务 页面
 * @author blue
 * @Time time2015-9-1
 * 
 */
public class PropertyServiceActivity extends BaseActivity {

	private TitleBar mTitleBar;
	/** 分类列表 */
	private ScrollLayoutFragment mScrollLayoutFragment;
	/** Banner图 */
	private BannerFragment mBannerFragment;

	/** 小区Logo和Name */
	private View mLayoutHousingMsg;
	/** 小区联系方式 */
	private View mLayoutHousingPhone;

	/** Banner图数据 */
	private List<BannerData> mBannerDatas;
	/** 分类数据 */
	private List<FastLinkData> mFastLinkDatas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_property_service);

		initView();

		loadBannerData();
		loadFastLinkData();
	}

	private void initView() {

		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mScrollLayoutFragment = (ScrollLayoutFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_linkList);
		mBannerFragment = (BannerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_banner);

		mTitleBar.setTitle("物业服务");

		mScrollLayoutFragment.setPageSize(6);
		mScrollLayoutFragment.setFastLickItemClickListener(new BackItemClickListener() {

			@Override
			public void onFastLinkItemClick(FastLinkData bean) {
				int id = bean.getChannelId();
				Class clazz = null;
				switch (id) {
				case 1:
					clazz = HousingEstateNoticesActivity.class;
					break;
				case 2:
					clazz = HousingEstateShouldKnowActivity.class;
					break;
				case 3:
					clazz = ReserveServiceActivity.class;
					break;
				case 4:
					clazz = OftenUsedPhoneActivity.class;
					break;
				case 5:
					clazz = RepairReportActivity.class;
					break;
				case 6:
					clazz = MyPropertiesActivity.class;
					break;

				default:
					break;
				}

				startActivity(new Intent(PropertyServiceActivity.this,clazz));
			}
		});

	}

	/**
	 * 
	 * @Description 加载Banner数据
	 * @Author blue
	 * @Time 2015-9-1
	 */
	public void loadBannerData() {
		
		mBannerFragment.loadBannerData(13, 2);
	}

	/**
	 * 加载分类数据
	 */
	public void loadFastLinkData() {

		// 构造FastLink假数据
		mFastLinkDatas = new ArrayList<FastLinkData>();
		mFastLinkDatas.add(new FastLinkData(1, "小区通知", R.drawable.ic_happyliving_notice));
		mFastLinkDatas.add(new FastLinkData(2, "小区须知", R.drawable.ic_happyliving_should_know));
		mFastLinkDatas.add(new FastLinkData(3, "预约服务", R.drawable.ic_happyliving_reserve_service));
		mFastLinkDatas.add(new FastLinkData(4, "常用电话", R.drawable.ic_happyliving_often_phone));
		mFastLinkDatas.add(new FastLinkData(5, "物业报修", R.drawable.ic_happyliving_prepair));
		mFastLinkDatas.add(new FastLinkData(6, "我的物业", R.drawable.ic_happyliving_my_property));
		mScrollLayoutFragment.setData(mFastLinkDatas);
	}

}
