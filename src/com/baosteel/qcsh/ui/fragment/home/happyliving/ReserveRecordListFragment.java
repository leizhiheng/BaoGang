package com.baosteel.qcsh.ui.fragment.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ReserveRecordData;
import com.baosteel.qcsh.ui.adapter.ReserveRecordListAdapter;
import com.common.base.BaseFragment;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.common.view.pulltorefresh.PullToRefreshListView;

public class ReserveRecordListFragment extends BaseFragment{

	private PullToRefreshListView mListView;
	private ReserveRecordListAdapter mAdapter;
	private List<ReserveRecordData> mDatas;
	
	private int mCurPage;
	private int mPageSize = 10;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_tab_indicator, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initData();

		initView();
		
		loadData();
	}
	
	private void initData() {
		mDatas = new ArrayList<ReserveRecordData>();
		mAdapter =  new ReserveRecordListAdapter(this.getActivity(), mDatas);
	}
	
	private void initView() {
		mListView = (PullToRefreshListView) findViewById(R.id.refresh_listview);
		mListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new GetDataTask().execute();
			}
		});
		mListView.setMode(Mode.PULL_FROM_END);
		mListView.setAdapter(mAdapter);
		
	}
	
	private void loadData() {
		for (int i = mPageSize * mCurPage; i < mPageSize * (mCurPage + 1); i++) {
			mDatas.add(new ReserveRecordData());
		}
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

