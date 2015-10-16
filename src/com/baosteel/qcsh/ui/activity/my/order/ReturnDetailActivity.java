package com.baosteel.qcsh.ui.activity.my.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;

/**
 * 退货详情-(请退货，等待商家收货，退款成功，退款失败，退款关闭)
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-21
 */
public class ReturnDetailActivity extends BaseActivity implements View.OnClickListener {

	/**提交按钮**/
	private Button submitBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_return_detail);
		setTitle("退货/款详情");
		setTitleBarColor(getResources().getColor(R.color.index_red));
		
		//初始化控件
		initView();
		
		//初始化数据
		initData();
	}
	
	/**
	 * 初始化控件
	 */
	private void initView(){
		
		//点击事件
		findViewById(R.id.logisticsLayout).setOnClickListener(this);
		findViewById(R.id.talkLayout).setOnClickListener(this);
		
		submitBtn = (Button)findViewById(R.id.submit_apply_btn);
		submitBtn.setOnClickListener(this);
	}
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		
		submitBtn.setText("退回商品");
		submitBtn.setVisibility(View.VISIBLE);
		
	}
	

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.submit_apply_btn:
			
			//提交
			
			//根据退货状态，显示不同的按钮，操作也不一样，这里暂时先做寄回商品
			sendBackProduct();
			
			break;
		case R.id.logisticsLayout:
			
			//进入物流详情
			startActivity(new Intent(mContext, LogisticsDetailActivity.class));
			
			break;
		case R.id.talkLayout:
			
			//协商详情
			startActivity(new Intent(mContext, ReturnTalkRecordActivity.class));
			
			break;
		}
	}
	
	/**
	 * 寄回商品
	 */
	private void sendBackProduct(){
		
		//跳转到寄件界面
		startActivity(new Intent(mContext, ReturnSendbackActivity.class));
	}
}
