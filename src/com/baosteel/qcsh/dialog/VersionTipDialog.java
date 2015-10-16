package com.baosteel.qcsh.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.UpdateInfo;
import com.baosteel.qcsh.utils.UpdateManager;

public class VersionTipDialog extends Dialog implements android.view.View.OnClickListener{

	private Context mContext;
	
	private TextView mVersionName;
	private TextView mVersionSize;
	private TextView mVersionTip;
	private TextView mVersionForceUpdate;
	private TextView mBtnOk;
	private TextView mBtnCancel;
	
	private String mMsg;
	
	private UpdateInfo mUpdateInfo;
	
	public VersionTipDialog(Context context, UpdateInfo updateInfo) {
		super(context,R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_version_tip);
		
		init(context, updateInfo);
	}
	
	private void init(Context context, UpdateInfo updateInfo) {
		
		mContext = context;
		mUpdateInfo = updateInfo;
		
		mVersionName = (TextView) findViewById(R.id.tv_version_name);
		mVersionSize = (TextView) findViewById(R.id.tv_version_size);
		mVersionTip = (TextView) findViewById(R.id.tv_version_tip);
		mVersionForceUpdate = (TextView) findViewById(R.id.tv_version_forceupdate);
		
		mBtnOk = (TextView) findViewById(R.id.btn_ok);
		mBtnCancel = (TextView) findViewById(R.id.btn_cancel);
		
		mVersionName.setText("发现新版本："+mUpdateInfo.getVersionName());
		mVersionTip.setText(mUpdateInfo.getUpdateTip().trim());
		
		mBtnOk.setOnClickListener(this);
		mBtnCancel.setOnClickListener(this);
		
		if (mUpdateInfo.getForceUpdate().equals("1")) {
			mVersionForceUpdate.setVisibility(View.VISIBLE);
			mBtnCancel.setVisibility(View.GONE);
			setCancelable(false);
		}
	}
	
	public View.OnClickListener onOKclick;
	public View.OnClickListener onCancelclick;
	public void setOnClickListener(android.view.View.OnClickListener listener) {
		this.onOKclick = listener;
		this.onCancelclick = listener;
	}
	
	public void setOnOKClickListener(android.view.View.OnClickListener listener) {
		this.onOKclick = listener;
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
	
	@Override
	public void onClick(View v) {
		dismiss();
		switch (v.getId()) {
		case R.id.btn_ok:
			UpdateManager manager = new UpdateManager(mContext, "BaoGang.apk", mUpdateInfo.getDownloadAddress());
			manager.showDownloadDialog(false);
			if (mUpdateInfo.getForceUpdate().equals("1")) {
				//setCancelable(false);
			} else {
				//setCancelable(true);
				dismiss();
			}
			break;

		case R.id.btn_cancel:
			dismiss();
			break;
		}
	}

}
