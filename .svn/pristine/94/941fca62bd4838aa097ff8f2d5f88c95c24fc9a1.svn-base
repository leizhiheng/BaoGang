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

public class NimblePaymentPropertyActivity extends BaseActivity implements OnClickListener{

	private View mRootView;
	private View mViewChooseCity;
	private TextView mTvCity;
	
	private TextView mBtnProtol;
	
	private TextView mBtnNext;
	
	/**
	 * 选择所在城市的PopWindow
	 */
	private ChooseAreaPopWindow mAreaChoosePopwindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nimble_payment_property);
		initData();
		initView();
	}

	private void initData() {
	}

	private void initView() {
		mRootView = findViewById(R.id.root_view);
		setTitle("物业费");
		
		mViewChooseCity = findViewById(R.id.layout_choose_city);
		mTvCity = (TextView) findViewById(R.id.tv_city);
		mBtnNext = (TextView) findViewById(R.id.btn_next);
		mBtnProtol = (TextView) findViewById(R.id.btn_payment_protol);
		
		mViewChooseCity.setOnClickListener(this);
		mBtnNext.setOnClickListener(this);
		mBtnProtol.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_choose_city:
			chooseCity();
			showToast("choose area");
			break;
		case R.id.btn_next:
			Intent intent = new Intent(this, PaymentDetailActivity.class);
			intent.putExtra(PaymentDetailActivity.EXTRA_KEY_PAY_TYPE, PaymentDetailActivity.PAY_TYPE_PROPERTY);
			intent.putExtra(PaymentDetailActivity.EXTRA_KEY_PAYMENT_NAME, "物业费");
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
	
	/**
	 * 
	 * @Description 选择所在城市
	 * @Author blue
	 * @Time 2015-9-8
	 */
	private void chooseCity() {
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
							buffer.append("-" + mAreaChoosePopwindow.getmCurrentCityName());
						}
						if (mAreaChoosePopwindow.getmCurrentAreaName().trim().length() > 0) {
							buffer.append("-" + mAreaChoosePopwindow.getmCurrentAreaName());
						}
						mTvCity.setText(buffer.toString());
					}
				}
			});
		}
		
		mAreaChoosePopwindow.showAtLocation(mRootView, Gravity.BOTTOM, 0, 0);
	}
}
