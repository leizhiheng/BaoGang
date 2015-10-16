package com.baosteel.qcsh.ui.activity.home.happyliving;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.dialog.ChooseAreaPopWindow;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 
 * @description 该页面用于设置小区信息和修改小区信息，两个功能布局有所却别需要区分
 * @author blue
 * @Time 2015-9-1
 *
 */
public class HousingEstateMessageActivity extends BaseActivity implements OnClickListener{

	private View mRootView;
	private TitleBar mTitleBar;
	private TextView mBtnChooseAddress;
	private TextView mBtnChooseName;
	
	/**用于区分跳转到此页面的目的是设置小区信息还是修改小区信息*/
	public static final String INTENT_KEY_ACTION = "intent.key.action";
	/**跳转到此页面的目的是设置小区信息还小区信息*/
	public static final int INTENT_ACTION_SETTING = 0;
	/**跳转到此页面的目的是修改小区信息*/
	public static final int INTENT_ACTION_MODIFY= 1;
	
	private int mIntentAction = INTENT_ACTION_SETTING;
	
	/**
	 * 选择小区所在地的popupWindow
	 */
	private ChooseAreaPopWindow mAreaChoosePopwindow = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_housingestate_message);
		initData();
		initView();
	}
	
	private void initData() {
		Intent intent = getIntent();
		mIntentAction = intent.getIntExtra(INTENT_KEY_ACTION, INTENT_ACTION_SETTING);
	}

	private void initView() {
		
		mRootView = findViewById(R.id.root_view);
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mBtnChooseAddress = (TextView) findViewById(R.id.btn_choose_address);
		mBtnChooseName = (TextView) findViewById(R.id.btn_choose_name);
		
		/*
		 * 根据mIntentAction修改TitleBar
		 */
		if (mIntentAction == INTENT_ACTION_SETTING) {
			mTitleBar.setTitle(R.string.title_setting_house_estate);
			//设置右侧popmenu图标可见
			mTitleBar.setRightIconVisibility(0, View.VISIBLE);
		} else {
			mTitleBar.setTitle(R.string.title_fix_house_estate);
		}
		
		mBtnChooseAddress.setOnClickListener(this);
		mBtnChooseName.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btn_choose_address:
			showToast("btn_choose_address clicked");
			chooseAddress();
			break;
		case R.id.btn_choose_name:
//			startActivity(new Intent(this, HouseMessageActivity.class));
			showToast("正待开发");
			break;
		default:
			break;
		}
	}
	
	
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
						
						mBtnChooseAddress.setTextColor(getResources().getColor(R.color.black));
						StringBuffer buffer = new StringBuffer();
						buffer.append(mAreaChoosePopwindow.getmCurrentProviceName());
						if (mAreaChoosePopwindow.getmCurrentCityName().trim().length() > 0) {
							buffer.append("  -  " + mAreaChoosePopwindow.getmCurrentCityName());
						}
						if (mAreaChoosePopwindow.getmCurrentAreaName().trim().length() > 0) {
							buffer.append("  -  " + mAreaChoosePopwindow.getmCurrentAreaName());
						}
						mBtnChooseAddress.setText(buffer.toString());
					}
				}
			});
		}
		
		mAreaChoosePopwindow.showAtLocation(mRootView, Gravity.BOTTOM, 0, 0);
	}
}
