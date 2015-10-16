package com.baosteel.qcsh.ui.activity.home.healthy;

import android.os.Bundle;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.common.base.BaseActivity;

/**
 * 
 * @description 页面：健康宝-》特需服务
 * @author blue
 * @Time 2015-9-14
 *
 */
public class SpecialServiceActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_special_service);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		
	}
	
	private void initView() {
		setTitle("特需服务");
		mTitleBar.setBackgroudColor(Constants.getTypeColor(Constants.TYPE_HEALTH));
	}
	
	private void loadData() {
		
	}
}
