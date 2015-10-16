package com.common.base;

import android.view.KeyEvent;
import android.widget.Toast;

import com.common.utils.AppManager;

/**
 * 主界面的基类
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-27
 */
public class BaseMainActivity extends BaseActivity{
	
	private long secondTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		long spaceTime = 0;
		long firstTime = 0;
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			firstTime = System.currentTimeMillis();
			spaceTime = firstTime - secondTime;
			secondTime = firstTime;
			if (spaceTime > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				return false;
			} else {
				AppManager.getAppManager().AppExit(this);
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
