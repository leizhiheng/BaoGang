package com.baosteel.qcsh.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;

/**
 * 
 * @description 这是一个只有简单问题提示和确定取消按钮的Dialog
 * @author blue
 * @Time 2015-9-10
 *
 */
public class SimpleIconDialog extends Dialog{

	private TextView mTvMsg;
	private TextView mBtnOk;
	private ImageView mIcon;
	
	private String mMsg;
	
	public SimpleIconDialog(Context context) {
		super(context,R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_simple_icon);
		initView();
	}
	
	private void initView() {
		mTvMsg = (TextView) findViewById(R.id.tv_msg);
		mBtnOk = (TextView) findViewById(R.id.btn_ok);
		mIcon = (ImageView) findViewById(R.id.iv_dialog_icon);
		mBtnOk.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
	
	public void setOnClickListener(android.view.View.OnClickListener listener) {
		mBtnOk.setOnClickListener(listener);
	}
	
	public void setMsg(String msg) {
		mTvMsg.setText(msg);
	}
	
	public void setOkText(String text){
		mBtnOk.setText(text);
	}
	
}
