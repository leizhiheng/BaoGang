package com.baosteel.qcsh.ui.activity.my.order;

import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsOrder;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.OrderItem;
import com.baosteel.qcsh.ui.adapter.EntityAndServiceOrderAdapter;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.common.view.pulltorefresh.PullToRefreshListView;

public class OrderListActivity extends BaseActivity implements OnRefreshListener2<ListView>, OnItemClickListener{

	public static final String EXTRAN_ORDER_STATUS = "order.status";
	
	private PullToRefreshListView mListView;
	
	private String mTitle;
	private int mOrderStatu;
	
	private EntityAndServiceOrderAdapter mAdapter;
	
	private int mCurPage = 1;
	private int mPageSize = 6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_order_list);
		
		initData();
		initView();
		
		loadData();
		
	}
	
	private void initData() {
		
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
		mOrderStatu = getIntent().getIntExtra(EXTRAN_ORDER_STATUS, ConstantsOrder.ORDER_STATUS_NOT_CHECK);
		
		mAdapter = new EntityAndServiceOrderAdapter(this, null);
		
//		/*
//		 * 订单状态
//		 * (0全部 1 待付款 2 待发货 3 待确认 4 待评价 5 已取消 6 已关闭 7 待审核 8 待消费 9 已完成)
//		 */
//		switch (mOrderStatu) {
//		case ConstantsOrder.ORDER_STATUS_NOT_CHECK:
//			
//			//待审核
//			mAdapter = new OrderCheckingAdapter(this, mDatas); 
//			
//			break;
//		case ConstantsOrder.ORDER_STATUS_NOT_PAY:
//			
//			//待付款
//			mAdapter = new OrderNotPayAdapter(this, mDatas);
//			
//			break;
//		case ConstantsOrder.ORDER_STATUS_NOT_SEND:
//			
//			//待发货
//			mAdapter = new OrderNotSentAdapter(this, mDatas);
//			
//			break;
//		case ConstantsOrder.ORDER_STATUS_NOT_RECEIVE:
//			
//			//待收货
//			mAdapter = new OrderNotReceiveAdapter(this, mDatas);
//			
//			break;
//		case ConstantsOrder.ORDER_STATUS_NOT_COMMENT:
//			
//			//待评价
//			mAdapter = new OrderNotCommentAdapter(this, mDatas);
//			
//			break;
//		case ConstantsOrder.ORDER_STATUS_RETURN:
//			
//			//预约/退换
//			mAdapter = new OrderReturnAdapter(this, mDatas);
//			
//			break;
//
//		default:
//			break;
//		}
	}
	
	private void initView() {
		setTitle(mTitle);
		mTitleBar.setBackgroud(R.color.theme_color_order);
		
		mListView = (PullToRefreshListView) findViewById(R.id.refresh_listview);
		mListView.setAdapter(mAdapter);
		mListView.setMode(Mode.PULL_FROM_END);
		mListView.setOnRefreshListener(this);
		mListView.setOnItemClickListener(this);
	}
	
	boolean showDialog = true;
	/**
	 * 
	 * @Description 下载数据
	 * @Author blue
	 * @Time 2015-9-28
	 */
	private void loadData() {
		//用户id，订单类型，订单状态
		String userId = BaoGangData.getInstance().getUserId();
		int orderType = ConstantsOrder.ORDER_TYPE_ENTITY_SERVICE;
		
		RequestClient.queryAppOrderList(this, userId, orderType, mOrderStatu, mCurPage, mPageSize, new RequestCallback<JSONObject>(showDialog) {
			
			@Override
			public void onFinish(){
				super.onFinish();
				mListView.onRefreshComplete();
				
				//首次加载数据显示对话框，后面则不显示加载对话框
				showDialog = false;
			}
			
			@Override
			public void onResponse(JSONObject response) {
				
				//不管是否下拉刷新，执行隐藏FooterView的动作
				mListView.onRefreshComplete();
				
				if (JSONParseUtils.isSuccessRequest(OrderListActivity.this, response)) {
					//解析订单数据
					List<OrderItem> tempData = JSONParseUtils.getOrderItems(response);
					
					//非空判断
					if(null == tempData || tempData.size() == 0){
						showToast("暂无数据");
						mListView.setMode(Mode.PULL_FROM_START);
						return;
					}
					
					//第一页，下拉刷新操作
					if(mCurPage <= 1){
						mAdapter.refreshData(tempData);
					}else{
						
						//第二页后，上拉加载数据
						mAdapter.appendData(tempData);
					}
					
					
					//无下一页数据,判断是否还有下一页
					Mode mode = (tempData.size() < mPageSize) ? Mode.PULL_FROM_START : Mode.BOTH;
					mListView.setMode(mode);
					
					//pageIndex++
					if(tempData.size() >= mPageSize){
						mCurPage++;
					}
					
				}
			}
			
		});
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//		// 获取当前点击的订单
//		position -= mListView.getHeadViewCount();
//		OrderItem item = (OrderItem) mAdapter.getItem(position);
//
//		Intent intent = new Intent(mContext, OrderDetailActivity.class);
//		// 传入订单状态
//		intent.putExtra(OrderDetailActivity.EXTRA_ORDER_STATUS, item.getStatus());
//		// 传入订单Id
//		intent.putExtra(OrderDetailActivity.EXTRA_ORDER_ID, item.getOrderId());
//		startActivity(intent);
	}
	
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		mCurPage = 1;
		loadData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		loadData();
	}
}

