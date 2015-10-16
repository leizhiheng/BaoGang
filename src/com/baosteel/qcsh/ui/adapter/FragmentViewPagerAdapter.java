package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

/**
 * FragmentViewPager
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-2
 */
public class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> mFragmentList;

	public FragmentViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public FragmentViewPagerAdapter(FragmentManager fm, List<Fragment> mFragmentList) {
		super(fm);
		this.mFragmentList = mFragmentList;
	}

	@Override
	public int getCount() {
		return mFragmentList == null ? 0 : mFragmentList.size();
	}

	// 得到每个item
	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position);
	}

	public void refreshFragmentList(List<Fragment> listLikeFragments) {
		this.mFragmentList = listLikeFragments;
		this.notifyDataSetChanged();
	}
}
