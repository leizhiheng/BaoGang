package com.baosteel.qcsh.ui.activity.home.travel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.ui.fragment.home.travel.TraveDestinationlFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 目的地
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class DestinationActivity extends BaseActivity {

	
	/** 标题栏 **/
	private TitleBar mTitleBar;
	
	/**目的地fragment**/
	private TraveDestinationlFragment fragment;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_destination);

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
		mTitleBar.setTitle("选择目的地");
		
		fragment = new TraveDestinationlFragment();
		showFragment(R.id.frame_layout, fragment);
	}

	/**
	 * 初始化数据
	 */
	public void initData() {

	}

}
