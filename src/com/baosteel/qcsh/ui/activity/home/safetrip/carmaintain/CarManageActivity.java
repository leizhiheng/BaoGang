package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.CarManageAdapter;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * 车辆管理 Created by kuangyong on 15/9/15.
 */
public class CarManageActivity extends BaseActivity {

	private HeadView headview;// 标题布局
	private android.widget.LinearLayout layoutselectetype;//添加车辆
	private android.widget.ListView lvcarlist;//汽车列表

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_car_info);
		findView();
		initView();
	}

	private void findView() {
		this.headview = (HeadView) findViewById(R.id.headview);
		this.lvcarlist = (ListView) findViewById(R.id.lv_car_list);
		this.layoutselectetype = (LinearLayout) findViewById(R.id.layout_selecte_type);
	}

	private void initView() {
		headview.setTitle("车辆管理");
		headview.setHeadViewBackGroundColor(getResources().getColor(
				R.color.green_safetrip));
		headview.showRightTextBtn(View.VISIBLE, "确定",
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
		CarManageAdapter adapter = new CarManageAdapter(mContext);
		lvcarlist.setAdapter(adapter);
		layoutselectetype.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, SelecteCarInfoActivity.class).putExtra(SelecteCarInfoActivity.OPRETATE_TYPE, SelecteCarInfoActivity.OPRETATE_ADD));
			}
		});
	}
}
