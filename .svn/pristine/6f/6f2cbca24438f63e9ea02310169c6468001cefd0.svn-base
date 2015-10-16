package com.baosteel.qcsh.ui.activity.home.safetrip;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.bean.TopProduct;
import com.baosteel.qcsh.dialog.TopbarMenuPopwindow;
import com.baosteel.qcsh.ui.activity.common.SearchActivity;
import com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain.CarMainTainActivity;
import com.baosteel.qcsh.ui.activity.home.safetrip.carmall.CarListActivity;
import com.baosteel.qcsh.ui.activity.home.safetrip.carsafe.BuyCarSafeActivity;
import com.baosteel.qcsh.ui.activity.home.safetrip.illegalquery.IllegalQueryActivity;
import com.baosteel.qcsh.ui.activity.home.tongue.TonguePrivateActivity;
import com.baosteel.qcsh.ui.activity.home.tongue.TongueTypeActivity;
import com.baosteel.qcsh.ui.adapter.TopProdectAdapter;
import com.baosteel.qcsh.ui.view.MyGridView;
import com.common.base.BaseActivity;
import com.common.utils.DeviceUtils;
import com.common.view.banner.BannerData;
import com.common.view.banner.BannerView;
import com.common.view.banner.IOnBannerItenClick;
import com.common.view.fastlink.FastLinkData;
import com.common.view.fastlink.FastLinkView;
import com.common.view.fastlink.LnkToolsGridViewAdapter.BackItemClickListener;
import com.common.view.pulltorefresh.PullToRefreshScrollView;
import com.common.view.scrolllayout.PageControlView;
import com.common.view.scrolllayout.ScrollLayout;
import com.common.view.viewflow.CircleFlowIndicator;
import com.common.view.viewflow.ViewFlow;

/**
 * 安程宝首页 Created by kuangyong on 15/9/08.
 */
