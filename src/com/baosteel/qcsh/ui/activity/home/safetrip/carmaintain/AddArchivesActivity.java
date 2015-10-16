package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * 添加档案 Created by kuangyong on 15/9/10.
 */
public class AddArchivesActivity extends BaseActivity implements
		View.OnClickListener {

	private HeadView headview;// 标题布局
	private TextView tvcarname;// 汽车名称及参数
	private TextView tvcarmoreinfo;// 汽车更多信息
	private LinearLayout layoutcarinfo;// 汽车信息布局
	private TextView tvcurtime;// 当前保养时间
	private LinearLayout layouttime;
	private TextView tvarchives;// 配件
	private LinearLayout layoutselectefromorder;// 从订单选择
	private TextView tvmaintaintype;// 保养类型
	private LinearLayout layoutmaintaintype;
	private TextView btncommit;// 完成
	private android.widget.EditText evlicheng;// 里程数
	private android.widget.EditText evarchivesname;// 配件名称
	private android.widget.EditText evarchivesnum;// 配件用量
	private android.widget.EditText evmoreinfo;// 备注信息

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_archives);
		findView();
		setListener();
		initView();
	}

	private void findView() {
		this.headview = (HeadView) findViewById(R.id.headview);
		this.layoutcarinfo = (LinearLayout) findViewById(R.id.layout_car_info);
		this.tvcarmoreinfo = (TextView) findViewById(R.id.tv_car_more_info);
		this.tvcarname = (TextView) findViewById(R.id.tv_car_name);
		this.btncommit = (TextView) findViewById(R.id.btn_commit);
		this.layoutmaintaintype = (LinearLayout) findViewById(R.id.layout_maintain_type);
		this.tvmaintaintype = (TextView) findViewById(R.id.tv_maintain_type);
		this.layoutselectefromorder = (LinearLayout) findViewById(R.id.layout_selecte_from_order);
		this.tvarchives = (TextView) findViewById(R.id.tv_archives);
		this.layouttime = (LinearLayout) findViewById(R.id.layout_time);
		this.tvcurtime = (TextView) findViewById(R.id.tv_cur_time);
		this.evmoreinfo = (EditText) findViewById(R.id.ev_more_info);
		this.evarchivesnum = (EditText) findViewById(R.id.ev_archives_num);
		this.evarchivesname = (EditText) findViewById(R.id.ev_archives_name);
		this.evlicheng = (EditText) findViewById(R.id.ev_licheng);
	}

	private void setListener() {
		layoutcarinfo.setOnClickListener(this);
		layouttime.setOnClickListener(this);
		layoutmaintaintype.setOnClickListener(this);
		btncommit.setOnClickListener(this);
	}

	private void initView() {
		headview.setTitle("增加保养档案");
		headview.setSeparateLineVisible(View.INVISIBLE);
		headview.setHeadViewBackGroundColor(Color.TRANSPARENT);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_car_info:// 汽车信息
			startActivity(new Intent(mContext,CarManageActivity.class));
			break;
		case R.id.layout_maintain_type:// 保养类型
			showToast("保养类型");
			break;
		case R.id.layout_selecte_from_order:// 从订单选择
			showToast("从订单选择");
			break;
		case R.id.layout_time:// 保养时间
			showToast("保养时间");
			break;
		case R.id.btn_commit:// 完成
			finish();
			break;
		}
	}
}
