package com.baosteel.qcsh.ui.fragment.myorder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsOrder;
import com.baosteel.qcsh.dialog.SimpleMsgDialog;
import com.baosteel.qcsh.dialog.SingleSelectListDialog;
import com.baosteel.qcsh.dialog.SingleSelectListDialog.onOkClickListener;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.CancelOrderReason;
import com.baosteel.qcsh.model.OrderDetailData;
import com.baosteel.qcsh.model.OrderItem;
import com.baosteel.qcsh.model.OrderProduct;
import com.baosteel.qcsh.ui.activity.OnlinePaymentActivity;
import com.baosteel.qcsh.ui.activity.my.order.OrderDetailActivity;
import com.baosteel.qcsh.ui.activity.store.StoreMainActivity;
import com.baosteel.qcsh.ui.adapter.EntityAndServiceOrderAdapter;
import com.baosteel.qcsh.ui.adapter.OrderProductsAdapter;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseFragment;
import com.common.view.other.ListViewInScrollView;

public class OrderDetailBaseFragment extends BaseFragment implements OnClickListener{

	/**订单ID**/
	protected String mOrderId;
	/**订单类型**/
	protected int mOrderType;
	/**订单状态**/
	protected int mOrderStatus;
	
	protected OrderDetailData mOrderDetailData;
	protected boolean isSettedView;
	/** 订单中商品列表 **/
	protected ListViewInScrollView mListView;
	public TextView mTvShopName;//店铺名称
	public TextView mTv_order_state;
    public TextView mTv_service_tips;
    public TextView mTv_order_price;
    public TextView mTv_shipment;
    public LinearLayout mLin_back_order;
    public TextView mTv_payment_tips;
    public TextView mTv_good_receiver;
    public TextView mTv_good_send_address;
    public TextView mTv_user_info;
    public TextView mTv_address;
    public LinearLayout mLin_service_order;
    public TextView mTv_time;
    public RelativeLayout mRel_logistics;
    public LinearLayout mLin_begin;
    public Button mBtn_again_buy;
    public Button mBtn_refunds;
    public TextView mTv_bill_header;
    public TextView mTv_bill_detail;
    public TextView mTv_send_way;
    public TextView mTv_receive_place;
    public TextView mTv_seller_phone;
    public TextView mTv_price;
    public TextView mTv_shipment_price;
    public TextView mTv_cash;
    public TextView mTv_gift;
    public TextView mTv_heath;
    public TextView mTv_qicai;
    public TextView mTv_rear_pay;
    public TextView mTvOrderCode;//订单ID
    public TextView mTvApayOrderCode;//支付宝支付ID
    public TextView mTvOrderCommitTime;//订单成交时间 
    
	/*
	 * 底部状态按钮
	 */
	TextView btnGoPay;					// 去付款
	TextView btnBuyAgain;
	TextView btnDeleteOrder;			// 删除订单（交易完成后可取消订单）
	TextView btnComment;				// 评价（或追加评价）
	TextView btnCheckLogistics;			// 查看物流
	TextView btnConfirmReceive;			// 确认收货
	TextView btnCancelOrder;			// 取消订单
	TextView btnNoticeSeller;			// 提醒卖家
	TextView btnContactSeller;			// 联系订单
	TextView btnRufundAndChange;		// 退货/换货
	TextView btnOrderDetail;            // 订单详情
	List<View> mBtnViews;				// 用于管理底部按钮View

	private int mBtnIndex;
    

