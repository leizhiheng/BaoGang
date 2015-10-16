package com.baosteel.qcsh.ui.activity.home.safetrip.carsafe;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;
import com.common.utils.DialogUtil;
import com.common.view.topbar.HeadView;

/**
 * 添加/编辑车辆信息 Created by kuangyong on 15/9/10.
 */
public class EditAddCarInfoActivity extends BaseActivity implements
		View.OnClickListener {

	public static final String EDIT_TYPE = "edit_type";// 编辑类型
	private HeadView headview;// 标题布局
	private String type;// 1.添加 2.编辑
	private android.widget.EditText evcarnum;//车牌号
	private android.widget.EditText evframenumber;//车架号
	private android.widget.EditText evmotornumber;//发动机号
	private android.widget.TextView tvtime;//登记日期
	private LinearLayout layouttime;//日期布局

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_add_car_info);
		type = getIntent().getStringExtra(EDIT_TYPE);
		findView();
		setListener();
		initView();
	}

	private void findView() {
		this.headview = (HeadView) findViewById(R.id.headview);
		this.layouttime = (LinearLayout) findViewById(R.id.layout_time);
		this.tvtime = (TextView) findViewById(R.id.tv_time);
		this.evmotornumber = (EditText) findViewById(R.id.ev_motor_number);
		this.evframenumber = (EditText) findViewById(R.id.ev_frame_number);
		this.evcarnum = (EditText) findViewById(R.id.ev_carnum);
	}

	private void setListener() {
		layouttime.setOnClickListener(this);
	}

	private void initView() {
		if (!TextUtils.isEmpty(type) && type.equals("1")) {// 添加
			headview.setTitle("新增车辆信息");
		} else if (!TextUtils.isEmpty(type) && type.equals("2")) {// 编辑
			headview.setTitle("编辑车辆信息");
			evcarnum.setText("粤B5332");
			evmotornumber.setText("SJVGIDS886678");
			evframenumber.setText("234234K");
			tvtime.setText("2015-09-09");
		}
		headview.setHeadViewBackGroundColor(getResources().getColor(
				R.color.green_safetrip));
		headview.setRightTextBtnTextColor(getResources()
				.getColor(R.color.white));
		headview.showRightTextBtn(View.VISIBLE, "完成",
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mContext.finish();
					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_time:// 选择时间
			DialogUtil.showBirthdayDialog(tvtime, mContext);
			break;
		}
	}

}
