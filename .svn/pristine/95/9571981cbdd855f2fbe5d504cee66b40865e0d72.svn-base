package com.baosteel.qcsh.ui.activity.home.travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.ui.adapter.TravelInlandAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.fastlink.FastLinkData;
import com.common.view.fastlink.LnkToolsGridViewAdapter.BackItemClickListener;

/**
 * 旅行宝-国内游
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class OutboundActivity extends BaseActivity implements OnClickListener, BackItemClickListener {

	
	/** 标题栏 **/
	private TitleBar mTitleBar;
	
	/**热门景点列表**/
	private ListView dataListView;

	/**热门景点**/
	private TravelInlandAdapter adatper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_outbound);

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
		mTitleBar.setTitle("出境游");

		//热门景点
		dataListView = (ListView) findViewById(R.id.dataListview);
		adatper = new TravelInlandAdapter(mContext);
		dataListView.setAdapter(adatper);
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
		

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
