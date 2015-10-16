package com.baosteel.qcsh.ui.activity.my;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.Balance;
import com.baosteel.qcsh.model.Balance;
import com.baosteel.qcsh.ui.adapter.BalanceAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 现金券
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class MyBalanceActivity extends BaseActivity implements OnClickListener {

	/** 标题栏 **/
	private TitleBar mTitleBar;

	/** 已激活 **/
	private TextView activeTv;

	/** 未激活 **/
	private TextView unActiveTv;

	/** 现金券集合 **/
	private ListView dataListView;

	/** 适配器 **/
	private BalanceAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_balance);

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
		mTitleBar.setTitle("现金券");

		// 未激活布局，激活布局
		activeTv = (TextView) findViewById(R.id.activeTv);
		unActiveTv = (TextView) findViewById(R.id.unActiveTv);
		activeTv.setOnClickListener(this);
		unActiveTv.setOnClickListener(this);

		// 默认显示单程
		showlWay(true);

		// 初始化适配器
		adapter = new BalanceAdapter(mContext, getData());
		dataListView = (ListView) findViewById(R.id.dataListView);
		dataListView.setAdapter(adapter);
	}

	/**
	 * 构造现金券数据
	 * 
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
		case R.id.unActiveTv:

			// 未激活
			showlWay(true);

			break;
		case R.id.activeTv:

			// 激活
			showlWay(false);

			break;
		}
	}

	/**
	 * 显示单程，或往返
	 * 
	 * @param showUnAtive
	 */
	private void showlWay(boolean showUnAtive) {
		if (showUnAtive) {

			// 未激活
			unActiveTv.setBackgroundResource(R.drawable.common_leftradius_red_on);
			activeTv.setBackgroundResource(R.drawable.common_rightradius_red_in);
			unActiveTv.setTextColor(getResources().getColor(R.color.index_red));
			activeTv.setTextColor(getResources().getColor(R.color.white));
		} else {

			// 激活
			unActiveTv.setBackgroundResource(R.drawable.common_leftradius_red_in);
			activeTv.setBackgroundResource(R.drawable.common_rightradius_red_on);
			unActiveTv.setTextColor(getResources().getColor(R.color.white));
			activeTv.setTextColor(getResources().getColor(R.color.orange));

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
