package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HouseData;
import com.baosteel.qcsh.ui.activity.common.SingleSelectActivity;
import com.baosteel.qcsh.ui.adapter.HouseHolderAdapter;
import com.baosteel.qcsh.ui.fragment.commen.RefreshListFragment;
import com.baosteel.qcsh.ui.fragment.commen.RefreshListFragment.OnRefreshLisenter;
import com.baosteel.qcsh.ui.view.TabView;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 
 * @description 乐居宝->我是房东
 * @author blue
 * @Time 2015-9-6
 * 
 */
public class IamHouseHolderActivity extends BaseActivity implements
		OnClickListener {

	private RefreshListFragment mRefreshListFragment;
	/**
	 * 顶部四个按钮的布局
	 */
	private View mTabLayoutCity;
	private View mTabLayoutCategory;
	private View mTabLayoutArea;
	private View mTabLayoutSource;

	/**
	 * 底部四个按钮的布局
	 */
	private View mTabLayoutNear;
	private View mTabLayoutMap;
	private View mTabLayoutRelease;

	/**
	 * 城市
	 */
	private TabView mTabCity;
	/**
	 * 类型
	 */
	private TabView mTabCategory;
	/**
	 * 面积
	 */
	private TabView mTabArea;
	/**
	 * 来源
	 */
	private TabView mTabSource;

	/**
	 * 附近
	 */
	private TabView mTabNear;
	/**
	 * 地图
	 */
	private TabView mTabMap;
	/**
	 * 我要发布
	 */
	private TabView mTabRelease;
	
	private TabView mCurTopTabView;
	private TabView mCurBottomTabView;

	private List<HouseData> mDatas;
	private HouseHolderAdapter mAdapter;

	private int mCurpage;
	private static final int PAGE_SIZE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iam_house_holder);
		initData();
		initView();

		loadData();
	}

	private void initData() {
		mCurpage = 1;
		mDatas = new ArrayList<HouseData>();
		mAdapter = new HouseHolderAdapter(this, mDatas);
		
	}

	/**
	 * 
	 * @Description 初始化布局
	 * @Author blue
	 * @Time 2015-9-6
	 */
	private void initView() {
		/*
		 * title bar
		 */
		setTitle(R.string.title_iam_house_holder);
		mTitleBar.showRightIcon(TitleBar.ICON_INDEX_SEARCH, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IamHouseHolderActivity.this, SingleSelectActivity.class);
				intent.putExtra(EXTRA_KEY_TITLE, "租金");
				intent.putExtra(SingleSelectActivity.EXTRA_KEY_PAGE_INDEX, SingleSelectActivity.PAGE_INDEX_CHOOSE_PRICE);
				startActivityForResult(intent, 0);
			}
		});

		/*
		 * 上拉加载ListView
		 */
		mRefreshListFragment = new RefreshListFragment();
		mRefreshListFragment.setAdapter(mAdapter);
		mRefreshListFragment.setOnRefreshLisenter(new OnRefreshLisenter() {

			@Override
			public void onRefresh() {
				loadData();
				mRefreshListFragment.setSelection(1);
				mRefreshListFragment.onRefreshComplete();
			}
		});
		
		mRefreshListFragment.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(IamHouseHolderActivity.this, HouseDetailActivity.class));
			}
		});
		getSupportFragmentManager().beginTransaction().replace(R.id.container, mRefreshListFragment).commit();

		/*
		 * 城市
		 */
		mTabLayoutCity = findViewById(R.id.tab_city_llayout);
		mTabLayoutCity.setOnClickListener(this);
		mTabLayoutCity.setTag(0);

		ImageView imageview = (ImageView) findViewById(R.id.tab_city_imageview);
		TextView textview = (TextView) findViewById(R.id.tab_city_textview);
		mTabCity = new TabView();
		mTabCity.setView(imageview, textview);
		mTabCity.setIcon(R.drawable.spinner_down, R.drawable.spinner_down);
		mTabCity.setTextColor(getResources().getColor(R.color.red),
				getResources().getColor(R.color.black));
		mTabCity.clickIn();

		/*
		 * 类型
		 */
		mTabLayoutCategory = findViewById(R.id.tab_category_llayout);
		mTabLayoutCategory.setOnClickListener(this);
		mTabLayoutCity.setTag(1);

		imageview = (ImageView) findViewById(R.id.tab_category_imageview);
		textview = (TextView) findViewById(R.id.tab_category_textview);
		mTabCategory = new TabView();
		mTabCategory.setView(imageview, textview);
		mTabCategory.setIcon(R.drawable.spinner_down, R.drawable.spinner_down);
		mTabCategory.setTextColor(getResources().getColor(R.color.red),
				getResources().getColor(R.color.black));
		mTabCategory.clickOn();
		/*
		 * 面积
		 */
		mTabLayoutArea = findViewById(R.id.tab_area_llayout);
		mTabLayoutArea.setOnClickListener(this);
		mTabLayoutCity.setTag(2);

		imageview = (ImageView) findViewById(R.id.tab_area_imageview);
		textview = (TextView) findViewById(R.id.tab_area_textview);
		mTabArea = new TabView();
		mTabArea.setView(imageview, textview);
		mTabArea.setIcon(R.drawable.spinner_down, R.drawable.spinner_down);
		mTabArea.setTextColor(getResources().getColor(R.color.red),
				getResources().getColor(R.color.black));
		mTabArea.clickOn();
		/*
		 * 来源
		 */
		mTabLayoutSource = findViewById(R.id.tab_source_llayout);
		mTabLayoutSource.setOnClickListener(this);
		mTabLayoutCity.setTag(3);

		imageview = (ImageView) findViewById(R.id.tab_source_imageview);
		textview = (TextView) findViewById(R.id.tab_source_textview);
		mTabSource = new TabView();
		mTabSource.setView(imageview, textview);
		mTabSource.setIcon(R.drawable.spinner_down, R.drawable.spinner_down);
		mTabSource.setTextColor(getResources().getColor(R.color.red),
				getResources().getColor(R.color.black));
		mTabSource.clickOn();

		/*
		 * 附近
		 */
		mTabLayoutNear = findViewById(R.id.tab_near_llayout);
		mTabLayoutNear.setOnClickListener(this);
		mTabLayoutCity.setTag(0);

		imageview = (ImageView) findViewById(R.id.tab_near_imageview);
		textview = (TextView) findViewById(R.id.tab_near_textview);
		mTabNear = new TabView();
		mTabNear.setView(imageview, textview);
		mTabNear.setIcon(R.drawable.ic_happyliving_near_sel, R.drawable.ic_happyliving_near);
		mTabNear.setTextColor(getResources().getColor(R.color.red),
				getResources().getColor(R.color.black));
		mTabNear.clickOn();
		/*
		 * 地图
		 */
		mTabLayoutMap = findViewById(R.id.tab_map_llayout);
		mTabLayoutMap.setOnClickListener(this);
		mTabLayoutCity.setTag(2);

		imageview = (ImageView) findViewById(R.id.tab_map_imageview);
		textview = (TextView) findViewById(R.id.tab_map_textview);
		mTabMap = new TabView();
		mTabMap.setView(imageview, textview);
		mTabMap.setIcon(R.drawable.ic_happyliving_map_sel, R.drawable.ic_happyliving_map);
		mTabMap.setTextColor(getResources().getColor(R.color.red),
				getResources().getColor(R.color.black));
		mTabArea.clickOn();

		/*
		 * 我要发布
		 */
		mTabLayoutRelease = findViewById(R.id.tab_release_llayout);
		mTabLayoutRelease.setOnClickListener(this);

		imageview = (ImageView) findViewById(R.id.tab_release_imageview);
		textview = (TextView) findViewById(R.id.tab_release_textview);
		mTabRelease = new TabView();
		mTabRelease.setView(imageview, textview);
		mTabRelease.setIcon(R.drawable.ic_happyliving_release_sel, R.drawable.ic_happyliving_release);
		mTabRelease.setTextColor(getResources().getColor(R.color.red),
				getResources().getColor(R.color.black));
		mTabRelease.clickOn();

		mCurBottomTabView = mTabNear;
		mCurTopTabView = mTabCity;
		mTabLayoutMap.performClick();//这个Tab显示不正常，模拟一次点击显示正常了
		mTabLayoutCity.performClick();
	}

	/**
	 * 
	 * @Description 获取数据
	 * @Author blue
	 * @Time 2015-9-6
	 */
	private void loadData() {

		for (int i = (mCurpage - 1) * PAGE_SIZE; i < mCurpage * PAGE_SIZE; i++) {
			mDatas.add(new HouseData());
		}

		mCurpage++;
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/*
		 * 顶部tag
		 */
		case R.id.tab_city_llayout:
			setTopSelectedTab(mTabCity);
			break;
		case R.id.tab_category_llayout:
			setTopSelectedTab(mTabCategory);
			break;
		case R.id.tab_area_llayout:
			setTopSelectedTab(mTabArea);
			break;
		case R.id.tab_source_llayout:
			setTopSelectedTab(mTabSource);
			break;
		/*
		 * 底部Tab
		 */
		case R.id.tab_near_llayout:
			setTopSelectedTab(mTabNear);
			break;
		case R.id.tab_map_llayout:
			setTopSelectedTab(mTabMap);
			break;
		case R.id.tab_release_llayout:
			setTopSelectedTab(mTabRelease);
			startActivity(new Intent(this, ReleaseHouseMsgActivity.class));
			break;

		default:
			break;
		}
	}
	
	/**
	 * 
	 * @Description 选中TabView
	 * @Author blue
	 * @Time 2015-9-6
	 */
	private void setTopSelectedTab(TabView tabView) {
		if (tabView == null || mCurTopTabView == tabView) {
			return;
		}
		mCurTopTabView.clickOn();
		tabView.clickIn();
		mCurTopTabView = tabView;
	}
	
	private void setBottomSelectedTab(TabView tabView) {
		if (mCurBottomTabView == tabView) {
			return;
		}
		
		mCurBottomTabView.clickOn();
		tabView.clickIn();
		mCurBottomTabView = tabView;
	}
}
