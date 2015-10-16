package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;

import android.os.Bundle;

import com.baosteel.qcsh.ui.activity.common.TabPagerActivity;
import com.baosteel.qcsh.ui.fragment.home.ReportRepairListFragment;
import com.common.base.BaseFragment;

/**
 * 
 * @description 页面：乐居宝-》物业服务-》我的物业-》我的预约服务
 * @author blue
 * @Time 2015-9-10
 *
 */
public class ReportRepairListActivity extends TabPagerActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		initFragments();
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * 
	 * @Description 设置切换的Fragment
	 * @Author blue
	 * @Time 2015-9-10
	 */
	private void initFragments() {
		mFragments = new ArrayList<BaseFragment>();
		int color[] = new int[]{0xff00ffff,0xff0000ff,0xfff00fff,0xffff0000,0xff9966CC};
		for (int i = 0; i < color.length; i++) {
			mFragments.add(ReportRepairListFragment.getInstance(color[i]));
		}
	}
}
