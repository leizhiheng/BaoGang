package com.baosteel.qcsh.ui.fragment.home.happyliving;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baosteel.qcsh.R;
import com.common.base.BaseFragment;

public class SecondHouseBuyingFragment extends BaseFragment{

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_second_house_buying, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initData();

		initView();
		
		loadData();
	}
	
	private void initView() {
		
	}
	
	private void initData() {
		
	}
	
	private void loadData() {
		
	}
}
