package com.baosteel.qcsh.ui.activity.home.happyliving;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.dialog.ChooseAreaPopWindow;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 
 * @description 页面：乐居宝->便捷缴费->电费（话费...）
 * @author blue
 * @Time 2015-9-8
 * 
 */
public class NimblePaymentEntryActivity extends BaseActivity implements
		OnClickListener {

	private View mRootView;
	/**
	 * 所在城市
	 */
	private View mViewChooseCity;
	private TextView mTvCity;
	
	/**
	 * 缴费单位
	 */
	private View mViewChooseDepart;
	private TextView mTvDepart;
	
	/**
	 * 缴费服务协议
	 */
	private TextView mBtnProtol;
	
	/**
	 * 下一步
	 */
	private TextView mBtnNext;

	public static final String EXTRA_KEY_TITLE = "extra.title";
	public static final String EXTRA_KEY_INDEX = "extra.index";

	/** 缴纳电费标志位 */
	public static final int INDEX_POWER_COST = 0;
	/** 缴纳水费标志位 */
	public static final int INDEX_WATER_COST = 1;
	/** 缴纳燃气费标志位 */
	public static final int INDEX_GAS_COST = 2;
	/** 缴纳固话宽带标志位 */
	public static final int INDEX_BROADBAND_COST = 3;
	/** 缴纳有线电视标志位 */
	public static final int INDEX_TV_COST = 4;

	private int mPageIndex;
	private String mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nimble_payment_entry);

		initData();
		initView();
	}

	private void initData() {
		mPageIndex = getIntent().getIntExtra(EXTRA_KEY_INDEX, INDEX_POWER_COST);
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
	}

	private void initView() {
		setTitle(mTitle);

		mRootView = findViewById(R.id.root_view);
		mViewChooseCity = findViewById(R.id.layout_choose_city);
		mViewChooseDepart = findViewById(R.id.layout_choose_depart);
		mTvCity = (TextView) findViewById(R.id.tv_city_name);
		mTvDepart = (TextView) findViewById(R.id.tv_depart_name);
		mBtnNext = (TextView) findViewById(R.id.btn_next);
		mBtnProtol = (TextView) findViewById(R.id.btn_payment_protol);

		mViewChooseCity.setOnClickListener(this);
		mViewChooseDepart.setOnClickListener(this);
		mBtnNext.setOnClickListener(this);
		mBtnProtol.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.layout_choose_city:
			chooseAddress();
			break;

		case R.id.layout_choose_depart:
			intent = new Intent(this, PaymentCompanyActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.btn_next:
			intent = new Intent(this, PaymentDetailActivity.class);
			intent.putExtra(PaymentDetailActivity.EXTRA_KEY_PAY_TYPE, PaymentDetailActivity.PAY_TYPE_OTHER);
			intent.putExtra(PaymentDetailActivity.EXTRA_KEY_PAYMENT_NAME, mTitle);
			startActivity(intent);
			break;
		case R.id.btn_payment_protol:
			intent = new Intent(this, NoticeDetailActivity.class);
			intent.putExtra(EXTRA_KEY_TITLE, NoticeDetailActivity.TITLE_PAYMENT_PROTOL);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	
	private ChooseAreaPopWindow mAreaChoosePopwindow;
	private void chooseAddress() {
		if (mAreaChoosePopwindow == null) {
			mAreaChoosePopwindow = new ChooseAreaPopWindow(this, new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (v.getId() == R.id.btn_ok) {
						showToast("select area finished");
						if (mAreaChoosePopwindow != null && mAreaChoosePopwindow.isShowing()) {
							mAreaChoosePopwindow.dismiss();
						}
						
						StringBuffer buffer = new StringBuffer();
						buffer.append(mAreaChoosePopwindow.getmCurrentProviceName());
						if (mAreaChoosePopwindow.getmCurrentCityName().trim().length() > 0) {
							buffer.append("  -  " + mAreaChoosePopwindow.getmCurrentCityName());
						}
						if (mAreaChoosePopwindow.getmCurrentAreaName().trim().length() > 0) {
							buffer.append("  -  " + mAreaChoosePopwindow.getmCurrentAreaName());
						}
						mTvCity.setText(buffer.toString());
					}
				}
			});
		}
		
		mAreaChoosePopwindow.showAtLocation(mRootView, Gravity.BOTTOM, 0, 0);
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		if (arg2 != null) {
			mTvDepart.setText(arg2.getStringExtra(EXTRA_KEY_RESULT));
		}
	}
}
