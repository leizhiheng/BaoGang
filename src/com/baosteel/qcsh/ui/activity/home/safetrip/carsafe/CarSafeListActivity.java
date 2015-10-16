package com.baosteel.qcsh.ui.activity.home.safetrip.carsafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.CarInfoAdapter;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * 添加车辆信息 Created by kuangyong on 15/9/9.
 */
public class CarSafeListActivity extends BaseActivity implements
		View.OnClickListener {

	private HeadView headview;// 标题布局
	private LinearLayout layoutadd;//新增车辆信息
	private android.widget.ListView lvcar;//车辆信息列表

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy_car_safe_list);
		findView();
		setListener();
		initView();
	}

	private void findView() {
		this.headview = (HeadView) findViewById(R.id.headview);
		this.lvcar = (ListView) findViewById(R.id.lv_car);
		this.layoutadd = (LinearLayout) findViewById(R.id.layout_add);
	}

	private void setListener() {
		layoutadd.setOnClickListener(this);
		CarInfoAdapter adapter=new CarInfoAdapter(mContext);
		lvcar.setAdapter(adapter);
	}

	private void initView() {
		headview.setTitle("添加车辆信息");
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
		case R.id.layout_add:// 新增车辆信息
			startActivity(new Intent(mContext,EditAddCarInfoActivity.class).putExtra(EditAddCarInfoActivity.EDIT_TYPE,"1"));
			break;
		}
	}

}
