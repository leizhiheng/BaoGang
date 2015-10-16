package com.baosteel.qcsh.ui.activity.my.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseCameraActivity;

/**
 * 常用房屋信息
 * 
 * @author 刘远祺
 * 
 */
public class CommonHouseEditActivity extends BaseCameraActivity implements OnClickListener {

	/**房屋**/
	public static final String HOUSE = "house";
	
	/** 标题栏 **/
	private TitleBar mTitleBar;

	/**完成**/
	private Button finishBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_house_edit);

		// 初始化控件
		initView();

		// 初始化数据
		initData();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {

		// 标题栏
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.showRightIcon(3, null);
		mTitleBar.setBackgroudValue(getResources().getColor(R.color.index_red));
		mTitleBar.setTitle("编辑房屋信息");

		// 点击事件
		finishBtn = (Button)findViewById(R.id.finish_button);
		finishBtn.setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		Intent intent = getIntent();
		if(null != intent && intent.hasExtra(HOUSE)){
			
			mTitleBar.setTitle("编辑房屋信息");
			finishBtn.setText("完成");
			//ticket = (CommonTicket)intent.getSerializableExtra(HOUSE);
			//companyNameEt.setText(ticket.getCompany());
			
		}else{
			
			mTitleBar.setTitle("新增房屋信息");
			finishBtn.setText("添加");
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.finish_button:

			finish();
			
			break;
		}
	}
}
