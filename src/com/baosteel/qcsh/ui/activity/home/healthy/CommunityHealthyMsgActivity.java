package com.baosteel.qcsh.ui.activity.home.healthy;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HousingEstateNotice;
import com.baosteel.qcsh.model.ShouldKnowData;
import com.baosteel.qcsh.ui.adapter.HousingEstateNoticesAdapter;
import com.baosteel.qcsh.ui.adapter.ShouldKnowAdapter;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.common.view.pulltorefresh.PullToRefreshListView;

public class CommunityHealthyMsgActivity extends BaseActivity{

	public static final String EXTRA_KEY_MSG_TYPE = "msg.type";
	public static final int MSG_NOTICES = 0;
	public static final int MSG_TIPS = 1;
	
	private PullToRefreshListView mListView;
	
	/**
	 * 服务通知
	 */
	private List<HousingEstateNotice> mNotices;
	private HousingEstateNoticesAdapter mNoticeAdapter;
	
	/**
	 * 健康小常识
	 */
	private List<ShouldKnowData> mTips;
	private ShouldKnowAdapter mTipsAdapter;

	private int mCurpageIndex;
	private int mPageSize = 10;
	
	private int mMsgType;
	private String mTitle;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_community_healthy_msg);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mMsgType = getIntent().getIntExtra(EXTRA_KEY_MSG_TYPE, MSG_NOTICES);
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
		
		if (mMsgType == MSG_NOTICES) {
			mNotices = new ArrayList<HousingEstateNotice>();
			mNoticeAdapter = new HousingEstateNoticesAdapter(this, mNotices);
		} else {
			mTips = new ArrayList<ShouldKnowData>();
			mTipsAdapter = new ShouldKnowAdapter(this, mTips);
		}
	}
	
	private void initView() {
		setTitle(mTitle);
		mTitleBar.setBackgroudColor(getResources().getColor(R.color.theme_color_healthy));
		
		mListView = (PullToRefreshListView) findViewById(R.id.refresh_listview);
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
				Intent intent = new Intent(CommunityHealthyMsgActivity.this, MsgDetailActivity.class);
				if (mMsgType == MSG_NOTICES) {
					intent.putExtra(EXTRA_KEY_TITLE, "通知详情");
					intent.putExtra(MsgDetailActivity.EXTRA_KEY_MSG_TYPE, MSG_NOTICES);
				} else if (mMsgType == MSG_TIPS) {
					intent.putExtra(EXTRA_KEY_TITLE, "小常识");
					intent.putExtra(MsgDetailActivity.EXTRA_KEY_MSG_TYPE, MSG_TIPS);
				}
				startActivity(intent);
			}
		});
		mListView.setMode(Mode.PULL_FROM_END);
		if (mMsgType == MSG_NOTICES) {
			mListView.setAdapter(mNoticeAdapter);
		} else if (mMsgType == MSG_TIPS) {
			mListView.setAdapter(mTipsAdapter);
		}
	}
	
	private void loadData() {
		if (mMsgType == MSG_NOTICES) {
			for (int i = 0; i < 10; i++) {
				mNotices.add(new HousingEstateNotice("关于宝钢社区9月19号免费体检通知", "宝钢社区", null, "2015-9-14"));
			}
			mNoticeAdapter.notifyDataSetChanged();
		} else if (mMsgType == MSG_TIPS) {
			for (int i = 0; i < 10; i++) {
				mTips.add(new ShouldKnowData("夏天老人别做三类运动", "2015-9-14"));
			}
			mTipsAdapter.notifyDataSetChanged();
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
			
			// Call onRefreshComplete when the list has been refreshed.
			mListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
}
