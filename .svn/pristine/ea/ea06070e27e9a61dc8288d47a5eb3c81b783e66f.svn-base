package com.baosteel.qcsh.ui.activity.home.travel;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.utils.DialogUtil;
import com.common.view.fastlink.FastLinkData;
import com.common.view.fastlink.FastLinkView;
import com.common.view.fastlink.LnkToolsGridViewAdapter.BackItemClickListener;
import com.common.view.scrolllayout.PageControlView;
import com.common.view.scrolllayout.ScrollLayout;

/**
 * 旅行宝-签证
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class VisaActivity extends BaseActivity implements OnClickListener, BackItemClickListener {

	
	/** 标题栏 **/
	private TitleBar mTitleBar;

	/**快速导航**/
	private FastLinkView fastLinkView;
	
	/**开始搜索**/
	private Button searchBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_visa);

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
		mTitleBar.setTitle("签证");

		//快速导航图
		ScrollLayout mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayoutTest);
		PageControlView pageControl = (PageControlView) findViewById(R.id.pageControl);
		fastLinkView = new FastLinkView(mContext, mScrollLayout, pageControl);
		fastLinkView.setOntemClickListener(this);
		
        searchBtn = (Button) findViewById(R.id.startSearchBtn);
        searchBtn.setOnClickListener(this);
		
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
		
		// 构造FastLink假数据
		List<FastLinkData> fastList = new ArrayList<FastLinkData>();
		fastList.add(new FastLinkData(1, "日本", R.drawable.icon_country1));
		fastList.add(new FastLinkData(2, "韩国", R.drawable.icon_country2));
		fastList.add(new FastLinkData(3, "俄罗斯", R.drawable.icon_country3));
		fastList.add(new FastLinkData(4, "美国", R.drawable.icon_country4));
		fastList.add(new FastLinkData(5, "澳大利亚", R.drawable.icon_country5));
		fastList.add(new FastLinkData(6, "巴西", R.drawable.icon_country6));
		fastList.add(new FastLinkData(7, "瑞士", R.drawable.icon_country7));
		fastList.add(new FastLinkData(8, "希腊", R.drawable.icon_country8));
		fastLinkView.refreshData(fastList);

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
		// TODO Auto-generated method stub
		
	}
}
