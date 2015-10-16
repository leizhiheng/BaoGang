package com.baosteel.qcsh.ui.fragment.my;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.baosteel.qcsh.utils.CryptionUtil;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseFragment;

import org.json.JSONObject;

/**
 * 修改密码第二步
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-18
 */
public class FragmentModifyPayPwTwo extends BaseFragment{
	private static final String TAG = "FragmentModifyPwTwo";
			
	private EditText new_pw,check_pw;

	private String oldCode;					//上一步的验证码
	
	public FragmentModifyPayPwTwo(){
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
		View view = inflater.inflate(R.layout.fragment_modify_pay_pw_step_2, null);
		new_pw = (EditText) view.findViewById(R.id.et_new_pw);
		check_pw = (EditText) view.findViewById(R.id.et_check_pw);
		TextView tv_next_step = (TextView) view.findViewById(R.id.next_step);
		tv_next_step.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if ((new_pw == null ? 0 : new_pw.getText().length()) <= 0) {
					Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
					return;
				}

				if (new_pw.getText().length() < 6) {
					Toast.makeText(mContext, "密码不能少于6位数", Toast.LENGTH_SHORT).show();
					return;
				}

				if (!checkPw()) {
					Toast.makeText(mContext, "密码输入不一致", Toast.LENGTH_SHORT).show();
					return;
				}

				uploadNewPw();
			}
		});
		return view;
	}
	
	/**
	 * 判断两次密码输入是否一致
	 */
	public boolean checkPw(){
		return new_pw.getText().toString().equals(check_pw.getText().toString());
	}
	
	public void uploadNewPw(){
		String safaGrade= CryptionUtil.getPasswordSfae(new_pw.getText().toString());//密码安全强度
		RequestClient.queryAppUpdatePayPassword(mContext, oldCode,
				BaoGangData.getInstance().getUserInfo().getPhone(),
				BaoGangData.getInstance().getUserId(), new_pw.getText().toString(),safaGrade,
				new RequestCallback<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						if(JSONParseUtils.isSuccessRequest(mContext,response)){
							showToast(response.optString("msg"));
							if (mListener != null) {
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