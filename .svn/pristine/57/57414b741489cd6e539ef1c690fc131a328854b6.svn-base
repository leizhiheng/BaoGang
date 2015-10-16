package com.baosteel.qcsh.ui.activity.home.travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.utils.DialogUtil;

/**
 * 旅行宝-机票
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class AirTicketActivity extends BaseActivity implements OnClickListener {

	/** 标题栏 **/
	private TitleBar mTitleBar;

	/**去程布局**/
	private RelativeLayout goRL;
	
	/**返程布局**/
	private RelativeLayout backRL;
	
	/** 单程 **/
	private TextView singleWayTv;

	/** 往返 **/
	private TextView doubleWayTv;

	/**去程日期**/
	private TextView goDateTv;
	
	/**返程日期**/
	private TextView backDateTv;
	
	/** 开始搜索 **/
	private Button searchBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_airticket);

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
		mTitleBar.setBackgroudValue(Constants.getTypeColor(Constants.TYPE_TRAVEL));
		mTitleBar.setTitle("国内机票");

		//去程布局，返程布局
		goRL = (RelativeLayout)findViewById(R.id.goTimeRl);
		backRL = (RelativeLayout)findViewById(R.id.backTimeRl);
		goRL.setOnClickListener(this);
		backRL.setOnClickListener(this);
		
		// 单程，往返
		singleWayTv = (TextView) findViewById(R.id.singleWayTv);
		doubleWayTv = (TextView) findViewById(R.id.doubleWayTv);
		singleWayTv.setOnClickListener(this);
		doubleWayTv.setOnClickListener(this);

		//去程，返程日期
		goDateTv = (TextView)findViewById(R.id.goDateTv);
		backDateTv = (TextView)findViewById(R.id.backDateTv);
		
		// 搜索
		searchBtn = (Button) findViewById(R.id.startSearchBtn);
		searchBtn.setOnClickListener(this);

		//默认显示单程
		showlWay(true);
	}

	/**
	 * 初始化数据
	 */
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.singleWayTv:

			// 单程
			showlWay(true);
			
			break;
		case R.id.doubleWayTv:

			// 往返
			showlWay(false);
			
			break;
		case R.id.goTimeRl:
			
			//选择去程日期
			DialogUtil.showBirthdayDialog(goDateTv, mContext);
			
			break;
		case R.id.backTimeRl:
			
			//选择返程日期
			DialogUtil.showBirthdayDialog(backDateTv, mContext);
			
			break;
		case R.id.startSearchBtn:

			// 开始搜索

			break;
		}
	}

	/**
	 * 显示单程，或往返
	 * @param showSingleWay
	 */
	private void showlWay(boolean showSingleWay) {
		if (showSingleWay) {

			// 单程
			singleWayTv.setBackgroundResource(R.drawable.common_leftradius_orange_travel_on);
			doubleWayTv.setBackgroundResource(R.drawable.common_rightradius_orange_travel_in);
			backRL.setVisibility(View.GONE);
			singleWayTv.setTextColor(getResources().getColor(R.color.orange));
			doubleWayTv.setTextColor(getResources().getColor(R.color.white));
		} else {

			// 往返
			singleWayTv.setBackgroundResource(R.drawable.common_leftradius_orange_travel_in);
			doubleWayTv.setBackgroundResource(R.drawable.common_rightradius_orange_travel_on);
			backRL.setVisibility(View.VISIBLE);
			singleWayTv.setTextColor(getResources().getColor(R.color.white));
			doubleWayTv.setTextColor(getResources().getColor(R.color.orange));

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
