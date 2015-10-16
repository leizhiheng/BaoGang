package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ShouldKnowData;
import com.baosteel.qcsh.ui.adapter.ShouldKnowAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.common.view.pulltorefresh.PullToRefreshGridView;

/**
 * 
 * @description 乐居宝 -> 物业服务 ->小区须知
 * @author blue
 * @Time 2015-9-2
 * 
 */
public class HousingEstateShouldKnowActivity extends BaseActivity {

	private TitleBar mTitleBar;
	private PullToRefreshGridView mGridView;

	private List<ShouldKnowData> mShouldKnowDatas;
	private ShouldKnowAdapter mAdapter;

	private int mCurpageIndex;
	private int mPageSize = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_housing_estate_should_know);

		initData();
		initView();

		loadData();

	}

	private void initData() {
		mShouldKnowDatas = new ArrayList<ShouldKnowData>();
		mAdapter = new ShouldKnowAdapter(this, mShouldKnowDatas);
	}

	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle("小区须知");

		/**
		 * 上拉GridView
		 */
		mGridView = (PullToRefreshGridView) findViewById(R.id.gridview);
		mGridView.setMode(Mode.PULL_FROM_END);
		mGridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				loadData();
				mGridView.onRefreshComplete();
			}

		});
		
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(HousingEstateShouldKnowActivity.this, NoticeDetailActivity.class);
				intent.putExtra(NoticeDetailActivity.EXTRA_KEY_TITLE, NoticeDetailActivity.TITLE_SHOULD_KNOW_DETAIL);
				startActivity(intent);
			}
		});
		mGridView.setAdapter(mAdapter);
		
		

	}

	private void loadData() {

		for (int i = mCurpageIndex * mPageSize; i < (mCurpageIndex + 1) * mPageSize; i++) {
			mShouldKnowDatas.add(new ShouldKnowData(null, null));
		}
		mCurpageIndex++;
		mAdapter.notifyDataSetChanged();
	}
}
