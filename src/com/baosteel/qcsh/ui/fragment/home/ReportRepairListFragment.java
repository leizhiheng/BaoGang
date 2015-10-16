package com.baosteel.qcsh.ui.fragment.home;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.MyReportPrepairData;
import com.baosteel.qcsh.model.MyReserveServiceData;
import com.baosteel.qcsh.ui.adapter.MyReportRepairAdapter;
import com.baosteel.qcsh.ui.adapter.MyReserveServiceAdapter;
import com.common.base.BaseFragment;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshListView;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener2;

public class ReportRepairListFragment extends BaseFragment {

	private int mBgColor;
	/**
	 * 下拉刷新ListView
	 */
	private PullToRefreshListView mListView;
	/**
	 * mListView的Adapter
	 */
	private MyReportRepairAdapter mAdapter;
	/**
	 * ListView的单击事件
	 */
	private OnItemClickListener mOnItemClickListener;
	/**
	 * 显示数据
	 */
	private List<MyReportPrepairData> mDatas;
	private int mCurPage;
	private int mPageSize = 8;
	
	public static ReportRepairListFragment getInstance(int color) {
		ReportRepairListFragment fragment = new ReportRepairListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("color", color);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		Log.d("zhiheng", "arguments = "+getArguments()+", savedInstanceState = "+savedInstanceState);
		mBgColor = this.getArguments().getInt("color");
		fragmentView = inflater.inflate(R.layout.fragment_tab_indicator, null);
//		fragmentView.setBackgroundColor(mBgColor);
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
		mDatas = new ArrayList<MyReportPrepairData>();
		mAdapter = new MyReportRepairAdapter(this.getActivity(), mDatas);
	}

	private void initView() {
		mListView = (PullToRefreshListView) fragmentView.findViewById(R.id.refresh_listview);
		mListView.setAdapter(mAdapter);
		
		setListener();
	}
	
	private void loadData() {
		
		for (int i = mCurPage * mPageSize; i < (mCurPage + 1) * mPageSize; i++) {
			mDatas.add(new MyReportPrepairData());
		}
		mCurPage++;
		mAdapter.notifyDataSetChanged();
		
		mListView.onRefreshComplete();
	}

	/**
	 * 
	 * @Description 设置选中项
	 * @Author blue
	 * @Time 2015-9-6
	 */
	public void setSelection(int position) {
		mListView.getRefreshableView().setSelection(position);
	}

	/**
	 * 
	 * @Description 设置ListView的监听事件
	 * @Author blue
	 * @Time 2015-9-7
	 */
	public void setOnItemClickListener(OnItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}

	private void setListener() {
		mListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);// 设置刷新模式：下拉、上拉或两者皆可
		// mListView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载更多");
		// mListView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载");
		// mListView.getLoadingLayoutProxy(false,
		// true).setRefreshingLabel("正在加载...");
		if (mOnItemClickListener != null)
			mListView.setOnItemClickListener(mOnItemClickListener);
		mListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				new GetDataTask().execute();
			}
		});
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
