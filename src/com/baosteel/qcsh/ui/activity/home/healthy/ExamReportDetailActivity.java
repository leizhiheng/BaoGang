package com.baosteel.qcsh.ui.activity.home.healthy;

import android.os.Bundle;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;

public class ExamReportDetailActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exam_report_detail);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
	}
	
	private void initView() {
		setTitle("体检详情");
		mTitleBar.setBackgroud(R.color.theme_color_healthy);
		
	}
	
	private void loadData() {
	}
	
}

