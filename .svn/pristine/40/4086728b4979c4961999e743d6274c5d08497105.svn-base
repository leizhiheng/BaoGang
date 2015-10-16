package com.baosteel.qcsh.ui.activity.my.setting;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.AppContext;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.dialog.SimpleMsgDialog;
import com.baosteel.qcsh.dialog.VersionTipDialog;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.UpdateInfo;
import com.baosteel.qcsh.model.UserInfo;
import com.baosteel.qcsh.model.QueryAppGoodsDetailBean.ReturnMapEntity.ImgsEntity;
import com.baosteel.qcsh.ui.popwindow.SelectApplyReasonWindow;
import com.baosteel.qcsh.utils.ImageManager;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.Preferences;
import com.baosteel.qcsh.utils.UpdateVersionTool;
import com.baosteel.qcsh.utils.Preferences.PreKey;
import com.common.base.BaseCameraActivity;
import com.google.gson.Gson;

/**
 * 设置界面
 * @author 刘远祺
 *
 */
public class SettingActivity extends BaseCameraActivity implements OnClickListener {

	/**头像**/
    private ImageView mAccount_head;
    
    /**会员等级**/
    private TextView mAccount_level;
    
    /**昵称**/
    private TextView mAccount_username;
    
    /**性别**/
    private TextView mAccount_sex;
    
    /**新版本提示**/
    private TextView mVersion_tip_tv;
   
    /**New字段**/
    private TextView mVersion_new_tv;
    
    private boolean hasNewVersion;
    private UpdateInfo mUpdateInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		setTitle("设置");
		
		//初始化控件
		initView();	
		
		//加载用户信息
		loadUserInfo();
		
