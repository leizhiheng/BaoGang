package com.baosteel.qcsh.ui.activity.home.travel;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.model.ShipLine;
import com.baosteel.qcsh.ui.adapter.TravelShipLineAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.fastlink.FastLinkData;
import com.common.view.fastlink.FastLinkView;
import com.common.view.fastlink.LnkToolsGridViewAdapter.BackItemClickListener;
import com.common.view.scrolllayout.PageControlView;
import com.common.view.scrolllayout.ScrollLayout;

/**
 * 旅行宝-邮轮
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class PassengerLinerActivity extends BaseActivity implements OnClickListener, BackItemClickListener {

	
	/** 标题栏 **/
	private TitleBar mTitleBar;
	
	/**快速导航**/
	private FastLinkView fastLinkView;
	
	/**热门路线列表**/
	private ListView dataListView;

	/**热门路线**/
	private TravelShipLineAdapter adatper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_passenger_liner);

		// 初始化控件
		initView();

		// 初始化数据
		initData();
	}

	/**
	 * 初始化数据
	 */
	public void initView() {

		// 标题栏
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setRightIconVisibility(0, View.VISIBLE);
		mTitleBar.setBackgroudValue(Constants.getTypeColor(Constants.TYPE_TRAVEL));
		mTitleBar.setTitle("邮轮");

		//快速导航图
		ScrollLayout mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayoutTest);
		PageControlView pageControl = (PageControlView) findViewById(R.id.pageControl);
		fastLinkView = new FastLinkView(mContext, mScrollLayout, pageControl);
		fastLinkView.setOntemClickListener(this);
		
		//热门景点
		dataListView = (ListView) findViewById(R.id.dataListview);
		adatper = new TravelShipLineAdapter(mContext);
		dataListView.setAdapter(adatper);
	}

	/**
	 * 初始化数据
	 */
	public void initData() {

		// 构造FastLink假数据
		List<FastLinkData> fastList = new ArrayList<FastLinkData>();
		fastList.add(new FastLinkData(1, "日本", R.drawable.icon_youlun1));
		fastList.add(new FastLinkData(2, "亚洲", R.drawable.icon_youlun2));
		fastList.add(new FastLinkData(3, "美洲", R.drawable.icon_youlun3));
		fastList.add(new FastLinkData(4, "欧洲", R.drawable.icon_youlun4));
		fastList.add(new FastLinkData(5, "北欧", R.drawable.icon_youlun5));
		fastList.add(new FastLinkData(6, "夏威夷", R.drawable.icon_youlun6));
		fastList.add(new FastLinkData(7, "极地", R.drawable.icon_youlun7));
		fastList.add(new FastLinkData(0, "河轮", R.drawable.icon_youlun8));
		fastLinkView.refreshData(fastList);

		//构造热门路线
		List<ShipLine> hotLineList = new ArrayList<ShipLine>();
		hotLineList.add(new ShipLine(1, "日航航线-天海邮轮[新新世界]-京东+长椅5天4晚", R.drawable.bg_list_youlun1));
		hotLineList.add(new ShipLine(2, "日航航线-天海邮轮[新新世界]-京东+长椅5天4晚", R.drawable.bg_list_youlun2));
		hotLineList.add(new ShipLine(3, "日航航线-天海邮轮[新新世界]-京东+长椅5天4晚", R.drawable.bg_list_youlun3));
		hotLineList.add(new ShipLine(4, "日航航线-天海邮轮[新新世界]-京东+长椅5天4晚", R.drawable.bg_list_youlun1));
		hotLineList.add(new ShipLine(5, "日航航线-天海邮轮[新新世界]-京东+长椅5天4晚", R.drawable.bg_list_youlun2));
		hotLineList.add(new ShipLine(6, "日航航线-天海邮轮[新新世界]-京东+长椅5天4晚", R.drawable.bg_list_youlun3));
		hotLineList.add(new ShipLine(7, "日航航线-天海邮轮[新新世界]-京东+长椅5天4晚", R.drawable.bg_list_youlun1));
		hotLineList.add(new ShipLine(0, "日航航线-天海邮轮[新新世界]-京东+长椅5天4晚", R.drawable.bg_list_youlun2));
		adatper.refreshData(hotLineList);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.startSearchBtn:
			
			//开始搜索
			
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode != RESULT_OK){
			return;
		}
		
		switch (requestCode) {
		}
	}

	@Override
	public void onFastLinkItemClick(FastLinkData bean) {
		
	}
}
