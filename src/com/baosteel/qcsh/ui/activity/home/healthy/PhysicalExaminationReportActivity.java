package com.baosteel.qcsh.ui.activity.home.healthy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.common.base.BaseActivity;

/**
 * 
 * @description 页面：健康宝-》体检报告
 * @author blue
 * @Time 2015-9-14
 *
 */
public class PhysicalExaminationReportActivity extends BaseActivity implements OnClickListener{

	private TextView mBtnCommit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_physical_examination_report);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		
	}
	
	private void initView() {
		setTitle("体检报告");
		mTitleBar.setBackgroudColor(Constants.getTypeColor(Constants.TYPE_HEALTH));
		
		mBtnCommit = (TextView) findViewById(R.id.btn_query);
		
		mBtnCommit.setOnClickListener(this);
	}
	
	private void loadData() {
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_query:
			startActivity(new Intent(this, PhysicalExamReportListActivity.class));
			break;

		default:
			break;
		}
	}
}
