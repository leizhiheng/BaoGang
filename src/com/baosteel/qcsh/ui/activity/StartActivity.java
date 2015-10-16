package com.baosteel.qcsh.ui.activity;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Window;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.User;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.Preferences;
import com.baosteel.qcsh.utils.Preferences.PreKey;
import com.common.base.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

/**
 * 启动界面
 * @author 刘远祺
 *
 */
public class StartActivity extends BaseActivity {

	private Handler mHandler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message message) {

			//获取用户上次登录保存的信息
			String json = Preferences.getString(PreKey.USER);
			if(!TextUtils.isEmpty(json)){
				
				User user = JSONParseUtils.parseUser(json);
				if(null != user && !TextUtils.isEmpty(user.userId)){
					
					//登录成功
					BaoGangData.getInstance().setUser(user);
				}
			}

			// 进入到主界面
			startActivity(new Intent(StartActivity.this, MainActivity.class));
			finish();
			return false;
		}
	});

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
		
		sleep(2000);
	}


	/**
	 * 睡眠1秒
	 */
	private void sleep(long delay) {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// 通知去加载购物车，加载好了，跳转到主界面
				mHandler.sendEmptyMessage(0);
			}
		};
		timer.schedule(task, delay);
	}
}
