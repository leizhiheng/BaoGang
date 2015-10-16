package com.baosteel.qcsh.ui.activity.my.order;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.dialog.SimpleMsgDialog;
import com.baosteel.qcsh.ui.adapter.OrderTalkRecordAdapter;
import com.common.base.BaseActivity;

/**
 * 退货详情-商家审核
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-21
 */
public class ReturnVerifyActivity extends BaseActivity implements View.OnClickListener {

	/**修改退货**/
	public static final int REQUEST_CODE_UPDATE = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_return_verify);
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
		findViewById(R.id.updateApplyBtn).setOnClickListener(this);
		findViewById(R.id.cancelApplyBtn).setOnClickListener(this);
		findViewById(R.id.applyInputBtn).setOnClickListener(this);
		findViewById(R.id.talklayout).setOnClickListener(this);
	}
	
	/**
	 * 初始化数据
	 */
	private void initData() {
	}
	

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.updateApplyBtn:
			
			//修改申请
			Intent intent = new Intent(mContext, ReturnUpdateActivity.class);
			startActivityForResult(intent, REQUEST_CODE_UPDATE);
			
			break;
		case R.id.cancelApplyBtn:
			
			//撤销申请
			final SimpleMsgDialog dialog = new SimpleMsgDialog(mContext);
			dialog.setMsg("撤销申请后您将不能重新发起收货申请，是否确认撤销？");
			dialog.setOkText("撤销");
			dialog.show();
			dialog.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					if(v.getId() == R.id.btn_ok){
						finish();
					}
				}
			});
			
			
			break;
		case R.id.applyInputBtn:
				
			//申请平台介入
			int flag = new Random().nextInt(2);
			if(0 == flag){
				
				//不能申请介入
				final SimpleMsgDialog applyDialog = new SimpleMsgDialog(mContext);
				applyDialog.setMsg("退款/退货申请发起3天后才可申请客服介入");
				applyDialog.hideCancelBtn();
				applyDialog.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						applyDialog.dismiss();
					}
				});
				applyDialog.show();
				
			}else{
				
				//可以申请介入-跳转界面
				startActivity(new Intent(mContext, ReturnApplyPlatformActivity.class));
			}
			
			break;
		case R.id.talklayout:
			
			//协商详情
			startActivity(new Intent(mContext, ReturnTalkRecordActivity.class));
			
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		if(arg1 != RESULT_OK){
			return;
		}
		
		switch (arg0) {
		case REQUEST_CODE_UPDATE:
			
			//修改成功，则退出界面
			showToast("退款修改成功，数据已刷新");
			
			break;
		}
	}
}
