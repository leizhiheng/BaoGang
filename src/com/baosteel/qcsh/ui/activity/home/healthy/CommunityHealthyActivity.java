package com.baosteel.qcsh.ui.activity.home.healthy;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.model.HousemakingData;
import com.baosteel.qcsh.ui.adapter.HousemakingAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.banner.BannerData;
import com.common.view.other.GridViewInScrollView;

/**
 * 
 * @description 页面：健康宝-》社区健康
 * @author blue
 * @Time 2015-9-14
 *
 */
public class CommunityHealthyActivity extends BaseActivity{

	private GridViewInScrollView mGridView;
	
	private List<BannerData> mBannerDatas;
	private List<HousemakingData> mHousemakingDatas;
	
	private HousemakingAdapter mHousemakingAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_community_healthy);
		
		initData();
		initView();
		
		loadHousemakingData();
		
		mTitleBar.setFocusable(true);
		mTitleBar.setFocusableInTouchMode(true);
	}
	
	private void initData() {
		mHousemakingDatas = new ArrayList<HousemakingData>();
		mHousemakingAdapter = new HousemakingAdapter(this, mHousemakingDatas);
	}
	
	private void initView() {
		setTitle("社区健康");
		mTitleBar.setBackgroudColor(Constants.getTypeColor(Constants.TYPE_HEALTH));
		
		mGridView = (GridViewInScrollView) findViewById(R.id.gridview);
		mGridView.setAdapter(mHousemakingAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = null;
				if (position == 0) {
					intent = new Intent(CommunityHealthyActivity.this, CommunityHealthyMsgActivity.class);
					intent.putExtra(EXTRA_KEY_TITLE, mHousemakingDatas.get(position).getTitle());
					intent.putExtra(CommunityHealthyMsgActivity.EXTRA_KEY_MSG_TYPE, CommunityHealthyMsgActivity.MSG_NOTICES);
 				} else if(position == 1) {
 					intent = new Intent(CommunityHealthyActivity.this, CommunityHealthyMsgActivity.class);
					intent.putExtra(EXTRA_KEY_TITLE, mHousemakingDatas.get(position).getTitle());
					intent.putExtra(CommunityHealthyMsgActivity.EXTRA_KEY_MSG_TYPE, CommunityHealthyMsgActivity.MSG_TIPS);
 				
 				} else if (position == 2) {
					intent = new Intent(CommunityHealthyActivity.this, DistinctiveHospitalActivity.class);
				}
				if (intent != null) startActivity(intent);
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
		mHousemakingDatas.add(new HousemakingData("健康服务通知", R.drawable.ic_healthy_comm_notice, R.drawable.shape_corner_5_healthy_notice));
		mHousemakingDatas.add(new HousemakingData("健康小常识", R.drawable.ic_healthy_comm_tips, R.drawable.shape_corner_5_healthy_tip));
		mHousemakingDatas.add(new HousemakingData("特色医院", R.drawable.ic_healthy_comm_hospital, R.drawable.shape_corner_5_healthy_hospital));
		mHousemakingDatas.add(new HousemakingData("健康咨询", R.drawable.ic_healthy_comm_seek, R.drawable.shape_corner_5_healthy_seek));
		mHousemakingAdapter.notifyDataSetChanged();
	}
}