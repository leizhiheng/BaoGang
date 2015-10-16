package com.baosteel.qcsh.dialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CancelOrderReason;
import com.baosteel.qcsh.ui.adapter.CancelOrderReasonAdapter;

/**
 * 
 * @description 这是一个只有简单问题提示和确定取消按钮的Dialog
 * @author blue
 * @Time 2015-9-10
 *
 */
public class SingleSelectListDialog extends Dialog implements android.view.View.OnClickListener{

	private ListView mListView;
	private TextView mBtnOk;
	private TextView mBtnCancel;
	
	private Context mContext;
	private String mMsg;
	private List<CancelOrderReason> mReasons;
	private CancelOrderReasonAdapter mAdapter;
	
	private android.view.View.OnClickListener mOnClickListener;
	
	private int mSelectedIndex;
	
	public interface onOkClickListener{
		void onOkClick(int position);
	}
	
	public void setonOkClickListener(onOkClickListener listener) {
		this.mOnOkClickListener = listener;
	}
	
	private onOkClickListener mOnOkClickListener;
	
	public SingleSelectListDialog(Context context) {
		super(context,R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_single_choice_list);
		
		init(context);
	}
	
	private void init(Context context) {
		Log.d("zhiheng", "dialog init");
		mContext = context;
		mReasons = new ArrayList<CancelOrderReason>();
		mAdapter = new CancelOrderReasonAdapter(mContext, mReasons);
		
		mListView = (ListView) findViewById(R.id.listview);
		mBtnOk = (TextView) findViewById(R.id.btn_ok);
		mBtnCancel = (TextView) findViewById(R.id.btn_cancel);
		mBtnCancel.setOnClickListener(this);
		mBtnOk.setOnClickListener(this);
		
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mSelectedIndex == position) {
					return;
				} else {
					mReasons.get(mSelectedIndex).setSelected(false);
					mReasons.get(position).setSelected(true);
					mSelectedIndex = position;
					mAdapter.notifyDataSetChanged();
				}
			}
		});
	}
	
	public void setOnClickListener(android.view.View.OnClickListener listener) {
		mBtnOk.setOnClickListener(listener);
		mBtnCancel.setOnClickListener(listener);
	}
	
	
	public void setOkText(String text){
		mBtnOk.setText(text);
	}
	
	public void hideCancelBtn() {
		findViewById(R.id.line).setVisibility(View.GONE);
		mBtnCancel.setVisibility(View.GONE);
	}
	
	public void setDatas(List<CancelOrderReason> datas) {
		this.mReasons.addAll(datas);
		this.mReasons.get(0).setSelected(true);
		if (mAdapter != null) {
			mAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_ok) {
			if (mOnOkClickListener != null) {
				mOnOkClickListener.onOkClick(mSelectedIndex);
			}
		} 
		
		dismiss();
	}
}
