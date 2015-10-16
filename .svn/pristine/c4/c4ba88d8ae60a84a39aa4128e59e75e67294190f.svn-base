package com.baosteel.qcsh.ui.activity.my.setting;

import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baosteel.qcsh.AppContext;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.UpdateInfo;
import com.baosteel.qcsh.utils.UpdateManager;
import com.baosteel.qcsh.utils.UpdateVersionTool;
import com.common.base.BaseCameraActivity;

/**
 * 关于七彩生活
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-21
 */
public class AboutActivity extends BaseCameraActivity implements OnClickListener{

	public static final String EXTRA_UPDATE_INFO = "download.url";
	private Button mBtnUpdate;
	private UpdateInfo mUpdateInfo;
	
	private TextView mTvVersionCodeLocal;
	private TextView mTvVersionCodeNew;
	private TextView mTvVersionTip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		initData();
		//初始化控件
		initView();	
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		
		setTitle("关于七彩生活");
		mUpdateInfo = (UpdateInfo) getIntent().getSerializableExtra(EXTRA_UPDATE_INFO);
		
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		
		mTvVersionCodeLocal = (TextView) findViewById(R.id.tv_version_code_local);
		mTvVersionCodeNew = (TextView) findViewById(R.id.tv_version_code_new);
		mTvVersionTip = (TextView) findViewById(R.id.tv_version_tip);
		
		if (AppContext.isHasUpdate) {
			mBtnUpdate = (Button) findViewById(R.id.btn_update_app);
			mBtnUpdate.setVisibility(View.VISIBLE);
			mBtnUpdate.setOnClickListener(this);
			mTvVersionCodeNew.setVisibility(View.VISIBLE);
			mTvVersionCodeNew.setText("最新版本  "+mUpdateInfo.getVersionName());
		} 
		
		mTvVersionCodeLocal.setText("当前版本  " + UpdateVersionTool.getVersionName(this));
		mTvVersionTip.setText(mUpdateInfo.getUpdateTip());

	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btn_update_app:
			UpdateManager manager = new UpdateManager(this, "baosteel.apk", mUpdateInfo.getDownloadAddress());
			manager.showDownloadDialog();
			break;

		default:
			break;
		}
	}
}
