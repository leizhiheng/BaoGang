package com.baosteel.qcsh.ui.activity.home.happyliving;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;

/**
 * 
 * @description页面：乐居宝-》物业服务-》物业报修-》房屋信息修改
 * @author blue
 * @Time 2015-9-9
 *
 */
public class HouseMessageActivity extends BaseActivity implements OnClickListener {

	private TextView mBtnCommit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house_message);
		initData();
		initView();
	}

	private void initData() {
	}

	private void initView() {
		setTitle("修改房屋信息");
		
		mBtnCommit = (TextView) findViewById(R.id.btn_commit);
		
		mBtnCommit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_commit:
			this.finish();
			break;

		default:
			break;
		}
	}
}