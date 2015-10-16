package com.baosteel.qcsh.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * viewpager+fragment适配器 Created by kuangyong on 15/9/1.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

	private List<String> titleList;

	private List<Fragment> list;

	public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	public void setTitles(List<String> titleList) {
		this.titleList = titleList;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		try{
			return titleList.get(position);
		}catch(Exception e){
			return super.getPageTitle(position);
		}
	}
}
