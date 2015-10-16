package com.baosteel.qcsh.ui.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.ui.activity.CommonWebPageActivity;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 我的钱包
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class MyScoreActivity extends BaseActivity implements OnClickListener {

	/** 标题栏 **/
	private TitleBar mTitleBar;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_score);

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
		mTitleBar.setTitle("我的积分");

		//积分说明
		findViewById(R.id.levelTipTv).setOnClickListener(this);
		
	}


	/**
	 * 初始化数据
	 */
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.levelTipTv:
			
			//积分说明
			Intent i = new Intent();
			i.setClass(mContext, CommonWebPageActivity.class);
			i.putExtra(CommonWebPageActivity.Title, "积分说明");
			i.putExtra(CommonWebPageActivity.Content_Type, ConstantsAPI.Content_Type_3);
			startActivity(i);
			
			break;
		}
	}
}
