package com.baosteel.qcsh.ui.activity.home.healthy;

import android.os.Bundle;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.fragment.home.healthy.CommitBedReserveOrderFragment;
import com.baosteel.qcsh.ui.fragment.home.healthy.CommitPhysicalExamOrderFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 
 * @description 页面：健康宝-》体检预定-》商品详情-》提交订单
 * @author blue
 * @Time 2015-9-16
 *
 */
public class CommitOrderActivity extends BaseActivity{

	public static final String EXTRA_ORDER_TYPE = "order.type";
	/**
	 * 提交体检套餐预订单
	 */
	public static final int ORDER_PHYSICAL_EXAM = 0;
	/**
	 * 提交养老现床预订单
	 */
	public static final int ORDER_READY_BED = 1;
	/**
	 * 提交养老期床预定单
	 */
	public static final int ORDER_FUTURE_BED = 2;
	
	private int mOrderType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commit_order);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mOrderType = getIntent().getIntExtra(EXTRA_ORDER_TYPE, ORDER_PHYSICAL_EXAM);
	}
	
	private void initView() {
		setTitle("提交订单");
		mTitleBar.setBackgroud(R.color.theme_color_healthy);
		mTitleBar.showRightIcon(TitleBar.ICON_INDEX_MENU);
		
		if (mOrderType == ORDER_PHYSICAL_EXAM) {
			getSupportFragmentManager().beginTransaction().replace(R.id.container, new CommitPhysicalExamOrderFragment()).commit();
		} else {
			getSupportFragmentManager().beginTransaction().replace(R.id.container, new CommitBedReserveOrderFragment()).commit();
		}
	}
	
	private void loadData() {
		
	}
}
