package com.baosteel.qcsh.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baosteel.qcsh.R;

/**
 * 
 * @description 这是一个只有简单问题提示和确定取消按钮的Dialog
 * @author blue
 * @Time 2015-9-10
 *
 */
public class SimpleMsgDialog extends Dialog{

	private TextView mTvMsg;
	private TextView mBtnOk;
	private TextView mBtnCancel;
	
	private String mMsg;
	
	public SimpleMsgDialog(Context context) {
		super(context,R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_simple_msg);
		initView();
	}
	
	private void initView() {
		mTvMsg = (TextView) findViewById(R.id.tv_dialog_msg);
		mBtnOk = (TextView) findViewById(R.id.btn_ok);
		mBtnCancel = (TextView) findViewById(R.id.btn_cancel);
		
	}
	
	public View.OnClickListener onClickListener;
	public void setOnClickListener(android.view.View.OnClickListener listener) {
		this.onClickListener = listener;
		
		mBtnOk.setOnClickListener(onClickListener);
		mBtnCancel.setOnClickListener(onClickListener);
	}
	
	public void setMsg(String msg) {
		mTvMsg.setText(msg);
	}
	
	public void setOkText(String text){
		mBtnOk.setText(text);
	}
	
	public void hideCancelBtn() {
		findViewById(R.id.line).setVisibility(View.GONE);
		mBtnCancel.setVisibility(View.GONE);
	}
	
	boolean isDismiss = false;
	@Override
	public void dismiss() {
		
		//防止重复dismiss报错
		try {
			if (!isDismiss) {
				super.dismiss();
				isDismiss = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
