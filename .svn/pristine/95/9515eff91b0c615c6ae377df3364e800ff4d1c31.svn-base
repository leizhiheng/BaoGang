package com.baosteel.qcsh.ui.activity.home.happyliving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;

public class MyHappyLivingActivity extends BaseActivity implements
		OnClickListener {

	private View mLayout_all_release;
	private View mLayout_draft_release;
	private View mLayout_commited_release;
	private View mLayout_in_checking_release;
	private View mLayout_checked_release;
	private View mLayout_canceled_release;
	private View mLayout_all_reserve_service;
	private View mLayout_all_apply;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_happyliving);

		initData();
		initView();
		loadData();
	}

	private void initData() {

	}

	private void initView() {
		setTitle("我的乐居宝");

		mLayout_all_release = findViewById(R.id.layout_all_release);
		mLayout_draft_release = findViewById(R.id.layout_draft_release);
		mLayout_commited_release = findViewById(R.id.layout_commited_release);
		mLayout_in_checking_release = findViewById(R.id.layout_in_checking_release);
		mLayout_checked_release = findViewById(R.id.layout_checked_release);
		mLayout_canceled_release = findViewById(R.id.layout_canceled_release);
		mLayout_all_reserve_service = findViewById(R.id.layout_all_reserve_service);
		mLayout_all_apply = findViewById(R.id.layout_all_apply);

		mLayout_all_release.setOnClickListener(this);
		mLayout_draft_release.setOnClickListener(this);
		mLayout_commited_release.setOnClickListener(this);
		mLayout_in_checking_release.setOnClickListener(this);
		mLayout_checked_release.setOnClickListener(this);
		mLayout_canceled_release.setOnClickListener(this);
		mLayout_all_reserve_service.setOnClickListener(this);
		mLayout_all_apply.setOnClickListener(this);

	}

	private void loadData() {
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.layout_all_reserve_service:
			/*
			 * 我的预约记录
			 */
			intent = new Intent(this, MyRecordListActivity.class);
			intent.putExtra(EXTRA_KEY_TITLE, "我的预约记录");
			intent.putExtra(MyRecordListActivity.EXTRA_FRAGMENT_INDEX,
					MyRecordListActivity.FRAGMENT_INDEX_RESERVE_RECORD);
			break;

		case R.id.layout_all_apply:
			/*
			 * 我的申报记录
			 */
			intent = new Intent(this, MyRecordListActivity.class);
			intent.putExtra(EXTRA_KEY_TITLE, "我的申报记录");
			intent.putExtra(MyRecordListActivity.EXTRA_FRAGMENT_INDEX,
					MyRecordListActivity.FRAGMENT_INDEX_APPLY_RECORD);
			break;

		case R.id.layout_all_release:
			
			/*
			 * 我所有的发布
			 */
			intent = new Intent(this, MyReleaseRecordeActivity.class);
			intent.putExtra(EXTRA_KEY_TITLE, "全部发布信息");
			break;

		case R.id.layout_draft_release:

			/*
			 * 草稿
			 */
			intent = new Intent(this, MyReleaseRecordeActivity.class);
			intent.putExtra(EXTRA_KEY_TITLE, "草稿箱");
			break;

		case R.id.layout_commited_release:

			/*
			 * 已提交
			 */
			intent = new Intent(this, MyReleaseRecordeActivity.class);
			intent.putExtra(EXTRA_KEY_TITLE, "已提交发布");
			break;

		case R.id.layout_in_checking_release:

			/*
			 * 审核中
			 */
			intent = new Intent(this, MyReleaseRecordeActivity.class);
			intent.putExtra(EXTRA_KEY_TITLE, "审核中发布");
			break;

		case R.id.layout_checked_release:

			/*
			 * 已审核
			 */
			intent = new Intent(this, MyReleaseRecordeActivity.class);
			intent.putExtra(EXTRA_KEY_TITLE, "已审核发布");
			break;
			
		case R.id.layout_canceled_release:
			
			/*
			 * 已取消
			 */
			intent = new Intent(this, MyReleaseRecordeActivity.class);
			intent.putExtra(EXTRA_KEY_TITLE, "已取消发布");
			break;
		default:
			break;
		}
		startActivity(intent);
	}
}
