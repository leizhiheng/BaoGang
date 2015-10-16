package com.baosteel.qcsh.ui.activity.my;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.ui.activity.CommonWebPageActivity;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * 手机快速注册
 * @author 刘远祺
 * 
 */
public class PhoneRegistActivity extends BaseActivity implements OnClickListener {

	/**标题栏**/
	private HeadView mHeadView;
	
	/**手机号码**/
	private EditText phoneEt;
	
	/**短信验证码**/
	private EditText phoneCodeEt;

	/**用户协议**/
	private CheckBox cb_user_protocol;


	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_regist);
		
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
		mHeadView.setTitle("手机快速注册");
		mHeadView.showRightImageBtn(View.GONE, null);
		mHeadView.setHeadViewBackGroundColor(getResources().getColor(R.color.index_red));
		
		//手机，手机验证码
		phoneEt = (EditText)findViewById(R.id.phoneEt);
		phoneCodeEt = (EditText)findViewById(R.id.phoneCodeEt);
		cb_user_protocol = (CheckBox) findViewById(R.id.cb_user_protocol);

		//下一步
		findViewById(R.id.next_step_button).setOnClickListener(this);
		findViewById(R.id.protocalTv).setOnClickListener(this);
		findViewById(R.id.getPhoneCodeBtn).setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		
	}


	public void onClick(View v){
		switch (v.getId()) {
		case R.id.getPhoneCodeBtn:
			
			//获取短信验证码
			getPhoneCode();
			
			break;
		case R.id.next_step_button:
			if(cb_user_protocol.isChecked()){
				//下一步
				mobileValidate();
			}else{
				showToast("请您还没同意注册协议！");
			}

			break;
		case R.id.protocalTv:
			
			//七彩生活注册协议
			Intent i = new Intent();
			i.setClass(mContext, CommonWebPageActivity.class);
			i.putExtra(CommonWebPageActivity.Title, "七彩生活注册协议");
			i.putExtra(CommonWebPageActivity.Content_Type, ConstantsAPI.Content_Type_1);
			startActivity(i);
			
			
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
	
	/**
	 * 请求手机验证码
	 */
	private void getPhoneCode(){
		
		//非空判断
		if(isNull(phoneEt)){
			showToast("请输入手机号码");
			return;
		}
		
		//手机合法性验证
		String phone = getText(phoneEt);
		if(!StringUtils.isPhoneNumber(phone)){
			showToast("请输入正确的手机号码");
			return;
		}
		
		//获取验证码
		RequestClient.appReceiveValidateCode(mContext, phone, "1", new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				
				if(JSONParseUtils.isSuccessRequest(mContext, response)){
					
					//获取状态信息
					String msg = JSONParseUtils.getString(response, "msg");
					showToast(msg);
					
					//验证码默认是123456
					phoneCodeEt.setText("123456");
					
				}
			}
		});
		
	}
	
	/**
	 * 手机验证
	 */
	private void mobileValidate(){
		
		//验证不成功直接返回
		if(!validate()){
			return;
		}
		
		//验证手机，验证码
		final String phone = getText(phoneEt);
		final String validateKey = getText(phoneCodeEt);
		RequestClient.appMobileValidate(mContext, phone, validateKey, "1", new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				
				//验证成功
				if(JSONParseUtils.isSuccessRequest(mContext, response)){
					
					//跳转到注册密码界面
					Intent intent = new Intent(mContext, SetPasswordActivity.class);
					intent.putExtra(SetPasswordActivity.PHONE, phone);
					intent.putExtra(SetPasswordActivity.PHONE_CODE, validateKey);
					startActivity(intent);
				}
			}
		});
		
	}
}
