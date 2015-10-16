package com.baosteel.qcsh.ui.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.baosteel.qcsh.R;
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
public class MyWalletActivity extends BaseActivity implements OnClickListener {

	/** 标题栏 **/
	private TitleBar mTitleBar;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_wallet);

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
		mTitleBar.setTitle("账户钱包");

		//点击事件
		findViewById(R.id.layout_score).setOnClickListener(this);
		findViewById(R.id.layout_balance).setOnClickListener(this);
		findViewById(R.id.layout_gift_card).setOnClickListener(this);
		findViewById(R.id.layout_health).setOnClickListener(this);
		findViewById(R.id.layout_account_balance).setOnClickListener(this);
		
	}


	/**
	 * 初始化数据
	 */
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_account_balance:
			
			//账户余额
			Intent intenta = new Intent(mContext, AccountBalanceActivity.class);
			startActivity(intenta);
			
			break;
		case R.id.layout_health:

			//健康点
			Intent intentH = new Intent(mContext, BindHealthPointActivity.class);
			startActivity(intentH);
			
			break;
		case R.id.layout_gift_card:
			
			//礼品卡
			Intent intentB = new Intent(mContext, MyGiftCardActivity.class);
			startActivity(intentB);
			
			break;
		case R.id.layout_balance:
			
			//优惠券，现金券
			Intent intentF = new Intent(mContext, MyBalanceActivity.class);
			startActivity(intentF);
			
			break;
		case R.id.layout_score:
			
			//我的积分
			Intent intents = new Intent(mContext, MyScoreActivity.class);
			startActivity(intents);
			
			break;
		}
	}
}
