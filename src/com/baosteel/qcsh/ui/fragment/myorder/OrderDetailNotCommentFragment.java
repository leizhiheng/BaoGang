package com.baosteel.qcsh.ui.fragment.myorder;

import android.content.Intent;
import android.os.Bundle;

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
import com.baosteel.qcsh.ui.activity.my.order.CommentActivity;
import com.baosteel.qcsh.ui.activity.my.order.ReturnAddActivity;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseFragment;

/**
 * 
 * @description 订单详情 - 待评论
 * @author blue
 * @Time 2015-9-30
 * 
 */
public class OrderDetailNotCommentFragment extends OrderDetailBaseFragment implements
		OnClickListener {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initData();
		initView();

		loadData();
	}

	private void initData() {

	}

	private void initView() {
		mTv_order_state = (TextView) findViewById(R.id.tv_order_state);
		mTv_payment_tips = (TextView) findViewById(R.id.tv_payment_tips);
		mRel_logistics = (RelativeLayout) findViewById(R.id.rel_logistics);
		mLin_begin = (LinearLayout) findViewById(R.id.lin_begin);
		mBtn_refunds = (Button) findViewById(R.id.btn_refunds);
		mBtn_again_buy = (Button) findViewById(R.id.btn_again_buy);
		mBtn_refunds.setOnClickListener(this);
		mBtn_again_buy.setOnClickListener(this);

		mTv_order_state.setText("待评价");
		mRel_logistics.setVisibility(View.VISIBLE);
		mLin_begin.setVisibility(View.VISIBLE);
	}

	private void loadData() {

	}

	@Override
	public void onClick(View view) {
	}
}
