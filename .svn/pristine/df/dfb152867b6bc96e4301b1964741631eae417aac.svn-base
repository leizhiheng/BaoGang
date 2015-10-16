package com.baosteel.qcsh.ui.activity.my.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.activity.cart.ConsigneeInfoActivity;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseCameraActivity;

/**
 * 常用信息
 * 
 * @author 刘远祺
 * 
 */
public class CommonInfoActivity extends BaseCameraActivity implements OnClickListener {

	/** 标题栏 **/
	private TitleBar mTitleBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_info);

		// 初始化控件
		initView();

		// 初始化数据
		initData();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {

		// 标题栏
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setRightIconVisibility(0, View.VISIBLE);
		mTitleBar.setBackgroudValue(getResources().getColor(R.color.index_red));
		mTitleBar.setTitle("我的常用信息");
		
		findViewById(R.id.receiverLayout).setOnClickListener(this);
		findViewById(R.id.userinfoLayout).setOnClickListener(this);
		findViewById(R.id.ticketLayout).setOnClickListener(this);
		findViewById(R.id.houseLayout).setOnClickListener(this);
		findViewById(R.id.carLayout).setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.receiverLayout:
			
			//常用收货人
			startActivity(new Intent(getBaseContext(), ConsigneeInfoActivity.class));
			
			break;
		case R.id.userinfoLayout:
			
			//常用人员信息
			startActivity(new Intent(getBaseContext(), CommonPersionListActivity.class));
			
			break;
		case R.id.ticketLayout:
			
			//常用发票抬头
			startActivity(new Intent(getBaseContext(), CommonTicketListActivity.class));
			
			break;
		case R.id.houseLayout:
			
			//常用房屋信息
			startActivity(new Intent(getBaseContext(), CommonHouseListActivity.class));
			
			break;
		case R.id.carLayout:
			
			//常用车辆信息
			startActivity(new Intent(getBaseContext(), CommonCarListActivity.class));
			
			break;
		}
	}
}
