package com.baosteel.qcsh.ui.fragment.home.healthy;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baosteel.qcsh.R;
import com.common.base.BaseFragment;

public class PhysicalExamGuardFragment extends BaseFragment{

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_physical_exam_guard, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}


