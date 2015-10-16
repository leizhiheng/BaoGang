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
 * @description 浮窗：健康宝-》体检预约-》套餐详情-》选择资源
 * @author blue
 * @Time 2015-9-16
 *
 */
public class HealthyPhysicalExamSourceSelectDialog extends Dialog implements android.view.View.OnClickListener{

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
	
	/**
	 * 体检项目选择
	 */
	private ListViewInScrollView mListView;
	private List<PysicalExamProductSelectItem> mData;
	private PhysicalExamProductSelectAdatper mAdatper;
	
	
	public HealthyPhysicalExamSourceSelectDialog(Context context) {
		super(context,R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_healthy_product_source_select);
		initData(context);
		initView();
		
		setData();
	}
	
	private void initData(Context context) {
		mData = new ArrayList<PysicalExamProductSelectItem>();
		mAdatper = new PhysicalExamProductSelectAdatper(context, mData);
	}
	private void initView() {
		mIvIcon = (ImageView) findViewById(R.id.iv_icon);
		mIvIcon.setFocusable(true);
		mIvIcon.setFocusableInTouchMode(true);
		
		mBtnOk = (TextView) findViewById(R.id.btn_ok);
		mBtnOk.setOnClickListener(this);
		
		mBtnDismiss = (ImageView) findViewById(R.id.btn_dismiss);
		mBtnDismiss.setOnClickListener(this);
		
		mListView = (ListViewInScrollView) findViewById(R.id.listview);
		mListView.setAdapter(mAdatper);
		
		mBtnChooseBoy = (RadioButton) findViewById(R.id.btn_choose_sex_boy);
		mBtnChooseBoy.setChecked(true);
	}
	
	public void setOnClickListener(android.view.View.OnClickListener listener) {
		mBtnOk.setOnClickListener(listener);
	}
	
	public void setData() {
		for (int i = 0; i < 3; i++) {
			mData.add(new PysicalExamProductSelectItem());
		}
		mAdatper.notifyDataSetChanged();
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

