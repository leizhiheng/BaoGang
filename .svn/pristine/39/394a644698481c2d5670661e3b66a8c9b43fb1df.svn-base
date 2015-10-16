package com.baosteel.qcsh.ui.fragment.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
 * 修改密码第二步
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-18
 */
public class FragmentModifyEmailTwo extends BaseFragment{
	private static final String TAG = "FragmentModifyPwTwo";
			
	/**新的邮箱**/
	private EditText emailNewEt;

	private String pwd;					//用户登录密码
	
	public FragmentModifyEmailTwo(){
	}
	private OnStepToFinishListener mListener;

	public interface OnStepToFinishListener {
		void stepToFinish(String returnMsg);
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
		View view = inflater.inflate(R.layout.fragment_modify_email_step_2, null);
		emailNewEt = (EditText) view.findViewById(R.id.emailNewEt);
		TextView tv_next_step = (TextView) view.findViewById(R.id.next_step);
		tv_next_step.setText("发送");
		tv_next_step.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				queryAppUpdateEmail();
			}
		});
		return view;
	}

	/**
	 * 修改邮箱发送验证链接
	 */
	private void queryAppUpdateEmail() {
		String newEmail = emailNewEt.getText().toString();//密码
		if (TextUtils.isEmpty(newEmail)) {
			showToast("请输入邮箱！");
			return;
		}else if(!StringUtils.isEmail(newEmail)){
			showToast("请输入正确的邮箱！");
			return;
		}
		RequestClient.queryAppUpdateEmail(mContext, BaoGangData.getInstance().getUserId(), pwd, newEmail, new RequestCallback<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					String msg = response.optString("msg");
//					showToast(msg);
					mListener.stepToFinish(msg);
				}
			}
		});
	}

	/**
	 * 设置用户密码
	 * @param pwd
	 */
	public void setUserPwd(String pwd){
		this.pwd=pwd;
	}

}