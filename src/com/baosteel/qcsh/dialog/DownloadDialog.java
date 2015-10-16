package com.baosteel.qcsh.dialog;


import com.baosteel.qcsh.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownloadDialog extends Dialog implements OnClickListener {

	private View cancelView;
	private Button btnCancel;
	private TextView title;
	private TextView message;
	private ProgressBar progressBar;

	public DownloadDialog(Context context) {
		super(context, R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setCancelable(false);
		setContentView(R.layout.dialog_download);
		
		cancelView = findViewById(R.id.layout_cancel);
		title = (TextView) findViewById(R.id.title);
		message = (TextView) findViewById(R.id.message);
		progressBar = (ProgressBar) findViewById(R.id.progress);
		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(this);
	}

	public void setOnCancelListener(OnCancelListener clickListener) {
		mOnCancelListener = clickListener;
	}

	public void setCustomTitle(String value) {
		title.setText(value);
	}

	public void setCustomTitle(int value) {
		title.setText(value);
	}

	public void setMessage(String msg) {
		message.setText(msg);
	}

	public void setMessage(int msg) {
		message.setText(msg);
	}
	
	public void showCancelView(boolean show) {
		if (!show) {
			cancelView.setVisibility(View.GONE);
		}
	}

	private OnCancelListener mOnCancelListener;

	public interface OnCancelListener {
		void onCancel();
	}
	
	
	public TextView getMessage() {
		return message;
	}


	public ProgressBar getProgressBar() {
		return progressBar;
	}

	@Override
	public void onClick(View v) {
		if (v == btnCancel) {
			if (mOnCancelListener != null) {
				mOnCancelListener.onCancel();
			}
			dismiss();
		}
	}

}
