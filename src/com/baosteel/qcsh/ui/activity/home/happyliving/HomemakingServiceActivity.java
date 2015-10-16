package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.GridView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HousemakingData;
import com.baosteel.qcsh.ui.adapter.HousemakingAdapter;
import com.baosteel.qcsh.ui.fragment.commen.BannerFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.banner.BannerData;
import com.common.view.other.GridViewInScrollView;

/**
 * 
 * @description 页面：乐居宝->家政服务
 * @author blue
 * @Time 2015-9-6
 *
 */
public class HomemakingServiceActivity extends BaseActivity{

	private TitleBar mTitleBar;
//	private BannerFragment mBannerFragment;
	private GridViewInScrollView mGridView;
	
	
	private List<BannerData> mBannerDatas;
	private List<HousemakingData> mHousemakingDatas;
	
	private HousemakingAdapter mHousemakingAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homemaking_service);
		
		initData();
		initView();
		
//		loadBannerData();
		loadHousemakingData();
		
		mTitleBar.setFocusable(true);
		mTitleBar.setFocusableInTouchMode(true);
	}
	
	private void initData() {
		mHousemakingDatas = new ArrayList<HousemakingData>();
		mHousemakingAdapter = new HousemakingAdapter(this, mHousemakingDatas);
	}
	
	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(R.string.title_housemaking_service);
		mTitleBar.setBackgroud(R.color.title_bar_bg_voilet);
		
//		mBannerFragment = (BannerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_banner);
		
		mGridView = (GridViewInScrollView) findViewById(R.id.gridview);
		mGridView.setAdapter(mHousemakingAdapter);
	}
	
	/**
	 * 
	 * @Description 加载Banner数据
	 * @Author blue
	 * @Time 2015-9-1
	 */
	public void loadBannerData() {
		//构造Banner假数据
		mBannerDatas = new ArrayList<BannerData>();
		mBannerDatas.add(new BannerData());
		mBannerDatas.add(new BannerData());
		mBannerDatas.add(new BannerData());
		mBannerDatas.add(new BannerData());
		mBannerDatas.add(new BannerData());
//		mBannerFragment.setData(mBannerDatas);
	}
	
	private void loadHousemakingData() {
		mHousemakingDatas.add(new HousemakingData("保洁", R.drawable.ic_happyliving_baojie, R.drawable.shape_corner_5_happyliving_cleaningl));
		mHousemakingDatas.add(new HousemakingData("鲜花绿植", R.drawable.ic_happyliving_xianhua, R.drawable.shape_corner_5_happyliving_plant));
		mHousemakingDatas.add(new HousemakingData("净水服务", R.drawable.ic_happyliving_jinshui, R.drawable.shape_corner_5_happyliving_water));
		mHousemakingDatas.add(new HousemakingData("保姆月嫂", R.drawable.ic_happyliving_baonv, R.drawable.shape_corner_5_happyliving_nurse));
		mHousemakingAdapter.notifyDataSetChanged();
	}
}
