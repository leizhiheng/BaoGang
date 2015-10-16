package com.baosteel.qcsh.ui.activity.my.setting;

import android.os.Bundle;

import com.baosteel.qcsh.R;
import com.common.base.BaseCameraActivity;

/**
 * 消息设置
 * @author 刘远祺
 *
 */
public class MessageSettingActivity extends BaseCameraActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_message);
		setTitle("消息设置");
		
		//初始化控件
		initView();	
	}


	/**
	 * 初始化控件
	 */
	private void initView() {


	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		
		
	}
}
