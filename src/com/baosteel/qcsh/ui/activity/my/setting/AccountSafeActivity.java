package com.baosteel.qcsh.ui.activity.my.setting;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.dialog.InputNumberDialog;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.UserInfo;
import com.baosteel.qcsh.ui.activity.my.UpdateEmailActivity;
import com.baosteel.qcsh.ui.activity.my.UpdateMobileActivity;
import com.baosteel.qcsh.ui.activity.my.UpdatePasswordActivity;
import com.baosteel.qcsh.ui.activity.my.UpdatePayPasswordActivity;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.ImageManager;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseCameraActivity;
import com.common.utils.ResourceUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * 账户安全
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class AccountSafeActivity extends BaseCameraActivity implements OnClickListener {

	/** 标题栏 **/
	private TitleBar mTitleBar;

	/**头像**/
	private ImageView account_head;
	
	/**绑定手机**/
	private TextView bindPhoneTv;
	 
	/**绑定邮箱**/
	private TextView bindEmailTv;
	 
	/**支付密码等级**/ 
	private TextView payPsdDegreeTv;

	/**用户名**/
	private TextView tv_username;

	/**身份证号码**/
	private TextView tv_idcard;

	/**提示语**/
	private LinearLayout receiverLayout;

	/**提示语**/
	private TextView tv_tip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_safe);

		// 初始化控件
		initView();
	}

	/**
	 * 初始化数据
	 */
	public void initView() {

		// 标题栏
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setRightIconVisibility(0, View.VISIBLE);
		mTitleBar.setBackgroudValue(getResources().getColor(R.color.index_red));
		mTitleBar.setTitle("账户与安全");

		//绑定手机，邮箱，密码等级
		account_head = (ImageView)findViewById(R.id.account_head);
		tv_username = (TextView)findViewById(R.id.tv_username);
		bindPhoneTv = (TextView) findViewById(R.id.bindPhoneTv);
		bindEmailTv = (TextView) findViewById(R.id.bindEmailTv);
		payPsdDegreeTv = (TextView) findViewById(R.id.payPsdDegreeTv);
		tv_idcard = (TextView) findViewById(R.id.tv_idcard);
		tv_tip = (TextView) findViewById(R.id.tv_tip);
		receiverLayout = (LinearLayout) findViewById(R.id.receiverLayout);

		// 点击事件
		account_head.setOnClickListener(this);
		findViewById(R.id.loginPsdLayout).setOnClickListener(this);
		findViewById(R.id.bindPhoneLayout).setOnClickListener(this);
		findViewById(R.id.bindEmailLayout).setOnClickListener(this);
		findViewById(R.id.payPsdLayout).setOnClickListener(this);
		findViewById(R.id.identityCardLayout).setOnClickListener(this);
	}

	private void loadUserInfo(){
		if(!userIsLogin()){
			return;
		}
		String userId = BaoGangData.getInstance().getUser().userId;
		String client = ConstantsAPI.Client_Android;
		RequestClient.queryAppUserInfo(mContext, userId, "1", client, new RequestCallback<JSONObject>(false) {
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					JSONObject returnMap = response.optJSONObject("returnMap");

					//解析userId
					Gson gson = new Gson();
					UserInfo userInfo = gson.fromJson(returnMap.toString(), UserInfo.class);
					if (null != userInfo) {
						initData(userInfo);//初始化信息
						BaoGangData.getInstance().setUserInfo(userInfo);

					} else {

						//登录失败，用户信息解析错误
						showToast("获取用户信息失败");
					}
				}
			}
		});
	}


	/**
	 * 初始化数据
	 */
	public void initData(UserInfo userInfo) {
		tv_username.setText("尊敬的" + userInfo.getUserName());
		ImageManager.Load(userInfo.getHeadUrl(), account_head);
		//手机绑定
		boolean isBindPhone=true;
		String phone=userInfo.getPhone();
		if(TextUtils.isEmpty(phone)){
			isBindPhone=false;
			bindPhoneTv.setText("马上设置绑定");
		}else{
			bindPhoneTv.setText(StringUtils.changePhoneForrmat(phone));
		}

		//邮箱绑定
		boolean isBindEmail=true;
		String email=userInfo.getEmail();
		if(TextUtils.isEmpty(email)){
			isBindEmail=false;
			bindEmailTv.setText("马上设置绑定");
		}else{
			bindEmailTv.setText(StringUtils.changeEmailForrmat(email));
		}

		//设置支付密码安全程度
		boolean isSetPayPwd=true;
		String grade = userInfo.getPayPasswordGrade2Chinese();
		if(null!=grade){
			Spanned showContent= Html.fromHtml(ResourceUtils.changeStringColor("#666666","安全程度：")+ResourceUtils.changeStringColor("#F03A1A",grade));
			payPsdDegreeTv.setText(showContent);
		}else{
			isSetPayPwd=false;
			Spanned showContent= Html.fromHtml(ResourceUtils.changeStringColor("#F03A1A","马上设置绑定"));
			payPsdDegreeTv.setText(showContent);
		}

		//设置身份证号码
		boolean isBindIdcard=true;
		String idCard=userInfo.getIdentityCard();
		if(TextUtils.isEmpty(idCard)){
			isBindIdcard=false;
			Spanned showContent= Html.fromHtml(ResourceUtils.changeStringColor("#F03A1A","马上设置绑定"));
			tv_idcard.setText(showContent);
		}else{
			Spanned showContent= Html.fromHtml(ResourceUtils.changeStringColor("#666666",idCard));
			tv_idcard.setText(showContent);
		}
		String tip="";//提示语
		if(!isBindPhone){
			tip="为了您的账户安全，请尽快绑定手机号码！";
		}else if(!isBindEmail){
			tip="为了您的账户安全，请尽快绑定邮箱！";
		}else if(!isSetPayPwd){
			tip="为了您的账户安全，请尽快设置支付密码！";
		}else if(!isBindIdcard){
			tip="为了您的账户安全，请尽快绑定身份证号码！";
		}else{
			receiverLayout.setVisibility(View.GONE);
		}
		tv_tip.setText(tip);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.account_head:
			
			//修改头像
			showCameraPopwindow(v);
			
			break;
		case R.id.loginPsdLayout:
			
			//登录密码
			startActivity(new Intent(mContext, UpdatePasswordActivity.class));
			
			break;
		case R.id.bindPhoneLayout:
			
			//绑定手机
			startActivity(new Intent(mContext, UpdateMobileActivity.class));
			
			break;
		case R.id.bindEmailLayout:
			
			//绑定邮箱
			if(TextUtils.isEmpty(BaoGangData.getInstance().getUserInfo().getEmail())){//未绑定
				startActivity(new Intent(mContext, UpdateEmailActivity.class).putExtra(UpdateEmailActivity.OPERATE_TYPE,UpdateEmailActivity.OPERATE_BIND));
			}else{//已绑定
				startActivity(new Intent(mContext, UpdateEmailActivity.class).putExtra(UpdateEmailActivity.OPERATE_TYPE,UpdateEmailActivity.OPERATE_UPDATE));
			}

			break;
		case R.id.payPsdLayout:
			
			//支付密码
			startActivity(new Intent(mContext, UpdatePayPasswordActivity.class));
			
			break;
		case R.id.identityCardLayout:
			
			//身份证号码
			final InputNumberDialog dialog = new InputNumberDialog(mContext,BaoGangData.getInstance().getUserInfo().getIdentityCard());
			dialog.show();
			dialog.setOnSelectedResultListener(new InputNumberDialog.OnSelectedResultListener() {
				@Override
				public void confirm(String idCard) {
					tv_idcard.setText(idCard);
				}
			});
			
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadUserInfo();
	}
}
