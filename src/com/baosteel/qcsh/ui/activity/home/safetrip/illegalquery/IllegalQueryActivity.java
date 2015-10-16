package com.baosteel.qcsh.ui.activity.home.safetrip.illegalquery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.adress.SQLOpearteImpl.Area;
import com.baosteel.qcsh.dialog.PositionSelect;
import com.baosteel.qcsh.ui.activity.home.safetrip.carsafe.CarSafeListActivity;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * 违章查询 Created by kuangyong on 15/9/9.
 */
public class IllegalQueryActivity extends BaseActivity implements
		View.OnClickListener {

	private HeadView headview;// 标题布局
	private TextView tvadress;// 地址
	private LinearLayout layoutadress;// 地址布局
	private TextView tvcarnum;// 车牌号
	private EditText evframenumber;// 车架号
	private EditText evmotornumber;// 发动机号
	private TextView tvname;// 车主姓名
	private String provinceName;// 省
	private String cityName;// 市
	private String areaName;// 区
	private int provinceId;// 省id
	private int cityId;// 市id
	private int areaId;// 区id
	private TextView tvnumtype;//车牌号类型
	private LinearLayout layoutnumtype;
	private EditText evtypenum;//类型号
	private EditText tvphone;//电话号码
	private EditText tvcode;//验证码
	private TextView btngetcheckcode;//获取验证码
	private TextView btnquery;//立即查询

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_illegal_query);
		findView();
		setListener();
		initView();
	}

	private void findView() {
		this.btnquery = (TextView) findViewById(R.id.btn_query);
		this.btngetcheckcode = (TextView) findViewById(R.id.btn_get_check_code);
		this.tvcode = (EditText) findViewById(R.id.tv_code);
		this.tvphone = (EditText) findViewById(R.id.tv_phone);
		this.evtypenum = (EditText) findViewById(R.id.ev_type_num);
		this.layoutnumtype = (LinearLayout) findViewById(R.id.layout_num_type);
		this.tvnumtype = (TextView) findViewById(R.id.tv_num_type);
		this.tvname = (TextView) findViewById(R.id.tv_name);
		this.evmotornumber = (EditText) findViewById(R.id.ev_motor_number);
		this.evframenumber = (EditText) findViewById(R.id.ev_frame_number);
		this.tvcarnum = (TextView) findViewById(R.id.tv_carnum);
		this.layoutadress = (LinearLayout) findViewById(R.id.layout_adress);
		this.tvadress = (TextView) findViewById(R.id.tv_adress);
		this.headview = (HeadView) findViewById(R.id.headview);

	}

	private void setListener() {
		layoutadress.setOnClickListener(this);
		btnquery.setOnClickListener(this);
		layoutnumtype.setOnClickListener(this);
		btngetcheckcode.setOnClickListener(this);
	}

	private void initView() {
		headview.setTitle("违章查询");
		headview.setHeadViewBackGroundColor(getResources().getColor(
				R.color.green_safetrip));
		headview.setRightTextBtnTextColor(getResources()
				.getColor(R.color.white));
		headview.showRightTextBtn(View.VISIBLE, "添加",
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(mContext,
								CarSafeListActivity.class));
					}
				});

	}

	private void selectPosition() {
		// 选择位置
		View relyview = LayoutInflater.from(mContext).inflate(
				R.layout.layout_fragment_tonguetip_product, null);
		PositionSelect positionSelect = new PositionSelect(mContext, relyview,
				provinceId, cityId, areaId);
		positionSelect.setOnSelectResultListener(new PositionSelect.SelectResultListener() {
					@Override
					public void selectResult(Area province, Area city, Area area) {

						//非空判断
		            	if(null == province || null == city || null == area){
		            		return;
		            	}
		            	
						// 记住当前选中的省市县
						provinceId = (null != province) ? province.RecNo : -1;
						cityId = (null != city) ? city.RecNo : -1;
						areaId = (null != area) ? area.RecNo : -1;

						// 文本赋值
						provinceName = (null != province) ? province.name : "";
						cityName = (null != city) ? city.name : "";
						areaName = (null != area) ? area.name : "";
						tvadress.setText(provinceName + " " + cityName + " " + areaName);
					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_adress:// 选择地区
			selectPosition();
			break;
		case R.id.btn_query:// 查询
			startActivity(new Intent(mContext,IllegalListActivity.class));
			break;
		case R.id.layout_num_type:// 车牌类型
			showToast("车牌类型");
			break;
		}
	}

}
