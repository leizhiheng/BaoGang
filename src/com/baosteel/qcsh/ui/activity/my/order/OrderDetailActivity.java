package com.baosteel.qcsh.ui.activity.my.order;

import org.json.JSONObject;

import android.R.integer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsOrder;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.OrderDetailData;
import com.baosteel.qcsh.ui.fragment.myorder.OrderDetailBaseFragment;
import com.baosteel.qcsh.ui.fragment.myorder.OrderDetailFinishedFragment;
import com.baosteel.qcsh.ui.fragment.myorder.OrderDetailNotCheckFragment;
import com.baosteel.qcsh.ui.fragment.myorder.OrderDetailNotCommentFragment;
import com.baosteel.qcsh.ui.fragment.myorder.OrderDetailNotComsumeFragment;
import com.baosteel.qcsh.ui.fragment.myorder.OrderDetailNotPayFragment;
import com.baosteel.qcsh.ui.fragment.myorder.OrderDetailNotReceiveFragment;
import com.baosteel.qcsh.ui.fragment.myorder.OrderDetailNotSendFragment;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;

/**
 * 
 * @description 订单详情页面，不同状态的订单使用不同的Fragment对象来显示
 * @author blue
 * @Time 2015-9-30
 *
 */
public class OrderDetailActivity extends BaseActivity{
	
	/** 用于传递订单状态 **/
	public static final String EXTRA_ORDER_STATUS = "order.status";
	/** 用于传递订单Id **/
	public static final String EXTRA_ORDER_ID = "order.id";
	/** 用于传递订单类型 **/
	public static final String EXTRA_ORDER_TYPE = "order.type";
	
	private FrameLayout mContainer;
	private TextView mTvNoContentTip;
	
	private int mOrderStatus;
	private String mOrderId;
	private int mOrderType;
	
	private OrderDetailData mOrderDetailData;//订单详情数据实例
	private OrderDetailBaseFragment mFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_order_detail);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mOrderStatus = Integer.parseInt(getIntent().getStringExtra(EXTRA_ORDER_STATUS));
		mOrderId = getIntent().getStringExtra(EXTRA_ORDER_ID);
		//订单类型，默认是实物订单
		mOrderType = Integer.parseInt(getIntent().getStringExtra(EXTRA_ORDER_TYPE));
		Log.d(TAG, "mOrderId = "+mOrderId+", mOrderType = "+mOrderType);
	}
	
	private void initView() {
		
		setTitle("订单详情");
		mTitleBar.setBackgroud(R.color.theme_color_order);
		mContainer = (FrameLayout) findViewById(R.id.container);
		mTvNoContentTip = (TextView) findViewById(R.id.iv_no_contant_tip);
		
		switch (mOrderStatus) {
		case ConstantsOrder.ORDER_STATUS_NOT_CHECK:
			/*
			 * 待审核订单详情
			 */
			mFragment = new OrderDetailNotCheckFragment();
			
			break;
			
		case ConstantsOrder.ORDER_STATUS_NOT_PAY:
			/*
			 * 待付款订单详情
			 */
			mFragment = new OrderDetailNotPayFragment();
			
			break;
			
		case ConstantsOrder.ORDER_STATUS_NOT_SEND:
			/*
			 * 待发货订单详情
			 */
			mFragment = new OrderDetailNotSendFragment();
			
			break;
			
		case ConstantsOrder.ORDER_STATUS_NOT_RECEIVE:
			/*
			 * 待收货订单详情
			 */
			mFragment = new OrderDetailNotReceiveFragment();
			
			break;
			
		case ConstantsOrder.ORDER_STATUS_NOT_COMMENT:
			/*
			 * 待评价订单详情
			 */
			mFragment = new OrderDetailNotCommentFragment();
			
			break;
			
		case ConstantsOrder.ORDER_STATUS_NOT_COMSUME:
			/*
			 * 待消费订单详情
			 */
			mFragment = new OrderDetailNotComsumeFragment();
			
			break;
			
		case ConstantsOrder.ORDER_STATUS_FINISHED:
			/*
			 * 已完成订单详情
			 */
			mFragment = new OrderDetailFinishedFragment();
			
			break;

		default:
			break;
		}
		
		mFragment.setOrderMsg(mOrderId, mOrderType, mOrderStatus);
		getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragment).commit();
	}
	
	/**
	 * 
	 * @Description 下载订单详情
	 * @Author blue
	 * @Time 2015-9-30
	 */
	private void loadData() {
		RequestClient.queryAppOrderDetail(this, BaoGangData.getInstance().getUserId(), mOrderId, new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					mOrderDetailData = JSONParseUtils.getOrderDetailData(response);
				} else {
					showToast(JSONParseUtils.getString(response, "msg"));
				}
			}
			
			@Override
			public void onFinish() {
				super.onFinish();
				if (mOrderDetailData == null) {
					mTvNoContentTip.setVisibility(View.VISIBLE);
				} else {
					mTvNoContentTip.setVisibility(View.GONE);
					mFragment.setOrderDetailData(mOrderDetailData);
				}
			}
		});
		
	}
}
