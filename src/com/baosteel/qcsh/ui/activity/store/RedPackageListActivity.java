package com.baosteel.qcsh.ui.activity.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.activity.common.SearchActivity;
import com.baosteel.qcsh.ui.adapter.RedPackageListAdapter;
import com.baosteel.qcsh.ui.view.StoreScreeningPopWindow;
import com.baosteel.qcsh.utils.Utils;
import com.common.base.BaseActivity;
import com.common.view.listview.MyListView;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshScrollView;
import com.common.view.topbar.HeadView;

/**
 * 店铺列表 Created by kuangyong on 15/9/22.
 */
public class RedPackageListActivity extends BaseActivity implements
		View.OnClickListener, AdapterView.OnItemClickListener,
		PullToRefreshBase.OnRefreshListener<ScrollView> {
	private HeadView headview;// 标题栏
	private ImageView ivbanar;//图片
	private com.common.view.listview.MyListView lvjuan;//红包列表
	private com.common.view.listview.MyListView lvjuanrecieve;//已领取红包列表
	private com.common.view.pulltorefresh.PullToRefreshScrollView scorollview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_redpackage_list);
		findView();
		setListener();
		initView();
		initData();
	}

	private void findView() {
		this.scorollview = (PullToRefreshScrollView) findViewById(R.id.scoroll_view);
		this.lvjuanrecieve = (MyListView) findViewById(R.id.lv_juan_recieve);
		this.lvjuan = (MyListView) findViewById(R.id.lv_juan);
		this.ivbanar = (ImageView) findViewById(R.id.iv_banar);
		this.headview = (HeadView) findViewById(R.id.headview);
	}

	private void setListener() {

	}

	private void initView() {
		headview.setTitle("店铺红包");
		headview.setRightImageBtnBackGround(R.drawable.icon_3point);
		headview.showRightImageBtn(View.VISIBLE, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("菜单");
			}
		});
		headview.setHeadViewBackGroundColor(getResources().getColor(
				R.color.red_baosteel));
		scorollview.setEnabled(false);
		scorollview.setMode(PullToRefreshBase.Mode.BOTH);
		scorollview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
		scorollview.getLoadingLayoutProxy().setLastUpdatedLabel(
				Utils.getCurrTiem());
		scorollview.getLoadingLayoutProxy().setPullLabel("往下拉更新数据...");
		scorollview.getLoadingLayoutProxy().setRefreshingLabel("正在载入中...");
		scorollview.getLoadingLayoutProxy().setReleaseLabel("放开更新数据...");

		// 下拉刷新数据
		scorollview.setOnRefreshListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		RedPackageListAdapter adapter=new RedPackageListAdapter(mContext,1);
		lvjuan.setAdapter(adapter);
		RedPackageListAdapter adapterRecieve=new RedPackageListAdapter(mContext,2);
		lvjuanrecieve.setAdapter(adapterRecieve);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_search:// 搜索
			startActivity(new Intent(mContext, SearchActivity.class));
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}


	@Override
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {

	}
}
