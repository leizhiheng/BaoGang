package com.baosteel.qcsh.ui.activity.home.travel;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.ui.adapter.ViewPagerFragmentAdapter;
import com.baosteel.qcsh.ui.fragment.home.travel.TravelPlanMultipleFragment;
import com.baosteel.qcsh.ui.fragment.home.travel.TravelPlanSingleFragment;
import com.baosteel.qcsh.ui.view.TabViewPagerIndicator;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.banner.BannerData;
import com.common.view.banner.BannerView;
import com.common.view.banner.IOnBannerItenClick;
import com.common.view.viewflow.CircleFlowIndicator;
import com.common.view.viewflow.ViewFlow;

/**
 * 旅行宝-保险
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class InsuranceActivity extends BaseActivity implements IOnBannerItenClick {

	
	/**标题栏**/
	private TitleBar mTitleBar;
	
	/**Banner轮播图**/
    private BannerView bannerView;
	
	private TabViewPagerIndicator mIndicator;

	private ViewPager viewpager;

	private ViewPagerFragmentAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insurance);
		
		//初始化控件
		initView();
		
		//初始化数据
		initData();
	}

	/**
	 * 初始化数据
	 */
	public void initView() {

		//标题栏
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setRightIconVisibility(0, View.VISIBLE);	
		mTitleBar.setBackgroudValue(Constants.getTypeColor(Constants.TYPE_TRAVEL));
		mTitleBar.setTitle("保险预定");
		
		//轮播图
        ViewFlow viewFlow = (ViewFlow) findViewById(R.id.mHomeViewflow);// 获得viewFlow对象
        CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.mHomeViewflowindic); // viewFlow下的indic
        bannerView = new BannerView(mContext, viewFlow, indic);
        bannerView.setOnItemClickListener(this);

		
		
		viewpager = (ViewPager) findViewById(R.id.water_pager);
		adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), getFragments());
		viewpager.setAdapter(adapter);
		
		mIndicator = (TabViewPagerIndicator) findViewById(R.id.indicator);
		//设置Tab标题，并且根据标题个数创建一样个数的Tab
		mIndicator.setTabTitles(getTitles());
		//设置ViewPager
		mIndicator.setViewPager(viewpager);
		//设置选中颜色
		mIndicator.setColor(Constants.getTypeColor(Constants.TYPE_TRAVEL));
		//设置选中项
		mIndicator.setCurrentItem(0);
	}

	/**
	 * 获取标题
	 * @return
	 */
	private String[] getTitles() {
		String[] list = new String[]{"单次计划", "一年多次计划"};
		return list;
	}

	/**
	 * 单次计划，一年多次计划
	 * 
	 * @return
	 */
	private List<Fragment> getFragments() {
		List<Fragment> list = new ArrayList<Fragment>();
		list.add(new TravelPlanSingleFragment());
		list.add(new TravelPlanMultipleFragment());
		return list;
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
		
		//构造Banner假数据
        List<BannerData> dataList = new ArrayList<BannerData>();
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        bannerView.refreshData(dataList);
	}

	@Override
	public void onBannerItemClick(BannerData data, int position) {
		// TODO Auto-generated method stub
		
	}
}
