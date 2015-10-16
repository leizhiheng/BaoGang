package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.PhoneData;
import com.baosteel.qcsh.ui.adapter.OftenUsedPhoneAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshGridView;

/**
 * 
 * @description 页面：乐居宝->物业服务->常用电话
 * @author blue
 * @Time 2015-9-7
 *
 */
public class OftenUsedPhoneActivity extends BaseActivity{

	private TitleBar mTitleBar;
	private ListView mListView;
	
	private List<PhoneData> mPhoneDatas;
	private OftenUsedPhoneAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_often_used_phone);

		initData();
		initView();

		loadData();

	}

	private void initData() {
		mPhoneDatas = new ArrayList<PhoneData>();
		mAdapter = new OftenUsedPhoneAdapter(this, mPhoneDatas);
	}

	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle("常用电话");

		mListView = (ListView) findViewById(R.id.listview);
		mListView.setAdapter(mAdapter);
	}

	private void loadData() {
		for (int i = 0; i < 20; i++) {
			mPhoneDatas.add(new PhoneData());
		}
	}
}
