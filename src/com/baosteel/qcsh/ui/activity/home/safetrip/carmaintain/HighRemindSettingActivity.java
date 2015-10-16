package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * 汽车保养/维修 Created by kuangyong on 15/9/10.
 */
public class HighRemindSettingActivity extends BaseActivity implements
		View.OnClickListener {

	private HeadView headview;// 标题布局
	private TextView tvcarname;// 汽车名称及参数
	private TextView tvcarmoreinfo;// 汽车更多信息
	private LinearLayout layoutcarinfo;// 汽车信息布局
	private RemindListFragment remindListFragment;//提醒列表
	private RemindDetailsFragment remindDetailsFragment;// 提醒详情

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_remind_setting);
		findView();
		setListener();
		initView();
		initFragments();
	}

	private void findView() {
		this.headview = (HeadView) findViewById(R.id.headview);
		this.layoutcarinfo = (LinearLayout) findViewById(R.id.layout_car_info);
		this.tvcarmoreinfo = (TextView) findViewById(R.id.tv_car_more_info);
		this.tvcarname = (TextView) findViewById(R.id.tv_car_name);
	}

	private void setListener() {
		layoutcarinfo.setOnClickListener(this);
	}

	/**
	 * 初始化页面
	 */
	private void initFragments() {
		remindListFragment=new RemindListFragment();
		remindListFragment.setOnItemClickListener(new RemindListFragment.OnListItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				remindDetailsFragment=new RemindDetailsFragment();
				remindDetailsFragment.setOnCompleteListener(new RemindDetailsFragment.OnCompleteListener() {
					@Override
					public void complete() {
						showFragment(R.id.fragment_setting, remindListFragment);
					}
				});
				showFragment(R.id.fragment_setting,remindDetailsFragment);
			}
		});
		showFragment(R.id.fragment_setting, remindListFragment);
	}

	private void initView() {
		headview.setTitle("高级提醒设置");
		headview.setHeadViewBackGroundColor(Color.TRANSPARENT);
		headview.setSeparateLineVisible(View.INVISIBLE);
		headview.setRightImageBtnBackGround(R.drawable.icon_location_white);
		headview.showRightImageBtn(View.VISIBLE, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("定位");
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_car_info:// 汽车信息
			startActivity(new Intent(mContext, CarManageActivity.class));
			break;
		}
	}
}
