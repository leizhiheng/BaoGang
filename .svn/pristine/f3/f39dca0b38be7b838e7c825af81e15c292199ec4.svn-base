package com.baosteel.qcsh.ui.activity.my.order;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.popwindow.SelectApplyReasonWindow.IOnItemClick;
import com.common.base.BaseActivity;

/**
 * 退货-寄回商品
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-21
 */
public class ReturnSendbackActivity extends BaseActivity implements View.OnClickListener {

	/**提交按钮**/
	private Button submitBtn;
	
	/**物流公司**/
	private TextView expressTv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_return_sendback);
		setTitle("寄回商品");
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
		
		//快递公司
		expressTv = (TextView)findViewById(R.id.expressTv);
	
		//提交
		submitBtn = (Button)findViewById(R.id.submit_apply_btn);
		submitBtn.setOnClickListener(this);
		
		//点击事件
		findViewById(R.id.expressLayout).setOnClickListener(this);

	}
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		
		submitBtn.setText("寄回商品");
		submitBtn.setVisibility(View.VISIBLE);
		
	}
	

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.submit_apply_btn:
			
			//提交
			setResult(RESULT_OK);
			finish();
			
			break;
		case R.id.expressLayout:
			
			//选择快递公司
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("5", "顺丰快递");
			dataMap.put("4", "圆通快递");
			dataMap.put("3", "如风达快递");
			dataMap.put("2", "中通快递");
			dataMap.put("1", "一阵风快递");
			showReasonPopwindow(view, dataMap, getTag(expressTv), false, new IOnItemClick() {
				
				@Override
				public void onItemClick(String id, String value) {
					expressTv.setTag(id);
					expressTv.setText(value);
				}
			});
			
			break;
		}
	}
	
}
