package com.baosteel.qcsh.ui.fragment.home.healthy;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HospitalExpertItem;
import com.baosteel.qcsh.ui.adapter.HospitalExpertListAdapter;
import com.common.base.BaseFragment;
import com.common.view.other.ListViewInScrollView;

public class HospitalExpertIntraoduceFragment extends BaseFragment{

	private ListViewInScrollView mListView;
	private List<HospitalExpertItem> mDatas;
	private HospitalExpertListAdapter mAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_hospital_expert_introduce, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mDatas = new ArrayList<HospitalExpertItem>();
		mAdapter = new HospitalExpertListAdapter(this.getActivity(), mDatas);
	}
	
	private void initView() {
		mListView = (ListViewInScrollView) findViewById(R.id.listview);
		mListView.setAdapter(mAdapter);
	}
	
	private void loadData() {
		for (int i = 0; i < 15; i++) {
			mDatas.add(new HospitalExpertItem());
		}
		mAdapter.notifyDataSetChanged();
	}
}

