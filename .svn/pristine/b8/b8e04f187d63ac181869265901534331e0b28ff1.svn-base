package com.baosteel.qcsh.ui.fragment.my;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseFragment;

import org.json.JSONObject;

/**
 * 修改手机第二步
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-18
 */
public class FragmentModifyMobileTwo extends BaseFragment{
	private static final String TAG = "FragmentModifyPwTwo";
			
	private EditText new_pw,check_pw;
	private EditText et_phone;							//新手机号码
	private EditText et_check_code;						//验证码
	private String phone;
	private String code;
	private String oldCode;								//上一步的验证码
	private TextView btn_get_message;					//获取验证码

	
	private Context mContext;
	public FragmentModifyMobileTwo(Context context){
		mContext = context;
	}
	private OnStepToFinishListener mListener;

	public interface OnStepToFinishListener {
		void stepToFinish();
	}

	public void setOnStepToFinishListener(OnStepToFinishListener listener) {
		mListener = listener;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_modify_mobile_step_2, null);
		new_pw = (EditText) view.findViewById(R.id.et_new_pw);
		check_pw = (EditText) view.findViewById(R.id.et_check_pw);
		et_phone = (EditText) view.findViewById(R.id.et_phone);
		et_check_code = (EditText) view.findViewById(R.id.et_check_code);
		TextView tv_next_step = (TextView) view.findViewById(R.id.next_step);
		btn_get_message = (TextView) view.findViewById(R.id.btn_get_message);
		btn_get_message.setOnClickListener(new OnClickListener() {//获取验证码

			@Override
			public void onClick(View arg0) {
				getPhoneVerifyCode();//获取验证码
			}
		});
		tv_next_step.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				phone = et_phone.getText().toString();
				code = et_check_code.getText().toString();
				if (TextUtils.isEmpty(phone)) {
					showToast("请输入手机号码！");
					return;
				} else if (!StringUtils.isPhoneNumber(phone)) {
					showToast("请输入正确的手机号码！");
					return;
				} else if (TextUtils.isEmpty(code)) {
					showToast("请输入验证码！");
					return;
				}
				queryAppUpdatePhone();
			}
		});

		return view;
	}

	/**
	 * 获取验证码
	 */
	private void  getPhoneVerifyCode(){
		phone=et_phone.getText().toString();
		RequestClient.appReceiveValidateCode(mContext, phone, "5", new RequestCallback<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					showToast("验证码已发送至您的手机，请注意查收！");
				}
			}
		});
	}

	/**
	 * 验证验证手机号码
	 */
	private void queryAppUpdatePhone() {
		RequestClient.queryAppUpdatePhone(mContext, oldCode, BaoGangData.getInstance().getUser().phone, code, phone, new RequestCallback<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					if (mListener != null) {
						showToast(response.optString("msg"));
						mListener.stepToFinish();
					}
				}
			}
		});
	}

	/**
	 * 设置上一步的验证码
	 * @param oldCode
	 */
	public void setOldCode(String oldCode){
		this.oldCode=oldCode;
	}
}