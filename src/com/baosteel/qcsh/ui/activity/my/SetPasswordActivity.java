package com.baosteel.qcsh.ui.activity.my;

import org.json.JSONObject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.User;
import com.baosteel.qcsh.ui.activity.MainActivity;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.Preferences;
import com.baosteel.qcsh.utils.Preferences.PreKey;
import com.common.base.BaseActivity;
import com.common.utils.NetworkUtils;
import com.common.view.topbar.HeadView;
import com.google.gson.Gson;

/**
 * 手机快速注册-设置登录密码
 * @author 刘远祺
 * 
 */
public class SetPasswordActivity extends BaseActivity implements OnClickListener {

	/**手机号码**/
	public static final String PHONE 			= "phone";
	
	/**手机验证码**/
	public static final String PHONE_CODE 		= "phoneCode";
	
	
	/**标题栏**/
	private HeadView mHeadView;
	
	/**密码**/
	private EditText passwordEt;
	
	/**再次输入密码**/
	private EditText passwordAgainEt;
	
	/**注册的手机号码**/
	private String phone;
	
	/**手机验证码**/
	private String phoneCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_password);
		
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
		mHeadView.setTitle("设置密码");
		mHeadView.showRightImageBtn(View.VISIBLE, null);
		mHeadView.setHeadViewBackGroundColor(getResources().getColor(R.color.index_red));
		
		//密码
		passwordEt = (EditText)findViewById(R.id.passwordEt);
		passwordAgainEt = (EditText)findViewById(R.id.passwordAgainEt);
		
		//完成
		findViewById(R.id.finish_button).setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData(){
		
		//取手机号码(从上一个界面传递过来的)
		phone = getStringExtra(PHONE);
		phoneCode = getStringExtra(PHONE_CODE);
		
	}
	

	public void onClick(View v){
		switch (v.getId()) {
		case R.id.finish_button:
			
			//注册
			postRegist();
			
			break;
		}
	}
	
	/**
	 * 登录前的验证
	 * @return
	 */
	private boolean validate(){
		
		if(isNull(passwordEt)){
			showToast("请输入密码");
			return false;
		}
		
		if(isNull(passwordAgainEt)){
			showToast("请再次输入密码");
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 注册
	 */
	private void postRegist(){
		
		//验证不成功直接返回
		if(!validate()){
			return;
		}
		
		//密码,验证码，IP地址，客户端类型
		String password = getText(passwordEt);
		String validateKey = phoneCode;
		String phoneIp = NetworkUtils.getIpAddress(mContext);
		String client = ConstantsAPI.Client_Android;
		String shopping_id = Preferences.getString(PreKey.SHOPPINGIDS);
		
		RequestClient.queryAppMobileRegisiter(mContext, phone, validateKey, phoneIp, client, password, shopping_id, new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				if(JSONParseUtils.isSuccessRequest(mContext, response)){
					
					JSONObject returnMap = response.optJSONObject("returnMap");
					
					//解析userId
					Gson gson = new Gson();
					User user = gson.fromJson(returnMap.toString(), User.class);
					if(null != user && !TextUtils.isEmpty(user.userId)){
						
						//登录成功
						BaoGangData.getInstance().setUser(user);
						
						//保存用户信息-本地
						Preferences.putString(PreKey.USER, response.toString());
						Preferences.removeKey(PreKey.SHOPPINGIDS);
						
						//跳转到主界面
						startToMainActivity(MainActivity.TAB_MY);
						
						//完成
						finish();
					}else{
						
						//登录失败，用户信息解析错误
						showToast("获取用户信息失败");
					}
					
				}
			}
		});
		
		
	}
}
