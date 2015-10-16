package com.baosteel.qcsh.ui.activity.my.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CommonPerson;
import com.baosteel.qcsh.model.CommonTicket;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseCameraActivity;

/**
 * 常用人员
 * 
 * @author 刘远祺
 * 
 */
public class CommonPersonEditActivity extends BaseCameraActivity implements OnClickListener {

	public static final String PERSON = "person";
	/** 标题栏 **/
	private TitleBar mTitleBar;

	/**完成**/
	private Button finishBtn;
	
	/**发票**/
	private CommonPerson person;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_person);

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
		mTitleBar.setRightIconVisibility(0, View.VISIBLE);
		mTitleBar.setBackgroudValue(getResources().getColor(R.color.index_red));
		mTitleBar.setTitle("编辑人员信息");

		//点击事件
		finishBtn = (Button)findViewById(R.id.finish_button);
		finishBtn.setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		Intent intent = getIntent();
		if(null != intent && intent.hasExtra(PERSON)){
			
			mTitleBar.setTitle("编辑人员信息");
			finishBtn.setText("完成");
			person = (CommonPerson)intent.getSerializableExtra(PERSON);
			
		}else{
			
			mTitleBar.setTitle("新增人员信息");
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
