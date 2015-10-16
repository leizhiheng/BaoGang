package com.baosteel.qcsh.ui.activity.home.healthy;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.dialog.HealthyProductFilterPopwindow;
import com.baosteel.qcsh.model.PhysicalExamPackageData;
import com.baosteel.qcsh.ui.adapter.PhysicalExamReserveAdapter;
import com.baosteel.qcsh.ui.view.TabViewPagerIndicator;
import com.baosteel.qcsh.ui.view.TabViewPagerIndicator.OnTabSelectedListerner;
import com.common.base.BaseActivity;
import com.common.utils.DeviceUtils;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshListView;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;

/**
 * 
 * @description 页面：健康宝-》养老期床
 * @author blue
 * @Time 2015-9-14
 *
 */
@SuppressLint("NewApi")
public class BeadhouseFutureBedActivity extends BaseActivity{

	private TabViewPagerIndicator mIndicator;
	private PullToRefreshListView mListView;
	
	private List<PhysicalExamPackageData> mDatas;
	private PhysicalExamReserveAdapter mAdapter;
	
	private int mPageSize = 10;
	private int mCurPage;
	
	private static final String TAB_TITELS[] = new String[]{"区域","价格","销量","筛选"};
	
	/**
	 * 商品筛选PopupWindow
	 */
	private HealthyProductFilterPopwindow mFilterPopwindow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beadhouse_ready_bed);
		
		initData();
		initView();
		
		loadData();
		
		mIndicator.setCurrentItem(0);
	}
	
	private void initData() {
		mDatas = new ArrayList<PhysicalExamPackageData>();
		mAdapter = new PhysicalExamReserveAdapter(this, mDatas);
	}
	
	private void initView() {
		setTitle("养老期床预约");
		mTitleBar.setBackgroudColor(Constants.getTypeColor(Constants.TYPE_HEALTH));
		
		mIndicator = (TabViewPagerIndicator) findViewById(R.id.indicator_tab);
		setTabIndicator();
		
		mListView = (PullToRefreshListView) findViewById(R.id.refresh_listview);
		mListView.setAdapter(mAdapter);
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
				Intent intent = new Intent(BeadhouseFutureBedActivity.this, HealthProductDetailActivity.class);
				intent.putExtra(HealthProductDetailActivity.EXTRA_KEY_PRODUCT_TYPE, HealthProductDetailActivity.PRODUCT_FUTURE_BED);
				startActivity(intent);
			}
		});
		mListView.setMode(Mode.PULL_FROM_END);
	}
	
	private void loadData() {
		for (int i = mCurPage * mPageSize; i < (mCurPage + 1) * mPageSize; i++) {
			mDatas.add(new PhysicalExamPackageData("北京福提养老院", R.drawable.ic_healthy_futures_bed));
		}
		mCurPage++;
	}
	
	private void setTabIndicator() {
		
		//设置TAB的标题，并根据标题的个数创建Tab
		mIndicator.setTabTitles(TAB_TITELS);
		
		//设置Tab选中颜色，该方法必须在setTabTitles（）之后调用
		mIndicator.setColor(getResources().getColor(R.color.theme_color_healthy));
		
		//显示Tab右侧图标
		mIndicator.setTabIcon(1, R.drawable.ic_order, R.drawable.ic_order_sel);
		mIndicator.setTabIcon(2, R.drawable.ic_order, R.drawable.ic_order_sel);
		
		//显示Tab右侧分割线
		mIndicator.showRightDivideLine(0);
		mIndicator.showRightDivideLine(1);
		mIndicator.showRightDivideLine(2);
		
		//设置Tab的点击监听事件
		mIndicator.setOnTabSelectedListerner(new OnTabSelectedListerner() {
			
			@Override
			public void onTabSelected(int tag) {
				switch (tag) {
				case 0:
					/*
					 * 区域Tab
					 */
//					if (mFilterPopwindow == null) {
						mFilterPopwindow = new HealthyProductFilterPopwindow(BeadhouseFutureBedActivity.this);
//					}
					int width = mFilterPopwindow.getWidth();
					
					if (!mFilterPopwindow.isShowing()) {
						mFilterPopwindow.showAsDropDown(mIndicator, DeviceUtils.getWidthMaxPx(BeadhouseFutureBedActivity.this) - width, 0);
					}
					
					break;
					
				case 1:
					/*
					 * 价格Tab
					 */
					showToast("selected price tag");
					
					break;
					
				case 2:
					/*
					 * 销量Tab
					 */
					showToast("selected sale number tag");
					
					break;
					
				case 3:
					/*
					 * 筛选Tab
					 */
//					if (mFilterPopwindow == null) {
						mFilterPopwindow = new HealthyProductFilterPopwindow(BeadhouseFutureBedActivity.this);
//					}
					
					if (!mFilterPopwindow.isShowing()) {
						mFilterPopwindow.showAsDropDown(mIndicator, 0, 0);
					}
					
					break;

				default:
					break;
				}
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
