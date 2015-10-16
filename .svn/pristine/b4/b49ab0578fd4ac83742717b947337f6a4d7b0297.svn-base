package com.baosteel.qcsh.ui.fragment.myorder;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.OrderDetailData;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseFragment;

/**
 * 
 * @description 订单详情 - 待审核
 * @author blue
 * @Time 2015-9-30
 *
 */
public class OrderDetailNotCheckFragment extends OrderDetailBaseFragment implements OnClickListener{

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initData();
		initView();
		
		if (mOrderDetailData != null) setView();
	}

	private void initData() {
		
	}
	
	
	private void initView() {
        mTv_service_tips.setVisibility(View.VISIBLE);
        mLin_service_order.setVisibility(View.VISIBLE);
        mTv_order_state.setText("待审核");
	}
	
	@Override
	public void setOrderDetailData(OrderDetailData data) {
		super.setOrderDetailData(data);
		setView();
	}
	
	@Override
    public void onClick(View view) {
    }
}
