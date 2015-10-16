package com.baosteel.qcsh.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.baosteel.qcsh.R;

/**
 * 
 * @description 浮窗：健康宝-》体检预约-》套餐详情-》产品属性
 * @author blue
 * @Time 2015-9-16
 *
 */
public class HealthyProductAttrsDialog extends Dialog implements OnClickListener{

	private ImageView mBtnDismiss;
	
	private String mMsg;
	
	public HealthyProductAttrsDialog(Context context) {
		super(context,R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_healthy_product_attrs);
		initView();
	}
	
	private void initView() {
		mBtnDismiss = (ImageView) findViewById(R.id.btn_dismiss);
		mBtnDismiss.setOnClickListener(this);
	}
	
	public void setOnClickListener(android.view.View.OnClickListener listener) {
	}
	
	public void setMsg(String msg) {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_dismiss:
			dismiss();
			break;

		default:
			break;
		}
	}

}
