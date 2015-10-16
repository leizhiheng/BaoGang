package com.baosteel.qcsh.dialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.PysicalExamProductSelectItem;
import com.baosteel.qcsh.ui.adapter.PhysicalExamProductSelectAdatper;
import com.common.view.other.ListViewInScrollView;

/**
 * 
 * @description 浮窗：健康宝-》养老现床（期床）-》套餐详情-》选择资源
 * @author blue
 * @Time 2015-9-16
 *
 */
public class HealthyBedSourceSelectDialog extends Dialog implements android.view.View.OnClickListener{

	private Context mContext;
	
	private TextView mBtnOk;
	
	/**
	 * Dialog图标
	 */
	private ImageView mIvIcon;
	
	private ImageView mBtnDismiss;
	
	/**
	 * 选择性别
	 */
	private RadioGroup mRadioGroupSex;
	private RadioButton mBtnChooseBoy;
	
	public HealthyBedSourceSelectDialog(Context context) {
		super(context,R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_healthy_bed_source_select);
		initData(context);
		initView();
		
		setData();
	}
	
	private void initData(Context context) {
	}
	private void initView() {
		mIvIcon = (ImageView) findViewById(R.id.iv_icon);
		mIvIcon.setFocusable(true);
		mIvIcon.setFocusableInTouchMode(true);
		
		mBtnOk = (TextView) findViewById(R.id.btn_ok);
		mBtnOk.setOnClickListener(this);
		
		mBtnDismiss = (ImageView) findViewById(R.id.btn_dismiss);
		mBtnDismiss.setOnClickListener(this);
		
		mBtnChooseBoy = (RadioButton) findViewById(R.id.btn_choose_sex_boy);
		mBtnChooseBoy.setChecked(true);
	}
	
	public void setOnClickListener(android.view.View.OnClickListener listener) {
		mBtnOk.setOnClickListener(listener);
	}
	
	public void setData() {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_dismiss:
			dismiss();
			break;
			
		case R.id.btn_ok:
			dismiss();
			break;

		default:
			break;
		}
	}

}

