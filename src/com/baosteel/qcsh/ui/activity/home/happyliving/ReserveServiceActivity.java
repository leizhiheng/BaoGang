package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ServiceData;
import com.baosteel.qcsh.ui.adapter.ReserveServiceAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.common.view.pulltorefresh.PullToRefreshListView;

/**
 * 
 * @description 页面：乐居宝->物业服务->预约服务
 * @author blue
 * @Time 2015-9-7
 *
 */
public class ReserveServiceActivity extends BaseActivity{
	
	private TitleBar mTitleBar;
	private PullToRefreshListView mListView;
	
	private List<ServiceData> mServiceDatas;
	private ReserveServiceAdapter mAdapter;
	
	private int mCurPage;
	private int mPageSize = 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserve_service);

		initData();
		initView();

		loadData();

	}

	private void initData() {
		mServiceDatas = new ArrayList<ServiceData>();
		mAdapter = new ReserveServiceAdapter(this, mServiceDatas);
	}

	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle("预约服务");

		mListView = (PullToRefreshListView) findViewById(R.id.listview);
		mListView.setMode(Mode.PULL_FROM_END);
		mListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//				loadData();
//				mListView.onRefreshComplete();
				new GetDataTask().execute();
			}
		});
		
//		mListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				startActivity(new Intent(ReserveServiceActivity.this, CommitReserveMsgActivity.class));
//			}
//		});
		
		mListView.setAdapter(mAdapter);

	}

	private void loadData() {
		
		for (int i = mCurPage * mPageSize; i < (mCurPage + 1) * mPageSize; i++) {
			mServiceDatas.add(new ServiceData());
		}
		mAdapter.notifyDataSetChanged();
	}
	
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			loadData();
			
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
}
