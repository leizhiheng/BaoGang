package com.baosteel.qcsh.ui.activity.my.order;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.OrderItem;
import com.baosteel.qcsh.ui.adapter.OrderTalkRecordAdapter;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.common.view.pulltorefresh.PullToRefreshListView;

/**
 * 协商记录
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-22
 */
public class ReturnTalkRecordActivity extends BaseActivity implements OnRefreshListener<ListView>{

	public static final String EXTRAN_ORDER_TYPE = "order.type";
	
	/**Listview记录**/
	private PullToRefreshListView mListView;
	
	/**适配器**/
	private BaseAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_talk_record);
		
		//初始化控件
		initView();
		
		//初始化数据
		initData();
		
		//加载数据
		loadData();
		
	}
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		
	}
	
	/**
	 * 初始化控件
	 */
	private void initView() {
		
		//标题
		setTitle("协商记录");
		mTitleBar.setBackgroud(R.color.theme_color_order);
		
		mListView = (PullToRefreshListView) findViewById(R.id.refresh_listview);
		mListView.setAdapter(mAdapter);
		mListView.setMode(Mode.PULL_FROM_END);
		mListView.setOnRefreshListener(this);
		
		//预约/退换
		mAdapter = new OrderTalkRecordAdapter(this);
		mListView.setAdapter(mAdapter);
	}
	
	/**
	 * 加载数据
	 */
	private void loadData() {
		
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		
	}
}

