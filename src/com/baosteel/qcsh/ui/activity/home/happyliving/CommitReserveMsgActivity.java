package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.RoomerMsgData;
import com.baosteel.qcsh.ui.activity.common.SingleSelectActivity;
import com.baosteel.qcsh.ui.adapter.RoomerMsgAdapter;
import com.baosteel.qcsh.ui.fragment.commen.WebViewFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 
 * @description 页面：乐居宝-》物业服务-》预约服务-》提交预约信息
 * @author blue
 * @Time 2015-9-9
 *
 */
public class CommitReserveMsgActivity extends BaseActivity implements OnClickListener{
	
	private TitleBar mTitleBar;
	
	/**
	 * 点击选择小区
	 */
	private View mViewChangeHE;
	/**
	 * 点击选择所在单元
	 */
	private View mViewChangePart;
	/**
	 * 点击选择房号
	 */
	private View mViewChangeRoom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commit_reserve_msg);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
	}
	
	private void initView() {
		setTitle("提交预约信息");
		
		mViewChangeHE = findViewById(R.id.layout_choose_xiaoqu);
		mViewChangePart = findViewById(R.id.layout_choose_depart);
		mViewChangeRoom = findViewById(R.id.layout_choose_room);
		
		mViewChangeHE.setOnClickListener(this);
		mViewChangePart.setOnClickListener(this);
		mViewChangeRoom.setOnClickListener(this);
		
	}
	
	private void loadData() {
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.layout_choose_xiaoqu:
			/*
			 * 选择小区
			 */
			intent = new Intent(this, HousingEstateMessageActivity.class);
			intent.putExtra(HousingEstateMessageActivity.INTENT_KEY_ACTION, HousingEstateMessageActivity.INTENT_ACTION_MODIFY);
			startActivityForResult(intent, 0);
			break;
		case R.id.layout_choose_depart:
			/*
			 * 选择所在单元
			 */
			intent = new Intent(this, HouseMessageActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.layout_choose_room:
			/*
			 * 选择所在房号
			 */
			intent = new Intent(this, HouseMessageActivity.class);
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}
		
	}
}