		//获取版本信息
		loadVersionInfo();
	}


	/**
	 * 初始化控件
	 */
	private void initView() {

		mAccount_head = (ImageView) findViewById(R.id.account_head);
		mAccount_level = (TextView) findViewById(R.id.account_level);
		mAccount_username = (TextView) findViewById(R.id.account_username);
		mAccount_sex = (TextView) findViewById(R.id.account_sex);
		mVersion_tip_tv = (TextView) findViewById(R.id.version_tip_tv);
		mVersion_new_tv = (TextView) findViewById(R.id.version_new_tv);
		
		findViewById(R.id.account_detail_msg).setOnClickListener(this);
		findViewById(R.id.layout_account_level).setOnClickListener(this);
		findViewById(R.id.account_username_layout).setOnClickListener(this);
		findViewById(R.id.account_sex_layout).setOnClickListener(this);
		findViewById(R.id.user_info_layout).setOnClickListener(this);
		findViewById(R.id.account_safe_layout).setOnClickListener(this);
		findViewById(R.id.msg_setting_layout).setOnClickListener(this);
		findViewById(R.id.version_update_layout).setOnClickListener(this);
		findViewById(R.id.logout_button).setOnClickListener(this);

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
						
						mAccount_username.setText((userInfo.getNickName() != null) ? userInfo.getNickName() : "未设置");
						mAccount_level.setText((userInfo.getGradeName() != null) ? userInfo.getGradeName() : "普通用户");
						mAccount_sex.setText(userInfo.getSex());
						ImageManager.Load(userInfo.getHeadUrl(),mAccount_head);
						
					} else {

						//登录失败，用户信息解析错误
						showToast("获取用户信息失败");
					}
				}
			}
		});
	}
	
	/**
	 * 
	 * @Description 获取版本信息
	 * @Author blue
	 * @Time 2015-9-28
	 */
	private void loadVersionInfo() {
		String versionNum = null;
		try {
			versionNum = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		RequestClient.queryAppUpdateVersion(this, versionNum, "1", new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.d(TAG, "queryAppUpdateVersion onResponse = "+response.toString());
				if (JSONParseUtils.isSuccessRequest(SettingActivity.this, response)) {
					Gson gson = new Gson();
					mUpdateInfo = gson.fromJson(JSONParseUtils.getString(response, "returnMap"), UpdateInfo.class);
					Log.d(TAG, "local version code = "+UpdateVersionTool.getVersionCode(SettingActivity.this)+", version name = "+mUpdateInfo.getVersionName()+", version num = "+mUpdateInfo.getVersionNum()+", update url = "+mUpdateInfo.getDownloadAddress()+", update tip = "+mUpdateInfo.getUpdateTip());
					updateApk(mUpdateInfo);
				}
			}
		});
	}
	
	/**
	 * 是否有版本更新
	 * @param updateInfo
	 */
	private void updateApk(final UpdateInfo updateInfo) {
		Log.d(TAG, "local version code = "+(UpdateVersionTool.getVersionCode(this)) + " , remote version code = "+updateInfo.getVersionNum()+", remote version name = "+updateInfo.getVersionName());
		try {
			if (UpdateVersionTool.getVersionCode(this) >= Float.parseFloat(updateInfo.getVersionNum())) {
				
			} else {
				VersionTipDialog dialog = new VersionTipDialog(this, updateInfo);
				dialog.show();
				if (mUpdateInfo.getForceUpdate().equals("1")) {
					dialog.setCancelable(false);
				}
				AppContext.isHasUpdate = true;
			}
		} catch (Exception e) {
			showToast("服务器上应用版本号格式错误");
			e.printStackTrace();
		}
	}

	/**
	 * 修改用户信息
	 * @param username		用户名
	 * @param nickname		昵称
	 * @param headImgUrl	头像地址
	 * @param sex			性别
	 * @param identityCard 	身份证号码
	 */
	private void updateUserInfo(String username, String nickname, String headImgUrl, String sex, String identityCard){
		if(!userIsLogin()){
			return;
		}
		String userId = BaoGangData.getInstance().getUser().userId;
		RequestClient.queryAppUpdateUserInfo(mContext, username, nickname, headImgUrl, userId, sex, identityCard, new RequestCallback<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					//获取状态信息
					String msg = JSONParseUtils.getString(response, "msg");
					showToast(msg);
				}
			}
		});
	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.account_detail_msg:
			
			//头像
			showCameraPopwindow(view, true, true);
			
			break;
		case R.id.layout_account_level:
			
			//会员等级
			startActivity(new Intent(mContext, AccountLevelActivity.class));
			
			break;
		case R.id.account_username_layout:
			
			//昵称
			startActivity(new Intent(mContext, UpdateUserNameActiviry.class).putExtra("username",mAccount_username.getText()));
			
			break;
		case R.id.account_sex_layout:
			
			//性别
			Map<String,String> map=new HashMap<String, String>();
            map.put("1","男");
            map.put("2","女");
            map.put("3","保密");
			showReasonPopwindow(view, map, "1", false, new SelectApplyReasonWindow.IOnItemClick() {
                @Override
                public void onItemClick(String id, String value) {
                	mAccount_sex.setText(id);
                	mAccount_sex.setText(value);
                	
                	//修改性别
					updateUserInfo("", "", "", id, "");
                }
            });
			
			break;
		case R.id.user_info_layout:
			
			//我的常用信息
			startActivity(new Intent(mContext, CommonInfoActivity.class));
			
			break;
		case R.id.account_safe_layout:
			
			//账户与安全
			startActivity(new Intent(mContext, AccountSafeActivity.class));
			
			break;
		case R.id.msg_setting_layout:
			
			//消息设置
			startActivity(new Intent(mContext, MessageSettingActivity.class));
			
			break;
		case R.id.version_update_layout:
			
			//版本更新
			Intent intentUpdate = new Intent(mContext, AboutActivity.class);
			if (mUpdateInfo != null) {
				intentUpdate.putExtra(AboutActivity.EXTRA_UPDATE_INFO, mUpdateInfo);
			}
			startActivity(intentUpdate);
			
			break;
		case R.id.logout_button:
			
			//注销
			final SimpleMsgDialog dialog = new SimpleMsgDialog(mContext);
			dialog.setMsg("你确定要退出当前账户吗？");
			dialog.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (v.getId() == R.id.btn_ok) {
						//清空缓存
						BaoGangData.getInstance().setUser(null);
						Preferences.removeKey(PreKey.USER);
						
						finish();
					}
					dialog.dismiss();
				}
			});
			dialog.show();
			
			break;
		}
	}
	
	@Override
	public void onUpLoadSuccess(String imageUrl, String imageFile) {
		super.onUpLoadSuccess(imageUrl, imageFile);
		
		
		//加载网络图片
		if(!TextUtils.isEmpty(imageUrl)){
			
			//显示头像
			ImageManager.Load(imageUrl, mAccount_head);
			
			//将头像更新到服务器
			updateUserInfo("", "", imageUrl, "", "");
			
			return;
		}
		
		
		//加载本地图片
		if(!TextUtils.isEmpty(imageFile)){
			ImageManager.Load("file:///" + imageFile, mAccount_head);
		}
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadUserInfo();
	}

}
