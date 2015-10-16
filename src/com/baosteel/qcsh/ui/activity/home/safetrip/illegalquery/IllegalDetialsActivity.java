package com.baosteel.qcsh.ui.activity.home.safetrip.illegalquery;

import android.os.Bundle;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * 违章列表 Created by kuangyong on 15/9/16.
 */
public class IllegalDetialsActivity extends BaseActivity {

	private HeadView headview;
	private TextView tvcarnum;//车牌号码
	private TextView tvaddress;//违章地点
	private TextView tvcontent;//违章内容
	private TextView tvtip;//提示
	private TextView tvtime;//违章时间
	private TextView tvstatus;//违章状态
	private TextView tvscore;//扣分
	private TextView tvmoney;//扣款
	private TextView tvpeoplenum;//违章人数

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_illegal_details);
		this.tvpeoplenum = (TextView) findViewById(R.id.tv_people_num);
		this.tvmoney = (TextView) findViewById(R.id.tv_money);
		this.tvscore = (TextView) findViewById(R.id.tv_score);
		this.tvstatus = (TextView) findViewById(R.id.tv_status);
		this.tvtime = (TextView) findViewById(R.id.tv_time);
		this.tvtip = (TextView) findViewById(R.id.tv_tip);
		this.tvcontent = (TextView) findViewById(R.id.tv_content);
		this.tvaddress = (TextView) findViewById(R.id.tv_address);
		this.tvcarnum = (TextView) findViewById(R.id.tv_carnum);
		this.headview = (HeadView) findViewById(R.id.headview);
		findView();
		setListener();
		initView();
	}

	private void findView() {
		this.headview = (HeadView) findViewById(R.id.headview);
	}

	private void setListener() {
		headview.setTitle("违章详情");
		headview.setHeadViewBackGroundColor(getResources().getColor(
				R.color.green_safetrip));
    }

    private void initView() {

    }
}
