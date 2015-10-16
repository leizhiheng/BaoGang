package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.MyReleaseRecordData;
import com.baosteel.qcsh.ui.adapter.MyReleaseRecordAdapter;
import com.common.base.BaseActivity;
import com.common.utils.DensityUtils;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.common.view.pulltorefresh.PullToRefreshScrollView;
import com.common.view.swipemenulistview.SwipeMenu;
import com.common.view.swipemenulistview.SwipeMenuCreator;
import com.common.view.swipemenulistview.SwipeMenuItem;
import com.common.view.swipemenulistview.SwipeMenuListView;

/**
 * 
 * @description 页面： 乐居宝-》我的乐居宝-》我的发布
 * @author blue
 * @Time 2015-9-14
 *
 */
public class MyReleaseRecordeActivity extends BaseActivity{

//	private PullToRefreshScrollView mScrollView;
	private SwipeMenuListView mListView;
	
	private MyReleaseRecordAdapter mAdapter;
	private List<MyReleaseRecordData> mData;
	
	private String mTitle;
	
	private int mCurPage;
	private int mPageSize = 20;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_release_record);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
		
		mData = new ArrayList<MyReleaseRecordData>();
		mAdapter = new MyReleaseRecordAdapter(this, mData);
	}
	
	private void initView() {
		setTitle(mTitle);
		
		/*
		 * 下拉刷新ScrollView
		 */
//		mScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_to_refresh_scrollview);
//		mScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
//
//			@Override
//			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
//				new GetDataTask().execute();
//			}
//		});
//		mScrollView.setMode(Mode.PULL_FROM_END);
		
		/*
		 * 侧滑菜单ListView
		 */
		mListView = (SwipeMenuListView) findViewById(R.id.swipe_listview);
		mListView.setAdapter(mAdapter);
		initSwipeListView();
	}
	
	private void loadData() {
		for (int i = mPageSize * mCurPage; i < (mCurPage + 1) * mPageSize; i++) {
			mData.add(new MyReleaseRecordData());
		}
		mCurPage++;
		mAdapter.notifyDataSetChanged();
	}
	
	/**
	 * 
	 * @Description 初始化SwipeMenuListView
	 * @Author blue
	 * @Time 2015-9-14
	 */
	private void initSwipeListView() {
		// step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // 删除
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(R.drawable.selector_btn_gray);
                deleteItem.setWidth(DensityUtils.dp2px(MyReleaseRecordeActivity.this, 70));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
                
                // 编辑
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getApplicationContext());
                editItem.setBackground(R.drawable.selector_btn_yellow);
                editItem.setWidth(DensityUtils.dp2px(MyReleaseRecordeActivity.this, 70));
                editItem.setTitle("编辑");
                editItem.setTitleSize(16);
                editItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(editItem);
                
                // 刷新
                SwipeMenuItem refreshItem = new SwipeMenuItem(
                        getApplicationContext());
                refreshItem.setBackground(R.drawable.selector_btn_orange);
                refreshItem.setWidth(DensityUtils.dp2px(MyReleaseRecordeActivity.this, 70));
                refreshItem.setTitle("刷新");
                refreshItem.setTitleSize(16);
                refreshItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(refreshItem);
                
                // 分享
                SwipeMenuItem shareItem = new SwipeMenuItem(
                        getApplicationContext());
                shareItem.setBackground(R.drawable.selector_btn_red);
                shareItem.setWidth(DensityUtils.dp2px(MyReleaseRecordeActivity.this, 70));
                shareItem.setTitle("分享");
                shareItem.setTitleSize(16);
                shareItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(shareItem);

//                // create "delete" item
//                SwipeMenuItem deleteItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                        0x3F, 0x25)));
//                // set item width
//                deleteItem.setWidth(DensityUtils.dp2px(MyReleaseRecordeActivity.this, 70));
//                // set a icon
//                deleteItem.setIcon(R.drawable.ic_delete);
//                // add to menu
//                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                    	// delete
                        showToast("Click to delete");
                        break;
                    case 1:
                        // edit
                    	showToast("Click to edit");
                        break;
                    case 2:
                    	// refresh
                    	showToast("Click to refresh");
                    	break;
                    case 3:
                    	// share
                    	showToast("Click to share");
                    	break;
                }
                return false;
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // set MenuStateChangeListener
        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
            }

            @Override
            public void onMenuClose(int position) {
            }
        });

        // other setting
//		listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
//        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view,
//                                           int position, long id) {
//                //Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MyReleaseRecordeActivity.this, HouseDetailActivity.class));
//            	return false;
//            }
//        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(MyReleaseRecordeActivity.this, HouseDetailActivity.class));
			}
        });
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
			
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
//			mScrollView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

}
