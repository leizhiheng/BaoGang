package com.baosteel.qcsh.ui.fragment.my;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Type;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseFragment;
import com.common.volley.VolleyError;

/**
 * 忘记密码第一步
 * @author blue
 *
 * @todo TODO
 *
 * @date 2015-9-18
 */
public class FragmentForgetPwOne extends BaseFragment implements View.OnClickListener{

	private Context mContext;
	private Handler mHandler;
	private TextView get_code_tv;
	private EditText identityWay;
	private EditText check_code;

	private OnStepToForgetPwListener mListener;
	
	private String mPhone;
	
	public interface OnStepToForgetPwListener {
		void stepToModifyPw(String phone, String checkCode);
	}

	public void setOnStepToForgetPwListener(OnStepToForgetPwListener listener) {
		mListener = listener;
	}

	public FragmentForgetPwOne(Context context, Handler handler) {
		this.mContext = context;
		this.mHandler = handler;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forget_pw_step_1, null);

		//初始化控件
		identityWay = (EditText) view.findViewById(R.id.tv_identity_way);
		get_code_tv = (TextView) view.findViewById(R.id.btn_get_message);
		check_code = (EditText) view.findViewById(R.id.et_check_code);
		
		view.findViewById(R.id.next_step).setOnClickListener(this);
		get_code_tv.setOnClickListener(this);
		view.findViewById(R.id.phoneIdentifyLayout).setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//初始化数据
		initData();
	}
	
	/**
	 * 初始化数据
	 */
	private void initData(){
		
		
		//默认用手机验证方式
		selectType(0);
	}
	
	private void selectType(int type){
		switch (type) {
		case 0:
			//手机验证方式
//			identityWay.setText("已验证手机:" + StringUtils.ensurePhoneNum("18566682807"));
			get_code_tv.setText("获取验证码");
			check_code.setHint("请输入手机验证码");
			//spinner_type.setTag(0);
			
			break;

		case 1:
			//邮箱验证方式
			identityWay.setText("已验证邮箱:" + "252153208@qq.com");
			get_code_tv.setText("获取邮箱验证码");
			check_code.setHint("请输入邮箱验证码");
			//spinner_type.setTag(1);
			
			break;
		}
	}
	
	/**
	 * 获取验证方式
	 * @return
	 */
	private List<String> getData() {
		ArrayList<String> data = new ArrayList<String>();
		
//		UserInfo userInfo = AppContext.mUserInfo;
//		if(!TextUtils.isEmpty(userInfo.phone)){
			data.add("已验证手机");
//		}

		//暂时屏蔽邮箱
//		if(userInfo.isBoundEmail()){
			data.add("已验证邮箱");
//		}
		
		return data;
	}

	/**
	 * 获取手机验证码
	 */
	private void getPhoneVerifyCode() {
		
		String phone = identityWay.getText().toString();
		if (TextUtils.isEmpty(phone)) {
			showToast("手机号码不能为空");
			return;
		} else if (!StringUtils.isPhoneNumber(phone)) {
			showToast("请输入正确的手机号码");
			return;
		}
		
		mPhone = phone;
		
		//开始计时
		showResendTip();
		
		//type:使用模块(1:注册 2:忘记密码)
		RequestClient.appReceiveValidateCode(getActivity(), phone, "2", new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.d(TAG, "appReceiveValidateCode response = " + response.toString());

				if (JSONParseUtils.isSuccessRequest(FragmentForgetPwOne.this.getActivity(), response)) {

					isSuccess = true;
					showResendTip();
					showToast("验证码为：123456");

				} else {

					String msg = JSONParseUtils.getString(response, "msg");
					showToast(msg);

				}
			}
		});
		

	}
	
	/**
	 * 获取邮箱验证码
	 */
	private void getEmailVerifyCode() {
//		UserInfo userInfo = AppContext.mUserInfo;
//		RequstClient.sendCheckEmail(userInfo.userId, userInfo.userName, userInfo.email, "1", "0", new CustomResponseHandler(FragmentModifyPwOne.this.getActivity()) {
//			@Override
//			public void onFailure(Throwable error, String content) {
//				super.onFailure(error, content);
//			}
//
//			@Override
//			public void onSuccess(int statusCode, Header[] headers, String content) {
//				super.onSuccess(statusCode, headers, content);
//				
//				int type = JSONParseUtils.getInt(content, "type");
//				String msg = JSONParseUtils.getString(content, "msg");
//				if(1 != type){
//					showMyToast(msg);
//					return;
//				}else{
//					
//					showMyToast(msg);
//					
//					//倒计时60秒后可以重新获取验证码
//					showResendTip();
//				}
//			}
//		});

	}

	private Timer mTimer = null;
	private int sec = 60;

	private void showResendTip() {

		mTimer = new Timer();
		get_code_tv.setEnabled(false);
		mTimer.schedule(new TimerTask() {

			@Override
			public void run() {

				sec--;
				mHandler.post(new Runnable() {
					@Override
					public void run() {

						if (sec < 1 || isSuccess) {
							get_code_tv.setEnabled(true);
							get_code_tv.setText("重新获取");
							sec = 60;
							mTimer.cancel();
						} else {
							String tipMsg = String.format(getString(R.string.time_tip), sec);
							get_code_tv.setText(tipMsg);
						}
					}
				});
			}
		}, 0, 1000);
	}

	@Override
	public void onPause() {
		super.onPause();
		
		//界面退出的时候，定时器也要杀掉
		try{
			if(null!=mTimer){
				mTimer.cancel();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/***
	 * 验证验证码
	 */
	private boolean isSuccess;

	/**
     * 验证验证码
     *
     * @param phone					手机号码/邮箱
     * @param messageVerifyCode   	验证码
     * @param flag 					0是手机验证码，1是邮箱验证码		
     * @param mHandler
     */
	private void postValidateCode() {
		
		//判断输入的手机验证码
		String code = getText(check_code);
		if(TextUtils.isEmpty(code)){
			showToast("请输入验证码");
			return;
		}
		
		//测试手机号码18566682807
		//验证码使用模块(1:注册2:忘记密码)
		RequestClient.appMobileValidate(getActivity(), mPhone, code,  "2", new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(FragmentForgetPwOne.this.getActivity(), response)) {
					
					//验证码输入正确，进入下一个界面
					isSuccess = true;
					if (mListener != null) {
						mListener.stepToModifyPw(mPhone, "123456");
					}
					if(null!=mTimer){
						mTimer.cancel();
					}
				} else {
					String errorMsg = JSONParseUtils.getString(response, "msg");
					showToast(errorMsg);
				}
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.phoneIdentifyLayout:
			
			//选择验证方式
			
			
			break;
		case R.id.next_step:
			
			//下一步
			String phone = identityWay.getText().toString();
			if (TextUtils.isEmpty(phone)) {
				showToast("手机号码不能为空");
				return;
			} else if (!StringUtils.isPhoneNumber(phone)) {
				showToast("请输入正确的手机号码");
				return;
			}
			postValidateCode();	
			break;

		case R.id.btn_get_message:
			
			//获取验证码
			int type = 0;/*Integer.parseInt(spinner_type.getTag().toString())*/;
			switch (type) {
			case 0:
				//获取手机验证码
				getPhoneVerifyCode();
				//showResendTip();
				
				break;

			case 1:
				//获取邮箱验证码
				//getEmailVerifyCode();
				//showResendTip();
				
				break;
			}
			
			break;
		}
	}
}
