package com.baosteel.qcsh.ui.activity.my;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.Balance;
import com.baosteel.qcsh.model.GiftCard;
import com.baosteel.qcsh.ui.adapter.AccountBalanceAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 我的健康点-修改密码
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class MyHealthUpdatePsdActivity extends BaseActivity implements OnClickListener {

	/** 标题栏 **/
	private TitleBar mTitleBar;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_update_password);

		// 初始化控件
		initView();

		// 初始化数据
		initData();
	}

	/**
	 * 初始化数据
	 */
	public void initView() {

		// 标题栏
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setRightIconVisibility(0, View.VISIBLE);
		mTitleBar.setBackgroudValue(getResources().getColor(R.color.index_red));
		mTitleBar.setTitle("健康点修改密码");

		//修改密码，解除绑定
		findViewById(R.id.submit_button).setOnClickListener(this);
		
	}

	/**
	 * 初始化数据
	 */
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit_button:
			
			// 确认提交
			showToast("修改成功");
			finish();
			
			break;
		}
	}
}
