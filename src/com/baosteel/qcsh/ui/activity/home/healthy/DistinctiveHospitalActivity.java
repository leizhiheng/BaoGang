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
import com.baosteel.qcsh.model.DistinctiveHospitalData;
import com.baosteel.qcsh.ui.adapter.DistinctiveHospitalAdapter;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.common.view.pulltorefresh.PullToRefreshListView;

/**
 * 
 * @description 页面：健康宝-》社区健康-》特色医院
 * @author blue
 * @Time 2015-9-15
 *
 */
public class DistinctiveHospitalActivity extends BaseActivity{

	private PullToRefreshListView mListView;
	
	private List<DistinctiveHospitalData> mDatas;
	private DistinctiveHospitalAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distinctive_hospital);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mDatas = new ArrayList<DistinctiveHospitalData>();
		mAdapter = new DistinctiveHospitalAdapter(this, mDatas);
	}
	
	private void initView() {
		setTitle("特色医院");
		mTitleBar.setBackgroud(R.color.theme_color_healthy);
		
		mListView = (PullToRefreshListView) findViewById(R.id.refresh_listview);
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
				Intent intent = new Intent(DistinctiveHospitalActivity.this, DistinctiveHospitalDetailActivity.class);
				intent.putExtra(EXTRA_KEY_TITLE, mDatas.get(position).getTitle());
				startActivity(intent);
			}
		});
		mListView.setAdapter(mAdapter);
	}
	
	private void loadData() {
		for (int i = 0; i < 10; i++) {
			mDatas.add(new DistinctiveHospitalData("上海人民医院"));
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
			mListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
}
