package com.baosteel.qcsh.ui.activity.common;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.SingleSelectItemData;
import com.baosteel.qcsh.ui.adapter.SingleSelectAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshListView;

/**
 * 
 * @description 页面显示了一个单选的ListView
 * @author blue
 * @Time 2015-9-9
 *
 */
public class SingleSelectActivity extends BaseActivity{

	/**
	 * 用于接收页面的标志位
	 */
	public static final String EXTRA_KEY_PAGE_INDEX = "page.index";
	
	/** 选择价格 */
	public static final int PAGE_INDEX_CHOOSE_PRICE = 0;
	
	private TitleBar mTitleBar;
	private ListView mListView;
	private SingleSelectAdapter mAdapter;
	private List<SingleSelectItemData> mDatas;
	private String mTitle;
	private int mPageIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_select);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
		mPageIndex = getIntExtra(EXTRA_KEY_PAGE_INDEX);
		
		mDatas = new ArrayList<SingleSelectItemData>();
		mAdapter = new SingleSelectAdapter(this, mDatas);
		
		mAdapter.setRightIconId(R.drawable.icon_selected);
		mAdapter.setTextColor(getResources().getColor(R.color.orange_red));
		mAdapter.setShowRightIcon(true);
	}
	
	private void initView() {
		setTitle(mTitle);
		
		mListView = (ListView) findViewById(R.id.listview);
		mListView.setAdapter(mAdapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mDatas.get(position).setSelected(true);
				mAdapter.notifyDataSetChanged();
				
				Intent intent = new Intent();
				intent.putExtra(EXTRA_KEY_RESULT, mDatas.get(position).getTitle());
				setResult(0, intent);
				finish();
			}
		});
	}
	
	private void loadData() {
		
		if (mPageIndex == PAGE_INDEX_CHOOSE_PRICE) {
			mDatas.add(new SingleSelectItemData("600以下", 0));
			mDatas.add(new SingleSelectItemData("600-1200元", 0));
			mDatas.add(new SingleSelectItemData("1200-1500元", 0));
			mDatas.add(new SingleSelectItemData("1500-2000元", 0));
			mDatas.add(new SingleSelectItemData("2000-3000元", 0));
			mDatas.add(new SingleSelectItemData("3000-5000元", 0));
			mDatas.add(new SingleSelectItemData("5000-8000元", 0));
			mDatas.add(new SingleSelectItemData("8000以上", 0));
		}
		mDatas.get(0).setSelected(true);
		mAdapter.notifyDataSetChanged();
	}
	
}