public class SafeTripActivity extends BaseActivity implements
		IOnBannerItenClick, BackItemClickListener, View.OnClickListener {
	
	private ImageView mRightIcon;
	private ImageView mBtnBack;
	private View mViewSearch;
	/**
	 * Banner轮播图
	 **/
	private BannerView bannerView;
	/**
	 * 快速导航
	 **/
	private FastLinkView fastLinkView;
	/**
	 * 热门榜单
	 **/
	private MyGridView myGridView;
	private android.widget.ImageView activityindexsousuoiv;
	private android.widget.TextView activityindexsearchcontent;
	private android.widget.LinearLayout absearch;
	private android.widget.ImageView homelocationimageview;
	private android.widget.LinearLayout indexsousuolinlayout;
	private android.widget.RelativeLayout layoutgallery;
	private android.widget.RelativeLayout myView;
	private com.common.view.pulltorefresh.PullToRefreshScrollView homebodyscrollview;
	private ImageView btn_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_safetrip);
		// 初始化控件
		initView();

		// 初始化数据
		initData();

		// 加载竞拍数据
		loadData();
	}

	private void initView() {
		
    	mRightIcon = (ImageView) findViewById(R.id.home_location_imageview);
    	mRightIcon.setOnClickListener(this);

    	mBtnBack = (ImageView) findViewById(R.id.btn_back);
    	mBtnBack.setOnClickListener(this);
    	
    	mViewSearch = findViewById(R.id.ab_search);
    	mViewSearch.setOnClickListener(this);

		// 轮播图
		ViewFlow viewFlow = (ViewFlow) findViewById(R.id.mHomeViewflow);// 获得viewFlow对象
		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.mHomeViewflowindic); // viewFlow下的indic
		bannerView = new BannerView(mContext, viewFlow, indic);
		bannerView.setOnItemClickListener(this);
		// 快速导航图
		ScrollLayout mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayoutTest);
		PageControlView pageControl = (PageControlView) findViewById(R.id.pageControl);
		fastLinkView = new FastLinkView(mContext, mScrollLayout, pageControl);
		fastLinkView.setOntemClickListener(this);
		myGridView = (MyGridView) findViewById(R.id.gv_top_list);
		this.homebodyscrollview = (PullToRefreshScrollView) findViewById(R.id.home_body_scrollview);
		this.myView = (RelativeLayout) findViewById(R.id.myView);
		this.layoutgallery = (RelativeLayout) findViewById(R.id.layout_gallery);
		this.indexsousuolinlayout = (LinearLayout) findViewById(R.id.index_sousuo_linlayout);
		this.homelocationimageview = (ImageView) findViewById(R.id.home_location_imageview);
		this.absearch = (LinearLayout) findViewById(R.id.ab_search);
		this.activityindexsearchcontent = (TextView) findViewById(R.id.activity_index_search_content);
		this.activityindexsousuoiv = (ImageView) findViewById(R.id.activity_index_sousuo_iv);
		this.btn_back = (ImageView) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);
		homelocationimageview.setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

	}

	/**
	 * 加载数据
	 */
	public void loadData() {

		// 构造Banner假数据
		List<BannerData> dataList = new ArrayList<BannerData>();
		dataList.add(new BannerData());
		dataList.add(new BannerData());
		dataList.add(new BannerData());
		dataList.add(new BannerData());
		dataList.add(new BannerData());
		dataList.add(new BannerData());
		dataList.add(new BannerData());
		bannerView.refreshData(dataList);

		// 构造FastLink假数据
		List<FastLinkData> fastList = new ArrayList<FastLinkData>();
		fastList.add(new FastLinkData(0, "车辆保险", R.drawable.icon_car_safe));

		fastList.add(new FastLinkData(1, "汽车保养", R.drawable.icon_car_maintain));

		fastList.add(new FastLinkData(2, "汽车商城", R.drawable.icon_car_store));

		fastList.add(new FastLinkData(3, "汽车租赁", R.drawable.icon_car_rent));

		fastList.add(new FastLinkData(4, "违章查询", R.drawable.icon_illegal_query));
		fastLinkView.refreshData(fastList);

		myGridView.setAdapter(new TopProdectAdapter(mContext, getData()));

	}

	private List<TopProduct> getData() {
		List<TopProduct> list = new ArrayList<TopProduct>();
		list.add(new TopProduct("丹麦进口 丹麦蓝罐曲奇饼干125g加仑子味下午茶点心",
				R.drawable.test_pic, "10.8", 157+""));
		list.add(new TopProduct("丹麦进口食品", R.drawable.test_pic, "10.8", 157+""));
		list.add(new TopProduct("丹麦进口食品", R.drawable.test_pic, "10.8", 157+""));
		list.add(new TopProduct("丹麦进口食品", R.drawable.test_pic, "10.8", 157+""));
		list.add(new TopProduct("丹麦进口食品", R.drawable.test_pic, "10.8", 157+""));
		list.add(new TopProduct("丹麦进口食品", R.drawable.test_pic, "10.8", 157+""));
		return list;
	}

	@Override
	public void onBannerItemClick(BannerData data, int position) {
//		showToast("点击了第" + position + "项数据");
	}

	@Override
	public void onFastLinkItemClick(FastLinkData bean) {
		switch (bean.getId()) {
		case 0:// 车辆保险
			startActivity(new Intent(mContext, BuyCarSafeActivity.class));
			break;
		case 1:// 汽车保养
			startActivity(new Intent(mContext, CarMainTainActivity.class));
			break;
		case 2:// 汽车商城
			startActivity(new Intent(mContext, CarListActivity.class));
			break;
		case 3:// 汽车租赁

			break;
		case 4:// 违章查询
			startActivity(new Intent(mContext, IllegalQueryActivity.class));
			break;
		}
//		showToast("点击了第" + bean.getName() + "项数据");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:// 返回
			this.finish();
			break;
		case R.id.home_location_imageview:
			TopbarMenuPopwindow popwindow = new TopbarMenuPopwindow(this);
			popwindow.showAsDropDown(homelocationimageview, DeviceUtils.getWidthMaxPx(this) - popwindow.getWidth(), 0);
			break;
		case R.id.ab_search:
			startActivity(new Intent(this, SearchActivity.class));
			break;
		}
	}
}
