package com.baosteel.qcsh.ui.activity.home.healthy;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HousingEstateNotice;
import com.baosteel.qcsh.model.ShouldKnowData;
import com.baosteel.qcsh.ui.adapter.HousingEstateNoticesAdapter;
import com.baosteel.qcsh.ui.adapter.ShouldKnowAdapter;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshListView;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;

/**
 * 
 * @description 页面：健康宝-》社区健康-》健康服务通知-》通知详情
 * @author blue
 * @Time 2015-9-15
 *
 */
public class MsgDetailActivity  extends BaseActivity{

	public static final String EXTRA_KEY_MSG_TYPE = "msg.type";
	public static final int MSG_NOTICES = 0;
	public static final int MSG_TIPS = 1;

	private int mMsgType;
	private String mTitle;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msg_detail);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mMsgType = getIntent().getIntExtra(EXTRA_KEY_MSG_TYPE, MSG_NOTICES);
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
	}
	
	private void initView() {
		setTitle(mTitle);
		mTitleBar.setBackgroudColor(getResources().getColor(R.color.theme_color_healthy));
		if (mMsgType == MSG_NOTICES) {
			findViewById(R.id.container).setBackgroundResource(R.drawable.ic_healthy_msg_notice);
		} else {
			findViewById(R.id.container).setBackgroundResource(R.drawable.ic_healthy_msg_tip);
		}
	}
	
	private void loadData() {
	}
}

