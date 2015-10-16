package com.baosteel.qcsh.ui.fragment.my;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseFragment;

import org.json.JSONObject;

/**
 * 修改手机第一步
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-18
 */
public class FragmentModifyMobileOne extends BaseFragment implements View.OnClickListener{

	private Context mContext;
	private Handler mHandler;
	private TextView identityWay, get_code_tv;
	private EditText check_code;
	private TextView phoneIdentifyTv;						//当前手机号码

	private OnStepToModifyPwListener mListener;
	private String  phone;									//当前绑定的手机号码

	public interface OnStepToModifyPwListener {
		void stepToModifyPw(String code);
	}

	public void setOnStepToModifyPwListener(OnStepToModifyPwListener listener) {
		mListener = listener;
	}

	public FragmentModifyMobileOne(Context context, Handler handler) {
		this.mContext = context;
		this.mHandler = handler;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_modify_mobile_step_1, null);

		//初始化控件
		identityWay = (TextView) view.findViewById(R.id.tv_identity_way);
		get_code_tv = (TextView) view.findViewById(R.id.btn_get_message);
		phoneIdentifyTv = (TextView) view.findViewById(R.id.phoneIdentifyTv);
		check_code = (EditText) view.findViewById(R.id.et_check_code);
		
		view.findViewById(R.id.next_step).setOnClickListener(this);
		get_code_tv.setOnClickListener(this);
		view.findViewById(R.id.phoneIdentifyLayout).setOnClickListener(this);
		phone=BaoGangData.getInstance().getUserInfo().getPhone();
		String show="您当前的手机号码："+StringUtils.changePhoneForrmat(phone);
		phoneIdentifyTv.setText(show);
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
		
		
	}
	

	
	private Timer mTimer = new Timer();
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

						if (sec < 1) {
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
	public void onDestroy() {
		super.onDestroy();
		
		//界面退出的时候，定时器也要杀掉
		try{
			mTimer.cancel();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/***
	 * 验证验证码
	 */
	private boolean isSuccess;

	/**
     * 验证验证手机号码
     */
	private void postValidateCode() {
		final String code = check_code.getText().toString();
		if(!TextUtils.isEmpty(code)){
			RequestClient.appMobileValidate(mContext, phone, code, "4", new RequestCallback<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					if(JSONParseUtils.isSuccessRequest(mContext,response)){
						showToast("验证成功！");
						if (mListener != null) {
							mTimer.cancel();
							mListener.stepToModifyPw(code);
						}
					}
				}
			});
		}else{
			showToast("请输入验证码！");
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.phoneIdentifyLayout:
			
			//选择验证方式
			
			
			break;
		case R.id.next_step:
			
			//下一步
			postValidateCode();

			break;

		case R.id.btn_get_message:
			
			//获取验证码
			int type = 0;/*Integer.parseInt(spinner_type.getTag().toString())*/;
			switch (type) {
			case 0:
				//获取手机验证码
				getPhoneVerifyCode();
				showResendTip();
				
				break;

			case 1:
				//获取邮箱验证码
				//getEmailVerifyCode();
				showResendTip();
				
				break;
			}
			
			break;
		}
	}

	/**
	 * 获取验证码
	 */
	private void  getPhoneVerifyCode(){
		RequestClient.appReceiveValidateCode(mContext, phone, "4",  new RequestCallback<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if(JSONParseUtils.isSuccessRequest(mContext,response)){
					showToast("验证码已发送至您的手机，请注意查收！");
				}
			}
		});
	}
}
