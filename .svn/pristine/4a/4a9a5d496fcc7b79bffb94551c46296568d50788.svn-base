package com.baosteel.qcsh.ui.activity.home.happyliving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.activity.common.TabPagerActivity;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshGridView;

/**
 * 
 * @description 页面：乐居宝->物业服务->我的物业
 * @author blue
 * @Time 2015-9-7
 * 
 */
public class MyPropertiesActivity extends BaseActivity implements
		OnClickListener {

	private TitleBar mTitleBar;
	private PullToRefreshGridView mGridView;

	/**
	 * 点击选择小区
	 */
	private View mViewHouseEstate;
	/**
	 * 点击设置房屋信息
	 */
	private View mViewHouseMessage;
	/**
	 * 点击查看预约记录
	 */
	private View mViewReserveMessage;
	/**
	 * 点击查看报修记录
	 */
	private View mViewRepairReport;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_properties);

		initData();
		initView();

		loadData();

	}

	private void initData() {
	}

	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle("我的物业");

		mViewHouseEstate = findViewById(R.id.layout_house_estate);
		mViewHouseMessage = findViewById(R.id.layout_house_message);
		mViewReserveMessage = findViewById(R.id.layout_reserve_message);
		mViewRepairReport = findViewById(R.id.layout_repair_report_message);
		
		mViewHouseEstate.setOnClickListener(this);
		mViewHouseMessage.setOnClickListener(this);
		mViewRepairReport.setOnClickListener(this);
		mViewReserveMessage.setOnClickListener(this);

	}

	private void loadData() {
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.layout_house_estate:
			intent = new Intent(this, HousingEstateMessageActivity.class);
			intent.putExtra(HousingEstateMessageActivity.INTENT_KEY_ACTION, HousingEstateMessageActivity.INTENT_ACTION_MODIFY);
			startActivity(intent);
			break;
		case R.id.layout_house_message:
			intent = new Intent(this, HouseMessageActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_reserve_message:
			intent = new Intent(this, MyReserviServiceActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_repair_report_message:
			intent = new Intent(this, ReportRepairListActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
