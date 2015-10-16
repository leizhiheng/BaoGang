package com.baosteel.qcsh.ui.activity.home.healthy;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.dialog.HealthyProductFilterPopwindow;
import com.baosteel.qcsh.model.PhysicalExamPackageData;
import com.baosteel.qcsh.ui.adapter.PhysicalExamReserveAdapter;
import com.baosteel.qcsh.ui.view.TabViewPagerIndicator;
import com.baosteel.qcsh.ui.view.TabViewPagerIndicator.OnTabSelectedListerner;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.common.view.pulltorefresh.PullToRefreshListView;

/**
 * 
 * @description 页面：健康宝-》体检预定
 * @author blue
 * @Time 2015-9-14
 *
 */
@SuppressLint("NewApi")
public class PhysicalExaminationReserveActivity extends BaseActivity implements OnClickListener, OnCheckedChangeListener{

	private ImageView mBtnBack;
	private ImageView mBtnMenu;
	private RadioGroup mRadioGroup;
	private RadioButton mBtnPersenal;
	private RadioButton mBtnTeam;
	
	private TabViewPagerIndicator mIndicator;
	private PullToRefreshListView mListView;
	
	private List<PhysicalExamPackageData> mDatas;
	private PhysicalExamReserveAdapter mAdapter;
	
	private int mPageSize = 10;
	private int mCurPage;
	
	/**
	 * 商品筛选PopupWindow
	 */
	private HealthyProductFilterPopwindow mFilterPopwindow;
	
	private static final String TAB_TITELS[] = new String[]{"区域","价格","销量","筛选"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_physical_examination_reserve);
		
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
		
		/*
		 * Title bar
		 */
		mBtnBack = (ImageView) findViewById(R.id.iv_headview_goback);
		mBtnMenu = (ImageView) findViewById(R.id.imgbtn_right);
		mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
		mBtnPersenal = (RadioButton) findViewById(R.id.btn_left);
		mBtnTeam = (RadioButton) findViewById(R.id.btn_right);
		mRadioGroup.setOnCheckedChangeListener(this);
		mBtnPersenal.setChecked(true);
		mBtnBack.setOnClickListener(this);
		mBtnMenu.setOnClickListener(this);
		
		/*
		 * 分类选项卡
		 */
		mIndicator = (TabViewPagerIndicator) findViewById(R.id.indicator_tab);
		setTabIndicator();
		
		/*
		 * 上啦加载ListView
		 */
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
				startActivity(new Intent(PhysicalExaminationReserveActivity.this, 
						HealthProductDetailActivity.class));
			}
		});
		
		mListView.setMode(Mode.PULL_FROM_END);
	}
	
	private void loadData() {
		for (int i = mCurPage * mPageSize; i < (mCurPage + 1) * mPageSize; i++) {
			mDatas.add(new PhysicalExamPackageData("宝钢健康公司 健康体检A套餐", R.drawable.ic_healthy_hospital));
		}
		mCurPage++;
	}
	
	/**
	 * 
	 * @Description 设置分类选项卡
	 * @Author blue
	 * @Time 2015-9-15
	 */
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
						mFilterPopwindow = new HealthyProductFilterPopwindow(PhysicalExaminationReserveActivity.this);
//					}
					
					if (!mFilterPopwindow.isShowing()) {
						mFilterPopwindow.showAsDropDown(mIndicator, 0, 0);
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
						mFilterPopwindow = new HealthyProductFilterPopwindow(PhysicalExaminationReserveActivity.this);
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
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_headview_goback:
			finish();
			break;
			
		case R.id.imgbtn_right:
			showToast("show popup menu");
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId == R.id.btn_left) {
			
		} else {
			
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
