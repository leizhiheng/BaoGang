package com.baosteel.qcsh.ui.activity.home.healthy;

import android.os.Bundle;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.common.base.BaseActivity;

public class DistinctiveHospitalDetailActivity extends BaseActivity{
	
	private String mTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distinctive_hospital_detail);
		
		initData();
		initView();
	}
	
	private void initData() {
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
	}
	
	private void initView() {
		setTitle("社区健康");
		mTitleBar.setBackgroudColor(Constants.getTypeColor(Constants.TYPE_HEALTH));
	}
}