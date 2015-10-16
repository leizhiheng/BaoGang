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
 * 账户余额
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class AccountBalanceActivity extends BaseActivity implements OnClickListener {

	/** 标题栏 **/
	private TitleBar mTitleBar;

	/**充值**/
	private Button rechargeMoneyBtn;
	
	/**礼品卡集合**/
	private ListView dataListView;
	
	/**适配器**/
	private AccountBalanceAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_account_balance);

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
		mTitleBar.setTitle("账户余额");

		//充值
		rechargeMoneyBtn = (Button)findViewById(R.id.rechargeMoneyBtn);
		rechargeMoneyBtn.setOnClickListener(this);
		
		//初始化适配器
		adapter = new AccountBalanceAdapter(mContext, getData());
		dataListView = (ListView)findViewById(R.id.dataListView);
		dataListView.setAdapter(adapter);
	}

	/**
	 * 构造礼品卡数据
	 * @return
	 */
	private List<Balance> getData() {
		List<Balance> list = new ArrayList<Balance>();
		list.add(new Balance());
		list.add(new Balance());
		list.add(new Balance());
		list.add(new Balance());
		list.add(new Balance());
		list.add(new Balance());
		list.add(new Balance());
		list.add(new Balance());
		list.add(new Balance());
		list.add(new Balance());
		list.add(new Balance());
		return list;
	}

	/**
	 * 初始化数据
	 */
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rechargeMoneyBtn:
			
			// 充值
			
			break;
		}
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}

		switch (requestCode) {
		}
	}
}
