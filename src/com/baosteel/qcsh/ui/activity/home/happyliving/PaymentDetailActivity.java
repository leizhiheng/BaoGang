package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.PaymentDetailsItem;
import com.baosteel.qcsh.ui.adapter.PaymentDetailsAdapter;
import com.common.base.BaseActivity;
import com.common.view.other.ListViewInScrollView;

/**
 * 
 * @description 页面：乐居宝-》物业服务-》便捷缴费-》 各种缴费的缴费明细
 * @author blue
 * @Time 2015-9-11
 *
 */
public class PaymentDetailActivity extends BaseActivity{
	
	private View mViewOther;
	private View mViewProperty;
	
	private ListViewInScrollView mDetailListView;
	
	private TextView mBtnPay;

	/**
	 * 传递缴费类型：物业费或其他
	 */
	public static final String EXTRA_KEY_PAY_TYPE = "pay.type";
	/**
	 * 传递缴费项目名称
	 */
	public static final String EXTRA_KEY_PAYMENT_NAME = "pay.name";
	/**
	 * 缴纳物业费
	 */
	public static final int PAY_TYPE_PROPERTY = 0;
	/**
	 * 缴纳其他费用
	 */
	public static final int PAY_TYPE_OTHER = 1;
	
	private int mPayType;
	private String mPaymentName;
	
	private List<PaymentDetailsItem> mDetailsItems;
	private PaymentDetailsAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment_detail);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mPayType = getIntent().getIntExtra(EXTRA_KEY_PAY_TYPE, PAY_TYPE_OTHER);
		mPaymentName = getIntent().getStringExtra(EXTRA_KEY_PAYMENT_NAME);
		
		mDetailsItems = new ArrayList<PaymentDetailsItem>();
		mAdapter = new PaymentDetailsAdapter(this, mDetailsItems);
	}
	
	private void initView() {
		setTitle("缴费明细");
		
		mViewOther = findViewById(R.id.layout_other_cost);
		mViewProperty = findViewById(R.id.layout_property_cost);
		if (mPayType == PAY_TYPE_OTHER) {
			mViewOther.setVisibility(View.VISIBLE);
		} else {
			mViewProperty.setVisibility(View.VISIBLE);
		}
		
		mDetailListView = (ListViewInScrollView) findViewById(R.id.listview);
		mDetailListView.setAdapter(mAdapter);
	}
	
	private void loadData() {
		if (mPayType == PAY_TYPE_OTHER) {
			mDetailsItems.add(new PaymentDetailsItem("缴费项目", mPaymentName));
			mDetailsItems.add(new PaymentDetailsItem("缴费地区", "缴费地区"));
			mDetailsItems.add(new PaymentDetailsItem("缴费单位", "上海电力公司"));
			mDetailsItems.add(new PaymentDetailsItem("用户姓名", "雷**"));
		} else if (mPayType == PAY_TYPE_PROPERTY) {
			mDetailsItems.add(new PaymentDetailsItem("缴费项目", mPaymentName));
			mDetailsItems.add(new PaymentDetailsItem("所在城市", "上海市"));
			mDetailsItems.add(new PaymentDetailsItem("所在小区", "中信领航"));
			mDetailsItems.add(new PaymentDetailsItem("所在楼栋", "B1栋"));
			mDetailsItems.add(new PaymentDetailsItem("所在房号", "505"));
			mDetailsItems.add(new PaymentDetailsItem("户主姓名", "雷**"));
			mDetailsItems.add(new PaymentDetailsItem("身份证号", "45355****"));
		}
		mAdapter.notifyDataSetChanged();
	}
}

