package com.baosteel.qcsh.ui.fragment.commen;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baosteel.qcsh.R;
import com.common.base.BaseFragment;

/**
 * 
 * @description 这个Fragment封装了 本地图片上传充能
 * @author blue
 * @Time 2015-9-11
 *
 */
public class UploadImageFragment extends BaseFragment{

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_upload_image, null);
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
