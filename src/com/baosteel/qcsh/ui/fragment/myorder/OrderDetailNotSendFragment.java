package com.baosteel.qcsh.ui.fragment.myorder;

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
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseFragment;

/**
 * 
 * @description 订单详情 - 待发货
 * @author blue
 * @Time 2015-9-30
 * 
 */
public class OrderDetailNotSendFragment extends OrderDetailBaseFragment
		implements OnClickListener {

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
		
		mTv_order_state.setText("待发货");
	}

	private void loadData() {

	}

	@Override
	public void onClick(View v) {

	}
}
