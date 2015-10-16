package com.baosteel.qcsh.ui.activity.home.healthy;

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
import com.baosteel.qcsh.model.PhysicalExamReportListItem;
import com.baosteel.qcsh.ui.adapter.PhysicalExamReportListAdapter;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.common.view.pulltorefresh.PullToRefreshListView;

public class PhysicalExamReportListActivity extends BaseActivity{

	private PullToRefreshListView mListView;
	
	private List<PhysicalExamReportListItem> mDatas;
	private PhysicalExamReportListAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_physical_examination_report_list);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mDatas = new ArrayList<PhysicalExamReportListItem>();
		mAdapter = new PhysicalExamReportListAdapter(this, mDatas);
	}
	
	private void initView() {
		setTitle("体检报告列表");
		mTitleBar.setBackgroud(R.color.theme_color_healthy);
		
		mListView = (PullToRefreshListView) findViewById(R.id.refresh_listview);
		mListView.setAdapter(mAdapter);
		mListView.setMode(Mode.PULL_FROM_END);
		mListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new GetDataTask().execute();
			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(PhysicalExamReportListActivity.this, ExamReportDetailActivity.class));
			}
		});
	}
	
	private void loadData() {
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				mDatas.add(new PhysicalExamReportListItem("全身体检"));
			} else {
				mDatas.add(new PhysicalExamReportListItem("内科、肝功能二项"));
			}
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