	/** 订单中商品列表的适配器 **/
	protected OrderProductsAdapter mAdapter;
	protected List<OrderProduct> mProducts;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_order_detail, null);
		return fragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initData();
		initView();
		
		setBottomBtns(mOrderStatus);

	}

	private void initData() {
		mProducts = new ArrayList<OrderProduct>();
		mAdapter = new OrderProductsAdapter(mContext, mProducts);
		mBtnViews = new ArrayList<View>();
	}

	private void initView() {
		mListView = (ListViewInScrollView) findViewById(R.id.listview);
		mListView.setAdapter(mAdapter);

		mTvShopName = (TextView) findViewById(R.id.tv_shop_name);
		mTv_order_state = (TextView) findViewById(R.id.tv_order_state);
        mTv_service_tips = (TextView) findViewById(R.id.tv_service_tips);
        mTv_order_price = (TextView) findViewById(R.id.tv_order_price);
        mTv_shipment = (TextView) findViewById(R.id.tv_shipment);
        mLin_back_order = (LinearLayout) findViewById(R.id.lin_back_order);
        mTv_payment_tips = (TextView) findViewById(R.id.tv_payment_tips);
        mTv_good_receiver = (TextView) findViewById(R.id.tv_good_receiver);
        mTv_good_send_address = (TextView) findViewById(R.id.tv_good_send_address);
        mTv_user_info = (TextView) findViewById(R.id.tv_user_info);
        mTv_address = (TextView) findViewById(R.id.tv_address);
        mLin_service_order = (LinearLayout) findViewById(R.id.lin_service_order);
        mTv_time = (TextView) findViewById(R.id.tv_time);
        mRel_logistics = (RelativeLayout) findViewById(R.id.rel_logistics);
        mLin_begin = (LinearLayout) findViewById(R.id.lin_begin);
        mBtn_again_buy = (Button) findViewById(R.id.btn_again_buy);
        mBtn_refunds = (Button) findViewById(R.id.btn_refunds);
        mTv_bill_header = (TextView) findViewById(R.id.tv_bill_header);
        mTv_bill_detail = (TextView) findViewById(R.id.tv_bill_detail);
        mTv_send_way = (TextView) findViewById(R.id.tv_send_way);
        mTv_receive_place = (TextView) findViewById(R.id.tv_receive_place);
        mTv_seller_phone = (TextView) findViewById(R.id.tv_seller_phone);
        mTv_price = (TextView) findViewById(R.id.tv_price);
        mTv_shipment_price = (TextView) findViewById(R.id.tv_shipment_price);
        mTv_cash = (TextView) findViewById(R.id.tv_cash);
        mTv_gift = (TextView) findViewById(R.id.tv_gift);
        mTv_heath = (TextView) findViewById(R.id.tv_heath);
        mTv_qicai = (TextView) findViewById(R.id.tv_qicai);
        mTv_rear_pay = (TextView) findViewById(R.id.tv_rear_pay);
        mTvOrderCode = (TextView) findViewById(R.id.tv_order_id);
        mTvApayOrderCode = (TextView) findViewById(R.id.tv_apay_order_id);
        mTvOrderCommitTime = (TextView) findViewById(R.id.tv_order_commit_time);
    
        if (mOrderType == ConstantsOrder.ORDER_TYPE_SERVICE) {
        	mLin_service_order.setVisibility(View.VISIBLE);
		}
        
        //初始化顶部按钮
        initBottomBtns();
        
        mTvShopName.setOnClickListener(this);

	}
	
	private void initBottomBtns() {
		//订单状态按钮
		mBtnViews = new ArrayList<View>();
		btnGoPay = (TextView) findViewById(R.id.btn_gopay);
		btnBuyAgain = (TextView) findViewById(R.id.btn_buy_again);
		btnDeleteOrder = (TextView) findViewById(R.id.btn_delete_order);
		btnComment = (TextView) findViewById(R.id.btn_comment_order);
		btnCheckLogistics = (TextView) findViewById(R.id.btn_check_logistics);
		btnConfirmReceive = (TextView) findViewById(R.id.btn_confirm_receive);
		btnCancelOrder = (TextView) findViewById(R.id.btn_cancel_order);
		btnNoticeSeller = (TextView) findViewById(R.id.btn_notice_seller);
		btnContactSeller = (TextView) findViewById(R.id.btn_contact_seller);
		btnRufundAndChange = (TextView) findViewById(R.id.btn_refund_and_change);
		btnOrderDetail = (TextView) findViewById(R.id.btn_order_detail);

		mBtnViews.add(btnGoPay);// 0 去付款
		mBtnViews.add(btnBuyAgain);// 1 再次购买
		mBtnViews.add(btnCancelOrder);// 2 取消订单
		mBtnViews.add(btnCheckLogistics);// 3 查看物流
		mBtnViews.add(btnComment);// 4 评论
		mBtnViews.add(btnConfirmReceive);// 5 确认收货
		mBtnViews.add(btnDeleteOrder);// 6 删除订单
		mBtnViews.add(btnNoticeSeller);// 7 提醒卖家
		mBtnViews.add(btnContactSeller);// 8 联系卖家
		mBtnViews.add(btnRufundAndChange);// 9 退货/换货
	}
	
	/**
	 * 
	 * @Description 将订单标号、订单类型、订单状态传递过来
	 * @Author blue
	 * @Time 2015-10-16
	 */
	public void setOrderMsg(String orderId, int orderType, int orderStatus) {
		mOrderId = orderId;
		mOrderType = orderType;
		mOrderStatus = orderStatus;
	}
	
	/**
	 * 
	 * @Description 获取订单详情后，填充数据
	 * @Author blue
	 * @Time 2015-10-16
	 */
	public void setOrderDetailData(OrderDetailData data) {
		mOrderDetailData = data;
		
		setView();
	}
	
	/**
	 * 
	 * @Description 填充订单数据
	 * @Author blue
	 * @Time 2015-10-16
	 */
	public void setView() {
		mTv_order_price.setText(mOrderDetailData.getPrice());
		mTv_shipment.setText(mOrderDetailData.getShipment());
		mTv_user_info.setText(mOrderDetailData.getName()+"    "+mOrderDetailData.getTelephone());
		mTv_address.setText(mOrderDetailData.getAddress());
		mTvShopName.setText(mOrderDetailData.getSellerName());
		
		mProducts.addAll(mOrderDetailData.getGoodsInfoList());
		mAdapter.notifyDataSetChanged();
		
		mTv_bill_header.setText(mOrderDetailData.getInvoiceStart());
		mTv_bill_detail.setText(mOrderDetailData.getInvoiceContent());
		mTv_price.setText(mOrderDetailData.getPrice());
		mTv_shipment_price.setText(mOrderDetailData.getShipment());
		mTv_cash.setText(mOrderDetailData.getCashCouponPrice());
		mTv_gift.setText(mOrderDetailData.getGiftCard());
		mTv_heath.setText(mOrderDetailData.getHealthPoint());
		mTv_qicai.setText(mOrderDetailData.getSevenColorCoin());
		mTv_rear_pay.setText(mOrderDetailData.getPrice());
		mTvOrderCode.setText(mOrderDetailData.getOrderCode());
		mTvOrderCommitTime.setText(mOrderDetailData.getOrderTime());
	}
	
	/**
	 * 
	 * @Description 根据订单的状态判断底部按钮的显示与隐藏
	 * @Author blue
	 * @Time 2015-9-21
	 */
	public void setBottomBtns(int orderState) {
		
		hideBtns();
		
		int [] btnIndexs = null;
		switch (orderState) {
		
		case ConstantsOrder.ORDER_STATUS_CANCELED:// 已取消
			btnIndexs = new int[] { 6 };// 显示“删除订单”按钮
			break;
			
		case ConstantsOrder.ORDER_STATUS_NOT_CHECK:// 待审核
			btnIndexs = new int[] { 2, 7 };// 显示“取消订单”,"提醒卖家"按钮
			break;

		case ConstantsOrder.ORDER_STATUS_NOT_PAY:// 待付款
			btnIndexs = new int[] { 0, 2, 8 };// 显示"去付款"，“取消订单”,"联系卖家"按钮

			break;
		case ConstantsOrder.ORDER_STATUS_NOT_SEND:// 待发货
			btnIndexs = new int[] { 2, 7 };// 显示“取消订单”， “提醒卖家”按钮
			break;

		case ConstantsOrder.ORDER_STATUS_NOT_RECEIVE:// 待收货
			btnIndexs = new int[] { 3, 5, 2 };// 显示"查看物流"，“确认收货”, "取消订单"按钮
			break;

		case ConstantsOrder.ORDER_STATUS_NOT_COMMENT:// 待评价
			btnComment.setText("晒单评价");
			btnIndexs = new int[] { 1, 6, 4, 9 };// 显示"再次购买"，“删除订单”，“晒单评价“,"退或/换货"按钮
			break;
			
		case ConstantsOrder.ORDER_STATUS_FINISHED:// 交易完成
			btnComment.setText("追加评价");
			btnIndexs = new int[] { 1, 6, 4, 9 };// 显示"再次购买"，“删除订单”，“晒单评价“,"退或/换货"按钮
			break;

		default:
			break;
		}
		
		if (btnIndexs == null || btnIndexs.length <= 0) {
			return;
		}
		
		showBtns(btnIndexs);
		setOnClickListener(btnIndexs);
	}

	/**
	 * 
	 * @Description 隐藏所有底部按钮
	 * @Author blue
	 * @Time 2015-9-21
	 */
	private void hideBtns() {
		for (int i = 0; i < mBtnViews.size(); i++) {
			if (mBtnViews.get(i).getVisibility() == View.VISIBLE) {
				mBtnViews.get(i).setVisibility(View.GONE);
			}
		}
	}

	/**
	 * 
	 * @Description 根据状态显示相应的底部按钮
	 * @Author blue
	 * @Time 2015-9-21
	 */
	private void showBtns(int[] btnIndexs) {
		
		for (int i : btnIndexs) {
			mBtnViews.get(i).setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 
	 * @Description 设置底部按钮的监听事件
	 * @Author blue
	 * @Time 2015-9-21
	 */
	private void setOnClickListener(int[] btnIndexs) {
		
		for (int i = 0; i < btnIndexs.length; i++) {
			mBtnIndex = btnIndexs[i];
			TextView btn = (TextView) mBtnViews.get(mBtnIndex);
			btn.setTag(mBtnIndex);

			/*
			 * 监听事件
			 */
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int btnIndex = Integer.parseInt(v.getTag().toString());
					switch (btnIndex) {
					case 0:
						//去付款

						goPay();
						
						break;
					case 1:
						//再次购买
						
						showToast("再次购买");
						
						break;
					case 2:
						//取消订单
						
						queryAppOrderStatusCancelReason();
						
						break;
					case 3:
						//查看物流
						
						showToast("查看物流");
						
						
						break;
					case 4:
						//评论
						
						showToast("评论");
						
						break;
					case 5:
						//确认收货
						
						queryAppOrderStatusConfirm();
						
						break;
					case 6:
						//删除订单
						
						deleteOrder();
						
						break;
					case 7:
						//提醒卖家
						
						showToast("提醒卖家");  
						
						break;
					case 8:
						//联系卖家
						
						showToast("联系卖家");  
						
						break;
					case 9:
						//退货/换货
						
						showToast("退货/换货");
						
						break;
						

					default:
						break;
					}
				}
			});
		}
	}
	
	/**
	 * 
	 * @Description 去付款
	 * @Author blue
	 * @Time 2015-10-9
	 */
	private void goPay() {
		Intent intent = new Intent(mContext, OnlinePaymentActivity.class);
		//传递订单ID
		intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_ID, mOrderId);
		//传递订单编号
		intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_CODE, mOrderDetailData.getOrderCode());
		//传递订单金额
		intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_PRICE, mOrderDetailData.getOrderAllPrice());
		mContext.startActivity(intent);
	}
	
	/**
	 * 
	 * @Description 删除订单
	 * @Author blue
	 * @Time 2015-10-9
	 */
	private void deleteOrder() {
		final SimpleMsgDialog dialog = new SimpleMsgDialog(mContext);
		dialog.setMsg("您确定要删除该订单吗？");
		dialog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.btn_ok) {
					queryAppOrderStatusDel();
				}
				
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
	/**
	 * 获取订单取消理由
	 */
	private List<CancelOrderReason> mCancelOrderReasons;
	protected void queryAppOrderStatusCancelReason() {
		RequestClient.queryAppOrderStatusCancelReason(mContext, new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					mCancelOrderReasons = JSONParseUtils.getCancelOrderReasons(response);
					final SingleSelectListDialog dialog = new SingleSelectListDialog(mContext);
					dialog.setDatas(mCancelOrderReasons);
					dialog.setonOkClickListener(new onOkClickListener() {
						
						@Override
						public void onOkClick(int position) {
							closeReason = mCancelOrderReasons.get(position).getName();
							queryAppOrderStatusCancel();
						}
					});
					dialog.show();
				}
			}
		});
	}
	
	/**
	 * 确认收货
	 * @param userId 用户Id
	 * @param orderId 订单Id
	 */
	protected void queryAppOrderStatusConfirm() {
		RequestClient.queryAppOrderStatusConfirm(mContext, "21", "1", new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					Toast.makeText(mContext, "已确认收货", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(mContext, JSONParseUtils.getString(response, "msg"), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private String userId;//用户Id
	private String orderId;//订单Id
	private String orderType;//订单类型(1实物2服务)
	private String closeReason;//订单取消理由
	
	/**
	 * 订单状态-取消 接口
	 * @param userId 用户Id
	 * @param orderId 订单Id
	 * @param orderType订单类型(1实物2服务)
	 * @param closeReason 订单取消理由
	 */
	protected void queryAppOrderStatusCancel() {
		RequestClient.queryAppOrderStatusCancel(mContext, BaoGangData.getInstance().getUserId(), mOrderId, ConstantsOrder.ORDER_TYPE_ENTITY+"", closeReason, new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					
				} else {
					Toast.makeText(mContext, JSONParseUtils.getString(response, "msg"), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	/**
	 * 订单状态-删除 接口
	 * @param userId 用户Id
	 * @param orderId 订单Id
	 * @param orderType订单类型(1实物2服务)
	 * @param closeReason 订单取消理由
	 */
	protected void queryAppOrderStatusDel() {
		RequestClient.queryAppOrderStatusDel(mContext, BaoGangData.getInstance().getUserId(), mOrderId, new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
				} else {
					Toast.makeText(mContext, JSONParseUtils.getString(response, "msg"), Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.tv_shop_name:
			
			break;

		default:
			break;
		}
	}

}


