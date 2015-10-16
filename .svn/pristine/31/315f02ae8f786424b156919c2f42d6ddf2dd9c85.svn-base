package com.baosteel.qcsh.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import java.util.List;

/**
 * MainActivity选项卡适配器   
 * @author liuyuanqi
 *
 */
public class FragmentTabAdapter{
	
	// 一个tab页面对应一个Fragment
	private List<Fragment> fragments; 
	
	// Fragment所属的Activity
	private FragmentActivity fragmentActivity; 

	// Activity中所要被替换的区域的id
	private int fragmentContentId; 

	// 当前Tab页面索引
	private int currentTab; 


	public FragmentTabAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments,
			int fragmentContentId) {
		
		//参数赋值
		this.fragments = fragments;
		this.fragmentActivity = fragmentActivity;
		this.fragmentContentId = fragmentContentId;
	}

	/**
	 * 初始化界面
	 */
	public void init(){
		// 默认显示第一页
		FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
		ft.add(fragmentContentId, fragments.get(0));
		ft.commit();
	}
	
	private int currentIndex = 0;
	public void checkedIndex(int checkedIndex) {
		
		//索引没有发生改变
		if(currentIndex == checkedIndex){
			return;
		}
		
		int count = (null != fragments ? fragments.size() : 0);
		
		//标记当前选中索引
		currentIndex = checkedIndex;
		
		for (int i=0; i<count; i++) {
			if (currentIndex == i) {
				Fragment fragment = fragments.get(i);
				FragmentTransaction ft = obtainFragmentTransaction(i);

				getCurrentFragment().onPause(); // 暂停当前tab
				// getCurrentFragment().onStop(); // 暂停当前tab

				if (fragment.isAdded()) {
					// fragment.onStart(); // 启动目标tab的onStart()
					fragment.onResume(); // 启动目标tab的onResume()
				} else {
					ft.add(fragmentContentId, fragment);
				}
				
				// 显示目标tab
				showTab(i); 
				
				
				ft.commit();
			}
		}

	}

	/**
	 * 切换tab
	 * 
	 * @param idx
	 */
	private void showTab(int idx) {
		for (int i = 0;i < fragments.size();i++) {
			Fragment fragment = fragments.get(i);
			FragmentTransaction ft = obtainFragmentTransaction(idx);

			if (idx == i) {
				ft.show(fragment);
			} else {
				ft.hide(fragment);
			}
			ft.commit();
		}
		currentTab = idx; // 更新目标tab为当前tab
	}

	/**
	 * 获取一个带动画的FragmentTransaction
	 * 
	 * @param index
	 * @return
	 */
	private FragmentTransaction obtainFragmentTransaction(int index) {
		FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
		// 设置切换动画
		// if(index > currentTab){
		// ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
		// }else{
		// ft.setCustomAnimations(R.anim.slide_right_in,
		// R.anim.slide_right_out);
		// }
		return ft;
	}

	public int getCurrentTab() {
		return currentTab;
	}

	public Fragment getCurrentFragment() {
		return fragments.get(currentTab);
	}

	public Fragment getFragment(int index) {
		return fragments.get(index);
	}
}
