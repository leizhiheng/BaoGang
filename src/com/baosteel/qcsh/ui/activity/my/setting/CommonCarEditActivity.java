package com.baosteel.qcsh.ui.activity.my.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CommonCar;
import com.baosteel.qcsh.model.CommonTicket;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseCameraActivity;

/**
 * 常用人员
 * 
 * @author 刘远祺
 * 
 */
public class CommonCarEditActivity extends BaseCameraActivity implements OnClickListener {
	
	/**发票**/
	public static final String CAR = "car";
	
	/**标题**/
	private TitleBar mTitle_bar;

	/**完成**/
	private Button finishBtn;
	
	/**车**/
	private CommonCar car;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_car_edit);
		
		//初始化控件
		initViews();
		
		//初始化数据
		initData();
	}

	/**
	 * 初始化控件
	 */
	private void initViews() {

		//标题
		mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
		mTitle_bar.setBackgroud(R.color.index_red);

		//点击事件
		finishBtn = (Button)findViewById(R.id.finish_button);
		finishBtn.setOnClickListener(this);
	}

	/**
	 * 初始化数据 
	 */
	private void initData() {

		Intent intent = getIntent();
		if(null != intent && intent.hasExtra(CAR)){
			
			mTitle_bar.setTitle("编辑常用车辆");
			finishBtn.setText("完成");
			car = (CommonCar)intent.getSerializableExtra(CAR);
			
			//显示信息
			showData(car);
			
			
		}else{
			
			mTitle_bar.setTitle("新增常用车辆");
			finishBtn.setText("添加");
		}
	}

	/**
	 * 显示车辆信息
	 * @param car
	 */
	private void showData(CommonCar car){
		
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.finish_button:

			//验证
//			if(isNull(companyNameEt)){
//				showToast("请输入公司名称");
//				return;
//			}
			
			if(null != car){
				//编辑
				//car.setCompany(getText(companyNameEt));
			}else{
				
				//添加
				car = new CommonCar();
			}
			
			//将数据回传
			Intent data = new Intent();
			data.putExtra(CAR, car);
			setResult(RESULT_OK, data);
			finish();
			
			break;
		}
	}
}
