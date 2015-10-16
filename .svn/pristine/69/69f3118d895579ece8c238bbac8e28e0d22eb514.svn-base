package com.baosteel.qcsh.ui.activity.home.happyliving;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.fragment.home.happyliving.ApplyRecordListFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.ReserveRecordListFragment;
import com.common.base.BaseActivity;

public class MyRecordListActivity extends BaseActivity implements
		OnClickListener {

	public static final String EXTRA_FRAGMENT_INDEX = "fragment.index";
	
	/**
	 * 加载预约服务列表的Fragment
	 */
	public static final int FRAGMENT_INDEX_RESERVE_RECORD = 0;
	/**
	 * 加载申报记录列表的Fragment
	 */
	public static final int FRAGMENT_INDEX_APPLY_RECORD = 1;
	
	private int mFragmentIndex;
	private String mTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commen_container_layout);

		initData();
		initView();

		loadData();

	}

	private void initData() {
		mFragmentIndex = getIntent().getIntExtra(EXTRA_FRAGMENT_INDEX, FRAGMENT_INDEX_RESERVE_RECORD);
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
	}

	private void initView() {
		setTitle(mTitle);
		
		if (mFragmentIndex == FRAGMENT_INDEX_APPLY_RECORD) {
			getSupportFragmentManager().beginTransaction().replace(R.id.container, new ApplyRecordListFragment()).commit();
		} else if (mFragmentIndex == FRAGMENT_INDEX_RESERVE_RECORD) {
			getSupportFragmentManager().beginTransaction().replace(R.id.container, new ReserveRecordListFragment()).commit();
		}
	}

	private void loadData() {
		
	}

	@Override
	public void onClick(View v) {
	}
}
