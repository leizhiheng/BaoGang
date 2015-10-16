package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.ConstantsOrder;
import com.baosteel.qcsh.model.OrderItem;
import com.baosteel.qcsh.ui.adapter.OrderCheckingAdapter.ViewHolder;

public class OrderNotPayAdapter extends BaseAdapter {
	private Context mContext;
	private List<OrderItem> mDatas;
	private LayoutInflater mInflater;

	public OrderNotPayAdapter(Context context, List<OrderItem> datas) {
		this.mContext = context;
		this.mDatas = datas;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.adapter_entity_and_service_order, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();

		holder.setView(Integer.parseInt(mDatas.get(position).getStatus()), position);

		return convertView;
	}

	class ViewHolder {
		TextView tvStateName;
		TextView btnGoPay;// 去付款
		TextView btnBuyAgain;
		TextView btnDeleteOrder;// 删除订单（交易完成后可取消订单）
		TextView btnComment;// 评价（或追加评价）
		TextView btnCheckLogistics;// 查看物流
		TextView btnConfirmReceive;// 确认收货
		TextView btnCancelOrder;// 取消订单

		List<View> mBtnViews;// 用于管理底部按钮View

		private int mBtnIndex;

		public ViewHolder(View view) {

			mBtnViews = new ArrayList<View>();

			tvStateName = (TextView) view
					.findViewById(R.id.tv_order_state_name);
			btnGoPay = (TextView) view.findViewById(R.id.btn_gopay);
			btnBuyAgain = (TextView) view.findViewById(R.id.btn_buy_again);
			btnDeleteOrder = (TextView) view
					.findViewById(R.id.btn_delete_order);
			btnComment = (TextView) view.findViewById(R.id.btn_comment_order);
			btnCheckLogistics = (TextView) view
					.findViewById(R.id.btn_check_logistics);
			btnConfirmReceive = (TextView) view
					.findViewById(R.id.btn_confirm_receive);
			btnCancelOrder = (TextView) view
					.findViewById(R.id.btn_cancel_order);

			mBtnViews.add(btnGoPay);// 0
			mBtnViews.add(btnBuyAgain);// 1
			mBtnViews.add(btnCancelOrder);// 2
			mBtnViews.add(btnCheckLogistics);// 3
			mBtnViews.add(btnComment);// 4
			mBtnViews.add(btnConfirmReceive);// 5
			mBtnViews.add(btnDeleteOrder);// 6
		}

		/**
		 * 
		 * @Description 根据订单的状态判断底部按钮的显示与隐藏
		 * @Author blue
		 * @Time 2015-9-21
		 */
		public void setView(int orderState, int position) {

			hideBtns();

			switch (orderState) {
			case ConstantsOrder.ORDER_STATUS_NOT_CHECK:// 待审核
				tvStateName.setText("待审核");
				showBtns(new int[] { 2 });// 显示“取消订单”按钮
				setOnClickListener(new int[] { 2 }, position);
				break;

			case ConstantsOrder.ORDER_STATUS_NOT_PAY:// 待付款
				tvStateName.setText("待付款");
				showBtns(new int[] { 0, 2 });// 显示"去付款"，“取消订单”按钮
				setOnClickListener(new int[] { 0, 2 }, position);

				break;
			case ConstantsOrder.ORDER_STATUS_NOT_SEND:// 待发货
				tvStateName.setText("待发货");
				showBtns(new int[] { 2 });// 显示“取消订单”按钮
				setOnClickListener(new int[] { 2 }, position);
				break;

			case ConstantsOrder.ORDER_STATUS_NOT_RECEIVE:// 待收货
				tvStateName.setText("待收货");
				showBtns(new int[] { 3, 5 });// 显示"查看物流"，“确认收货”按钮
				setOnClickListener(new int[] { 3, 5 }, position);
				break;

			case ConstantsOrder.ORDER_STATUS_NOT_COMMENT:// 待评价
				tvStateName.setText("交易完成");
				btnComment.setText("晒单评价");
				showBtns(new int[] { 1, 6, 4 });// 显示"再次购买"，“删除订单”，“晒单评价“按钮
				setOnClickListener(new int[] { 1, 6, 4 }, position);
				break;

			default:
				break;
			}
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
		private void setOnClickListener(int[] btnIndexs, final int position) {
			for (int i = 0; i < btnIndexs.length; i++) {
				TextView textView = (TextView) mBtnViews.get(i);
				Log.d("zhiheng", "btn index = " + i+"postion = "+position + ", btn text = "+textView.getText().toString());
				mBtnIndex = btnIndexs[i];
				if (i == 0) {

				} else if (i == 1) {

				} else if (i == 2) {

				} else if (i == 3) {

				} else if (i == 4) {

				} else if (i == 5) {

				} else if (i == 6) {

				}

				// 测试
				mBtnViews.get(mBtnIndex).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(
								mContext,
								"第 " + (position + 1) + "个订单  "
										+ ((TextView) v).getText().toString(),
								Toast.LENGTH_SHORT).show();
					}
				});
			}
		}
	}

}

