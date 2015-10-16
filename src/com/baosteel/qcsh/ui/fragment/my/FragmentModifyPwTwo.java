package com.baosteel.qcsh.ui.fragment.my;

import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseFragment;

/**
 * 修改密码第二步
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-18
 */
public class FragmentModifyPwTwo extends BaseFragment{
	private static final String TAG = "FragmentModifyPwTwo";
			
	private EditText new_pw,check_pw;
	
	private Context mContext;
	
	/**
	 * 忘记密码时输入的验证手机号
	 */
	private String mPhone;
	
	public FragmentModifyPwTwo(Context context){
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
		View view = inflater.inflate(R.layout.fragment_modify_pw_step_2, null);
		new_pw = (EditText) view.findViewById(R.id.et_new_pw);
		check_pw = (EditText) view.findViewById(R.id.et_check_pw);
		TextView tv_next_step = (TextView) view.findViewById(R.id.next_step);
		tv_next_step.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				uploadNewPw();
//				if (mListener != null) {
//					mListener.stepToFinish();
//				}
			}
		});
		return view;
	}
	
	/**
	 * 
	 * @Description 提交新密码
	 * @Author blue
	 * @Time 2015-9-28
	 */
	public void uploadNewPw(){
		
		if((new_pw == null ? 0 : new_pw.getText().length()) <= 0){
			Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (new_pw.getText().length() < 6) {
			Toast.makeText(mContext, "密码不能少于6位数", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(!checkPw()){
			Toast.makeText(mContext, "密码输入不一致", Toast.LENGTH_SHORT).show();
			return;
		}
		
////		if (StringUtils.isEmpty(mPhone)) {
//			mPhone = "18566682807";
////		}
		
		RequestClient.queryAppUpdatePassword(getActivity(), "123456", mPhone, new_pw.getText().toString(), new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				Log.d(TAG, "queryAppUpdatePassword  onResponse = "+response.toString());
				if (JSONParseUtils.isSuccessRequest(FragmentModifyPwTwo.this.getActivity(), response)) {
					if (mListener != null) {
						mListener.stepToFinish();
					} else {
						showToast(JSONParseUtils.getString(response, "msg"));
					}
				}
			}
			
			@Override
			public void onFinish() {
				super.onFinish();
				Log.d(TAG, "queryAppUpdatePassword  onFinish");
			}
		});
				
	}
	
	/**
	 * 判断两次密码输入是否一致
	 */
	public boolean checkPw(){
		return new_pw.getText().toString().equals(check_pw.getText().toString());
	}
	
	public void setPhone(String phone) {
		this.mPhone = phone;
	}
}