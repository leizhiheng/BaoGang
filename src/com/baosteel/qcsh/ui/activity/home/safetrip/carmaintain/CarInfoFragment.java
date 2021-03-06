package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.CarTypeListAdapter;
import com.common.base.BaseCameraFragment;

/**
 * 汽车信息 Created by kuangyong on 15/9/14.
 */
public class CarInfoFragment extends BaseCameraFragment implements View.OnClickListener{
	private TextView tvcarinfo;//车辆信息
	private TextView tvnowaytime;//新车上路时间
	private LinearLayout layouttime;
	private TextView tvproductionmonth;//生产月份
	private LinearLayout layoutproductionmonth;
	private TextView tvpretime;//上次保养时间
	private LinearLayout layoutpretime;
	private TextView tvprelicheng;//上次保养里程
	private LinearLayout layoutprelicheng;
	private TextView tvcurlicheng;//当前保养里程
	private LinearLayout layoutcurlicheng;
	private TextView btndelcar;//删除该车辆
	private android.widget.ImageView ivbrand;//车辆品牌图标

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_car_info, null);
		return fragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		findView();
		setListener();
		initView();
	}

	private void findView() {
		this.btndelcar = (TextView) findViewById(R.id.btn_del_car);
		this.layoutcurlicheng = (LinearLayout) findViewById(R.id.layout_cur_licheng);
		this.tvcurlicheng = (TextView) findViewById(R.id.tv_cur_licheng);
		this.layoutprelicheng = (LinearLayout) findViewById(R.id.layout_pre_licheng);
		this.tvprelicheng = (TextView) findViewById(R.id.tv_pre_licheng);
		this.layoutpretime = (LinearLayout) findViewById(R.id.layout_pre_time);
		this.tvpretime = (TextView) findViewById(R.id.tv_pre_time);
		this.layoutproductionmonth = (LinearLayout) findViewById(R.id.layout_production_month);
		this.tvproductionmonth = (TextView) findViewById(R.id.tv_production_month);
		this.layouttime = (LinearLayout) findViewById(R.id.layout_time);
		this.tvnowaytime = (TextView) findViewById(R.id.tv_noway_time);
		this.tvcarinfo = (TextView) findViewById(R.id.tv_car_info);
		this.ivbrand = (ImageView) findViewById(R.id.iv_brand);
	}

	private void setListener() {
		layouttime.setOnClickListener(this);
		layoutproductionmonth.setOnClickListener(this);
		layoutpretime.setOnClickListener(this);
		layoutprelicheng.setOnClickListener(this);
		btndelcar.setOnClickListener(this);
		layoutcurlicheng.setOnClickListener(this);
	}

	private void initView() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.layout_time://新车上路时间
				showToast("新车上路时间");
				break;
			case R.id.layout_production_month://生产月份
				showToast("生产月份");

				break;
			case R.id.layout_pre_time://上次保养时间
				showToast("上次保养时间");

				break;
			case R.id.layout_pre_licheng://上次保养里程
				showToast("上次保养里程");


				break;
			case R.id.layout_cur_licheng://当前里程
				showToast("当前里程");

				break;
			case R.id.btn_del_car://删除该车辆
				showToast("删除该车辆");

				break;

		}
	}

	public interface OnListItemClickListener {
		void onItemClick(ExpandableListView parent, View v, int groupPosition,
						 int childPosition, long id);
	}

	/**
	 * 列表item点击事件的回调
	 */
	private OnListItemClickListener listener;

	public void setOnItemClickListener(OnListItemClickListener listener) {
		this.listener = listener;
	}

}
