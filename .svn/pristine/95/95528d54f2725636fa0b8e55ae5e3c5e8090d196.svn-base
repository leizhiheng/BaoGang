package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HousingEstateNotice;
import com.baosteel.qcsh.ui.adapter.HousingEstateNoticesAdapter;
import com.baosteel.qcsh.ui.fragment.commen.RefreshListFragment;
import com.baosteel.qcsh.ui.fragment.commen.RefreshListFragment.OnRefreshLisenter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.common.view.pulltorefresh.PullToRefreshListView;

/**
 * 
 * @description 乐居宝 -> 物业服务 ->小区通知
 * @author blue
 * @Time 2015-9-2
 *
 */
public class HousingEstateNoticesActivity extends BaseActivity{

	
	private TitleBar mTitleBar;
	private RefreshListFragment mRefreshListFragment;
	private PullToRefreshListView mListView;
	
	private List<HousingEstateNotice> mNotices;
	private HousingEstateNoticesAdapter mAdapter;
	
	private int mNoticcNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_housing_estate_notices);
		
		initData();
		initView();
		
		loadData();
		
	}
	
	private void initData() {
		mNotices = new ArrayList<HousingEstateNotice>();
		mAdapter = new HousingEstateNoticesAdapter(this, mNotices);
	}
	
	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle("小区通知");
		
		mRefreshListFragment = new RefreshListFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.container, mRefreshListFragment).commit();
		mRefreshListFragment.setAdapter(mAdapter);
		mRefreshListFragment.setOnRefreshLisenter(new OnRefreshLisenter() {
			
			@Override
			public void onRefresh() {
				loadData();
			}
		});
		
		mRefreshListFragment.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(HousingEstateNoticesActivity.this, NoticeDetailActivity.class);
				intent.putExtra(NoticeDetailActivity.EXTRA_KEY_TITLE, NoticeDetailActivity.TITLE_NOTICE_DETAIL);
				startActivity(intent);
			}
		});
	}
	
	private void loadData() {
		
		for (int i = mNoticcNum; i < mNoticcNum + 10; i++) {
			mNotices.add(new HousingEstateNotice("第"+i+"条通知", "中信小区", null, new Date().toLocaleString()));
		}
		
		mNoticcNum = mNoticcNum + 10;
		mAdapter.notifyDataSetChanged();
		Log.d("zhiheng", "loadData ,notice count = "+mNotices.size());
	}
}
