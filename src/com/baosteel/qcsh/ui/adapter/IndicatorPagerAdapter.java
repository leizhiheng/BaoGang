package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.ui.fragment.commen.RefreshListFragment;
import com.baosteel.qcsh.ui.fragment.commen.TabPagerFragment;
import com.common.base.BaseFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class IndicatorPagerAdapter extends FragmentPagerAdapter {
	
	private List<BaseFragment> mFragments;
	
	public IndicatorPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	public IndicatorPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
		super(fm);
		this.mFragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return mFragments.get(position);
	}

	@Override
	public int getCount() {
		return mFragments == null ? 0 : mFragments.size();
	}
}
