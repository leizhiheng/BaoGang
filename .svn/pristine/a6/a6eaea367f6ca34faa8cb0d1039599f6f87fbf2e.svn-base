package com.baosteel.qcsh.ui.activity.my;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.URLs;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.User;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.Preferences;
import com.baosteel.qcsh.utils.Preferences.PreKey;
import com.common.base.BaseActivity;
import com.common.net.HttpUtils;
import com.common.net.RequestParams;
import com.common.view.topbar.HeadView;
import com.common.volley.Request.Method;

/**
 * 绑定健康点
 * @author 刘远祺
 * 
 */
public class BindHealthPointActivity extends BaseActivity implements OnClickListener {

	/**标题栏**/
	private HeadView mHeadView;
	
	/**手机号码**/
	private EditText phoneEt;
	
	/**短信验证码**/
	private EditText phoneCodeEt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bind_health_point);
		
		//初始化控件
		initView();
		
		//初始化数据
		initData();
	}


	/**
	 * 初始化控件
	 */
	private void initView(){
		
		//标题
		mHeadView = (HeadView) findViewById(R.id.headview);
		mHeadView.setTitle("绑定健康点");
		mHeadView.showRightImageBtn(View.VISIBLE, null);
		mHeadView.setHeadViewBackGroundColor(getResources().getColor(R.color.index_red));
		
		//下一步
		findViewById(R.id.next_step_button).setOnClickListener(this);
		findViewById(R.id.protocalTv).setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		
	}


	public void onClick(View v){
		switch (v.getId()) {
		case R.id.next_step_button:
			
			//确认绑定
			showToast("绑定成功");
			finish();
			
			break;
		}
	}
	
	/**
	 * 登录前的验证
	 * @return
	 */
	private boolean validate(){
		
		if(isNull(phoneEt)){
			showToast("请输入手机号码");
			return false;
		}
		
		if(isNull(phoneCodeEt)){
			showToast("请输入手机验证码");
			return false;
		}
		
		return true;
	}
	
	
	
}
