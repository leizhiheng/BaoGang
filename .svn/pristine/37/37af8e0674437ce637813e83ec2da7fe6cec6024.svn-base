package com.baosteel.qcsh.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baosteel.qcsh.R;

/**
 * 健康点-解除绑定
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-16
 */
public class FreeBindDialog extends Dialog{

	private TextView mTvMsg;
	private TextView mBtnOk;
	private TextView mBtnCancel;
	
	private String mMsg;
	
	public FreeBindDialog(Context context) {
		super(context,R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_free_bind);
		initView();
	}
	
	private void initView() {
		mTvMsg = (TextView) findViewById(R.id.tv_dialog_msg);
		mBtnOk = (TextView) findViewById(R.id.btn_ok);
		mBtnCancel = (TextView) findViewById(R.id.btn_cancel);
		findViewById(R.id.deleteImg).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
	
	public void setOnClickListener(android.view.View.OnClickListener listener) {
		mBtnOk.setOnClickListener(listener);
		mBtnCancel.setOnClickListener(listener);
	}
	
	public void setMsg(String msg) {
		mTvMsg.setText(msg);
	}

}
