package com.baosteel.qcsh.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;

import org.json.JSONObject;

/**
 * 设置身份证号码-通用
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-16
 */
public class InputNumberDialog extends Dialog{

	private TextView mTvTitle;
	private TextView mBtnOk;
	private TextView mBtnCancel;

	private EditText et_idcard;			//身份证号码
	
	private String mMsg;

	private String idCard;				//身份证号码
	
	public InputNumberDialog(Context context,String idCard) {
		super(context, R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_update_identify_number);
		initView();
		setOnClickListener();
		this.idCard=idCard;
	}
	
	private void initView() {
		mTvTitle = (TextView) findViewById(R.id.titleTv);
		mBtnOk = (TextView) findViewById(R.id.btn_ok);
		mBtnCancel = (TextView) findViewById(R.id.btn_cancel);
		et_idcard = (EditText) findViewById(R.id.et_idcard);
		if(!TextUtils.isEmpty(idCard)){
			et_idcard.setText(idCard);
		}
	}
	
	public void setOnClickListener() {
		mBtnOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(et_idcard.getText().toString())){
					Toast.makeText(getContext(),"请输入身份证号码！",Toast.LENGTH_SHORT).show();
					return;
				}else if(!StringUtils.isIdCard(et_idcard.getText().toString())){
					Toast.makeText(getContext(),"请输入正确的身份证号码！",Toast.LENGTH_SHORT).show();
					return;
				}
				queryAppUpdateUserInfo(et_idcard.getText().toString());
			}
		});
		mBtnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}

	/**
	 * 修改身份证号码
	 * @param idCard
	 */
	private void queryAppUpdateUserInfo(final String idCard){
		RequestClient.queryAppUpdateUserInfo(getContext(), "", "", "",
				BaoGangData.getInstance().getUserId(), "", idCard, new RequestCallback<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if(JSONParseUtils.isSuccessRequest(getContext(),response)){
					String  msg = response.optString("msg");
					Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
					if(null!=listener){
						listener.confirm(idCard);
					}
					dismiss();
				}
			}
		});
	}
	
	public void setTitle(String msg) {
		mTvTitle.setText(msg);
	}

	private OnSelectedResultListener listener;

	public void setOnSelectedResultListener(OnSelectedResultListener listener){
		this.listener=listener;
	}

	public interface OnSelectedResultListener{
		void confirm(String idCard);
	}
}
