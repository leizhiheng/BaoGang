package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.ViewPagerFragmentAdapter;
import com.common.base.BaseActivity;
import com.common.view.listview.MyListView;
import com.common.view.other.IndexViewPager;
import com.common.view.topbar.HeadView;

/**
 * 汽车保养/维修 Created by kuangyong on 15/9/10.
 */
public class CarMainTainActivity extends BaseActivity implements
		View.OnClickListener, ViewPager.OnPageChangeListener {

	private HeadView headview;// 标题布局
	private android.widget.TextView tvcarname;// 汽车名称及参数
	private android.widget.TextView tvcarmoreinfo;// 汽车更多信息
	private LinearLayout layoutcarinfo;// 汽车信息布局
	private android.widget.TextView tvzzby;// 自助保养
	private android.widget.TextView tvqcwx;// 汽车维修
	private android.widget.TextView tvbytx;// 保养提醒
	private android.widget.TextView tvbyda;// 保养档案
	private com.common.view.other.IndexViewPager vpfragments;
	private List<Fragment> fragments;
	private ViewPagerFragmentAdapter adapter;
	private MainTainZZBYFragment mainTainZZBYFragment;// 自助保养
	private MainTainQCWXFragment mainTainQCWXFragment;// 汽车维修
	private MainTainBYTXFragment mainTainBYTXFragment;// 保养提醒
	private MainTainBYDAFragment mainTainBYDAFragment;// 保养档案


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_maintain);
		findView();
		setListener();
		initView();
		initFragments();
	}

	private void findView() {
		this.headview = (HeadView) findViewById(R.id.headview);
		this.vpfragments = (IndexViewPager) findViewById(R.id.vp_fragments);
		this.tvbyda = (TextView) findViewById(R.id.tv_byda);
		this.tvbytx = (TextView) findViewById(R.id.tv_bytx);
		this.tvqcwx = (TextView) findViewById(R.id.tv_qcwx);
		this.tvzzby = (TextView) findViewById(R.id.tv_zzby);
		this.layoutcarinfo = (LinearLayout) findViewById(R.id.layout_car_info);
		this.tvcarmoreinfo = (TextView) findViewById(R.id.tv_car_more_info);
		this.tvcarname = (TextView) findViewById(R.id.tv_car_name);
	}

	private void setListener() {
		layoutcarinfo.setOnClickListener(this);
		tvzzby.setOnClickListener(this);
		tvqcwx.setOnClickListener(this);
		tvbytx.setOnClickListener(this);
		tvbyda.setOnClickListener(this);
	}

	/**
	 * 初始化 自助保养、汽车维修、保养提醒、保养档案四个页面
	 */
	private void initFragments() {
		mainTainZZBYFragment = new MainTainZZBYFragment();
		mainTainQCWXFragment = new MainTainQCWXFragment();
		mainTainBYTXFragment = new MainTainBYTXFragment();
		mainTainBYDAFragment = new MainTainBYDAFragment();
		fragments = new ArrayList<Fragment>();
		fragments.add(mainTainZZBYFragment);
		fragments.add(mainTainQCWXFragment);
		fragments.add(mainTainBYTXFragment);
		fragments.add(mainTainBYDAFragment);
		adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(),
				fragments);
		vpfragments.setAdapter(adapter);
		vpfragments.setCurrentItem(0);
		vpfragments.setOnPageChangeListener(this);
	}

	private void initView() {
		headview.setTitle("汽车保养/维修");
		headview.setHeadViewBackGroundColor(Color.TRANSPARENT);
		headview.setSeparateLineVisible(View.INVISIBLE);
		headview.setRightImageBtnBackGround(R.drawable.icon_location_white);
		headview.showRightImageBtn(View.VISIBLE, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("定位");
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_byda:// 保养档案
			vpfragments.setCurrentItem(3);
			break;
		case R.id.tv_bytx:// 保养提醒
			vpfragments.setCurrentItem(2);
			break;
		case R.id.tv_qcwx:// 汽车维修
			vpfragments.setCurrentItem(1);
			break;
		case R.id.tv_zzby:// 自助保养
			vpfragments.setCurrentItem(0);
			break;
		case R.id.layout_car_info:// 汽车信息
			startActivity(new Intent(mContext,SelecteCarInfoActivity.class).putExtra(SelecteCarInfoActivity.OPRETATE_TYPE,SelecteCarInfoActivity.OPRETATE_ADD));
			break;
		}
	}

	private void setBtnsBackGround(int index) {
		tvbyda.setBackgroundResource(R.color.white);
		tvbytx.setBackgroundResource(R.color.white);
		tvqcwx.setBackgroundResource(R.color.white);
		tvzzby.setBackgroundResource(R.color.white);
		this.tvbyda.setTextColor(getResources().getColor(R.color.black));
		this.tvbytx.setTextColor(getResources().getColor(R.color.black));
		this.tvqcwx.setTextColor(getResources().getColor(R.color.black));
		this.tvzzby.setTextColor(getResources().getColor(R.color.black));
		switch (index) {
		case 0:
			this.tvzzby.setTextColor(getResources().getColor(
					R.color.green_safetrip));
			tvzzby.setBackgroundResource(R.drawable.common_text_bg_with_greenline);
			break;
		case 1:
			this.tvqcwx.setTextColor(getResources().getColor(
					R.color.green_safetrip));
			tvqcwx.setBackgroundResource(R.drawable.common_text_bg_with_greenline);
			break;
		case 2:
			this.tvbytx.setTextColor(getResources().getColor(
					R.color.green_safetrip));
			tvbytx.setBackgroundResource(R.drawable.common_text_bg_with_greenline);
			break;
		case 3:
			this.tvbyda.setTextColor(getResources().getColor(
					R.color.green_safetrip));
			tvbyda.setBackgroundResource(R.drawable.common_text_bg_with_greenline);
			break;
		}
	}

	@Override
	public void onPageScrolled(int i, float v, int i1) {

	}

	@Override
	public void onPageSelected(int i) {
		setBtnsBackGround(i);
	}

	@Override
	public void onPageScrollStateChanged(int i) {

	}
}
